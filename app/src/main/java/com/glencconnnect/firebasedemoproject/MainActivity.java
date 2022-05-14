package com.glencconnnect.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnlogout;

    private EditText edtBookName;
    private EditText edtAuthorName;
    private Button btnSave;

    private TextView dataHolder;
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
        dataHolder = findViewById(R.id.txt_data);
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

//        dbRef.updateChildren(map);

        btnSave.setOnClickListener(v->{
            String txt_book_name = edtBookName.getText().toString();
            String txt_author_name = edtAuthorName.getText().toString();
            if(txt_book_name.isEmpty() || txt_author_name.isEmpty()){
                Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }else{
                //single value way
//                dbRef.push().child("name").setValue(txt_book_name);
//                dbRef.push().child("author").setValue(txt_author_name);

                //or
                map.put("name",txt_book_name);
                map.put("author",txt_author_name);
                dbRef.push().setValue(map)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(MainActivity.this, "Data Saved to DB", Toast.LENGTH_SHORT).show();
                            edtBookName.setText("");
                            edtAuthorName.setText("");
                        });
            }
        });

        final ArrayList<String> list = new ArrayList<>();
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for(DataSnapshot snap : snapshot.getChildren()){
//                    list.add(snap.getValue().toString());
//                    dataHolder.setText(list.toString());
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        //using model
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    Books books = snap.getValue(Books.class);
                    String text = books.getName() + ", " + books.getAuthor();
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    list.add(text);
                    dataHolder.setText(list.toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> topics = new HashMap<>();
        topics.put("name","Django");
        topics.put("author","Faith Chiridza");

        db.collection("Books").document("Learning")
                .set(topics)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                    }
                });

        //to merge data, in case you feel, you need to add another key value pair to existing document data
        //and merge the data

//        Map<String,Object> topics_added_field = new HashMap<>();
//        topics_added_field.put("status","member");
//
//        db.collection("Books").document("Learning")
//                .set(topics_added_field, SetOptions.merge())
//                .addOnCompleteListener(task -> {
//                    if(task.isSuccessful()){
//                        Toast.makeText(MainActivity.this, "additional field added", Toast.LENGTH_SHORT).show();
//                    }
//                });


        //in order to give data a unique ID use the add function
        Map<String,Object> data = new HashMap<>();
        data.put("name","Flask");
        data.put("author","Fay");
        db.collection("Books").add(data)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}