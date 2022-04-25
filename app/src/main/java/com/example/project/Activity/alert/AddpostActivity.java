package com.example.project.Activity.alert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddpostActivity extends AppCompatActivity {

    //creating variables for our button, edit text,firebase database, database refrence, progress bar.
    private Button addPostBtn;
    private TextInputEditText PostNameEdt, postDescEdt, postDateEdt, bestSuitedEdt, postImgEdt, postLinkEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String postID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        //initializing all our variables.
        addPostBtn = findViewById(R.id.idBtnAddPost);
        PostNameEdt = findViewById(R.id.idEdtPostName);
        postDescEdt = findViewById(R.id.idEdtPostDescription);
        postDateEdt = findViewById(R.id.idEdtDate);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        postImgEdt = findViewById(R.id.idEdtPostImageLink);
        postLinkEdt = findViewById(R.id.idEdtPostLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference("posts");
        //adding click listener for our add post button.
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                //getting data from our edit text.
                String postName = PostNameEdt.getText().toString();
                String postDesc = postDescEdt.getText().toString();
                String postPrice = postDateEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String postImg = postImgEdt.getText().toString();
                String postLink = postLinkEdt.getText().toString();
                postID = postName;
                //on below line we are passing all data to our modal class.
                postRVModal postRVModal = new postRVModal(postID, postName, postDesc, postPrice, bestSuited, postImg, postLink);
                //on below line we are calling a add value event to pass data to firebase database.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //on below line we are setting data in our firebase database.
                        databaseReference.child(postID).setValue(postRVModal);
                        //displaying a toast message.
                        Toast.makeText(AddpostActivity.this, "post Added..", Toast.LENGTH_SHORT).show();
                        //starting a main activity.
                        startActivity(new Intent(AddpostActivity.this, AlertActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //displaying a failure message on below line.
                        Toast.makeText(AddpostActivity.this, "Fail to add post..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}