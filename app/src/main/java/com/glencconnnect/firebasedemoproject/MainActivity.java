package com.glencconnnect.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btnlogout;

    private EditText edtBookName;
    private EditText edtAuthorName;
    private Button btnSave;

    private FirebaseAuth mAuth;

    private FirebaseDatabase fbDatabase;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogout = findViewById(R.id.btnLogout);

        edtAuthorName = findViewById(R.id.txtAuthor);
        edtBookName = findViewById(R.id.txtBookName);
        btnSave = findViewById(R.id.btnSave);

        mAuth = FirebaseAuth.getInstance();

        btnlogout.setOnClickListener(v->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,StartActivity.class));
        });

        fbDatabase = FirebaseDatabase.getInstance();
        dbRef = fbDatabase.getReference().child("Books").child("Android-Java");

        HashMap<String,Object> map = new HashMap<>();
//        map.put("name","Introduction to Java");
//        map.put("author","Glen Chiridza");

        dbRef  =fbDatabase.getReference().child("Books").child("Android-Java");

        dbRef.updateChildren(map);

        btnSave.setOnClickListener(v->{
            String txt_book_name = edtBookName.getText().toString();
            String txt_author_name = edtBookName.getText().toString();
            if(txt_book_name.isEmpty() || txt_author_name.isEmpty()){
                Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }else{
                map.put("name",txt_book_name);
                map.put("author",txt_author_name);
                dbRef.updateChildren(map)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(MainActivity.this, "Data Saved to DB", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}