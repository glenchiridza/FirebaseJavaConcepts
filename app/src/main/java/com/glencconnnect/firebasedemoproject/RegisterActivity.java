package com.glencconnnect.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        btnSignup = findViewById(R.id.btnSignUp);

        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(v->{
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(RegisterActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
            }else if(txt_password.length() < 6){
                Toast.makeText(RegisterActivity.this, "short password", Toast.LENGTH_SHORT).show();
            }else{
                signUp(txt_email,txt_password);
            }
        });
    }

    private void signUp(String txt_email, String txt_password) {
        mAuth.createUserWithEmailAndPassword(txt_email,txt_password)
                .addOnCompleteListener(RegisterActivity.this, task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(RegisterActivity.this, "Signup failed, an error occured", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}