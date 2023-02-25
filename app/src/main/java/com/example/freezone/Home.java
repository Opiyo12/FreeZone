package com.example.freezone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<modalClass> userlist;
    Adapter adapter;
    DatabaseReference pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pre  = FirebaseDatabase.getInstance().getReference();
        initData();
   initRecyclerview();
    }
    @Override
//   Displaying the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                startActivity(new Intent(Home.this, productUpload.class));
                return true;

            case R.id.setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show();
                return true;

            case R.id.logout:

                startActivity(new Intent(Home.this, Login.class));
                return true;
        }
       return false;
    }
            private void initData() {
        userlist= new ArrayList<>();
        userlist.add(new modalClass(R.drawable.oppo, "Oppo" ,"Price:UGX 550,000"));
        userlist.add(new modalClass(R.drawable.jumper, "Jumper" ,"Price:UGX 28,000"));
        userlist.add(new modalClass(R.drawable.spark, "Spark 7" ,"Price:UGX 420,000"));
        userlist.add(new modalClass(R.drawable.pc, "Hp Elite" ,"Price:UGX 850,000"));
        userlist.add(new modalClass(R.drawable.baby, "Baby's Wear" ,"Price:UGX 22,000"));
        userlist.add(new modalClass(R.drawable.phone1, "Samusung S7" ,"Price:UGX 350,000"));
        userlist.add(new modalClass(R.drawable.lsleve, "Long Sleve" ,"Price:UGX 30,000"));

          pre.child("product").addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                // modalClass t = snapshot.getValue();
               //   userlist.add(new modalClass())
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
    }

    private void initRecyclerview() {
        recyclerView =findViewById(R.id.recycler);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(userlist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}