package com.example.freezone;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.net.URI;
import java.util.Locale;

public class productUpload extends AppCompatActivity {
    private TextView headline, description, price, category, productName;
    private ImageView photo;
    Uri ImageUri;
    RelativeLayout relative;
    private Button upload, show,submit;
    //decaring firebase variables
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_upload);
        headline = (TextView) findViewById(R.id.headline);
        description = (TextView) findViewById(R.id.description);
        price = (TextView) findViewById(R.id.price);
        category = (TextView) findViewById(R.id.category);
        photo = (ImageView) findViewById(R.id.photo);
        upload = findViewById(R.id.upload);
        productName = (TextView) findViewById(R.id.productName);
        submit = (Button) findViewById(R.id.submit);
        show = (Button) findViewById(R.id.show);
        firebaseStorage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();


            }
        });
        // method for showing data to recycler view from firebase
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              productDetailShow();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = productName.getText().toString().trim();
                String cat = category.getText().toString().trim();
                String desc = description.getText().toString().trim();
                String head = headline.getText().toString().trim();
                String prices = price.getText().toString().trim();
                if (head.isEmpty()) {
                    headline.setError("Please Enter the Product Headline!");
                } else if (desc.isEmpty()) {
                    description.setError("Please enter the product description!");
                } else if (prices.isEmpty()) {
                    price.setError("Please enter the product description!");
                } else if (cat.isEmpty()) {
                    category.setError("Please enter the product name!");
                } else if (name.isEmpty()) {
                    productName.setError("Please enter the product Category!");
                } else {
                    final StorageReference reference = firebaseStorage.getReference().
                            child(System.currentTimeMillis() + "");
                    Toast.makeText(productUpload.this, ImageUri.toString(), Toast.LENGTH_SHORT).show();

                    reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(productUpload.this, "Product", Toast.LENGTH_SHORT).show();

                                    productModal modal = new productModal();
                                    modal.setPhoto(uri.toString());
                                    modal.setProductName(productName.getText().toString());
                                    modal.setCategory(category.getText().toString());
                                    modal.setDescription(description.getText().toString());
                                    modal.setHeadline(headline.getText().toString());
                                    modal.setPrice(price.getText().toString());
                                    database.getReference().child("product").push().setValue(modal).
                                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(productUpload.this, "Product Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(productUpload.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void uploadImage() {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        //this methodis used to receive the selected  image
                        startActivityForResult(intent, PICK_IMAGE_REQUEST);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(productUpload.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            ImageUri = data.getData();
            photo = (ImageView) findViewById(R.id.photo);
            photo.setImageURI(ImageUri);


        }
    }
    public void  productDetailShow(){

    }

}

