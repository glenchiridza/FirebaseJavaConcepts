package com.glencconnnect.firebasedemoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        btnSignup = findViewById(R.id.btnSignUp);


        btnSignup.setOnClickListener(v->{
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(RegisterActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
            }else if(txt_password.length() < 6){
                Toast.makeText(RegisterActivity.this, "short password", Toast.LENGTH_SHORT).show();
            }else{
                signUp(txt_email,txt_password);
            }
        });
    }

    private void signUp(String txt_email, String txt_password) {

    }
}