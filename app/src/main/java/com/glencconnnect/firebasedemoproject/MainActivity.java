package com.glencconnnect.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btnlogout;

    private FirebaseAuth mAuth;

    private FirebaseDatabase fbDatabase;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();

        btnlogout.setOnClickListener(v->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,StartActivity.class));
        });

        fbDatabase = FirebaseDatabase.getInstance();
        dbRef = fbDatabase.getReference().child("Books").child("Android-Java");

        HashMap<String,Object> map = new HashMap<>();
        map.put("name","Introduction to Java");
        map.put("author","Glen Chiridza");

        dbRef  =fbDatabase.getReference().child("Books").child("Android-Java");

        dbRef.updateChildren(map);
    }
}