package com.example.freezone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private TextView banner;
 private EditText uname, contact,pwd,cpwd;
  private Button signup;
  private ProgressBar progressBar;
    private FirebaseAuth mAuth;

   // Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         ActionBar bar=getSupportActionBar();
        bar.setTitle("Register");
        mAuth= FirebaseAuth.getInstance();
       // banner=(TextView)findViewById(R.id.banner);
        signup=(Button) findViewById(R.id.signup);
      //  signup.setOnClickListener((View.OnClickListener) this);
        contact=(EditText)findViewById(R.id.contact);
        pwd=(EditText)findViewById(R.id.pwd);
        cpwd=(EditText)findViewById(R.id.cpwd);
        uname=(EditText)findViewById(R.id.uname);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

    }
    public void buClick(View view) {

        signup();
    }

    private void signup() {
        String email= uname.getText().toString().trim();
        String phone= contact.getText().toString().trim();
        String password= pwd.getText().toString().trim();
        String confirmPassword= cpwd.getText().toString().trim();
        if(email.isEmpty()){
            uname.setError("Email is required");
            uname.requestFocus();
            return;
        }
        else  if(phone.isEmpty()){
            contact.setError("Contact is required");
            contact.requestFocus();
            return;
        }
        else  if(password.isEmpty()){
            pwd.setError("Password is required");
            pwd.requestFocus();
            return;
        }
        else  if(confirmPassword.isEmpty()){
            cpwd.setError("Contact is required");
            cpwd.requestFocus();
            return;
        }
        else if(password!=confirmPassword){
            cpwd.setError("Password Mistmatched");
            cpwd.requestFocus();
        }
        else  if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            uname.setError("Please provide valid email");
            uname.requestFocus();
            return;
        }
        else  if(password.length()<6){
            pwd.setError("Min password length is 6 characters");
            pwd.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             userRegistration user= new userRegistration(email,phone,password,confirmPassword);
                             FirebaseDatabase.getInstance().getReference("user")
                                     .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                     .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful()){
                                               startActivity(new Intent(register.this, Login.class));
                                               progressBar.setVisibility(View.GONE);
                                             }
                                           else{
                                               Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(View.GONE);
                                           }
                                         }
                                     });

                         }
                         else {
                             Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                             progressBar.setVisibility(View.GONE);
                         }
                    }
                });

    }

    public void bulogin(View view) {
        Intent intent = new Intent(register.this, Login.class);
            startActivity(intent);
    }
}