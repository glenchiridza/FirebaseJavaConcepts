package com.glencconnnect.firebasedemoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email  = findViewById(R.id.txtEm);
        password = findViewById(R.id.txtPass);
        btnSignin = findViewById(R.id.btnSignIn);

        mAuth  = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(v->{
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(LoginActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }else{
                signIn(txt_email,txt_password);
            }
        });

    }

    private void signIn(String txt_email, String txt_password) {
        mAuth.signInWithEmailAndPassword(txt_email,txt_password)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                });
    }
}