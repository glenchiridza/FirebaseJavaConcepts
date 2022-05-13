package com.glencconnnect.firebasedemoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            finish();
        });
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        });
    }
}