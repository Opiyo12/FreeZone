package com.example.freezone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button login;
    private EditText uname, pwd;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar bar=getSupportActionBar();
        bar.setTitle("Login");
        login=(Button)findViewById(R.id.login);
        uname=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();

    }
    public void btnlogin(View view) {
        switch (view.getId()){
            case R.id.login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
       String email= uname.getText().toString().trim();
       String password= pwd.getText().toString().trim();
        if(email.isEmpty()){
            uname.setError("Email is required");
            uname.requestFocus();
            return;
        }
        else  if(password.isEmpty()){
            pwd.setError("Contact is required");
            pwd.requestFocus();
            return;
        }
        else  if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            uname.setError("Please provide valid email");
            uname.requestFocus();
            return;
        }
        else  if(password.length()<6){
            pwd.setError("Min password length is 6 characters");
            pwd.requestFocus() ;
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect
                    startActivity(new Intent(Login.this, Home.class));
                }
                else{
                    Toast.makeText(Login.this, "Failed to Login", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void btnsignup(View view) {
        Intent intent = new Intent(Login.this, register.class);
        startActivity(intent);
    }
}