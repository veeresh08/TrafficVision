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

import java.util.HashMap;
import java.util.Map;

public class EditpostActivity extends AppCompatActivity {

    //creating variables for our edit text, firebase database, database reference, post rv modal,progress bar.
    private TextInputEditText PostNameEdt, postDescEdt, postDateEdt, bestSuitedEdt, postImgEdt, postLinkEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    postRVModal postRVModal;
    private ProgressBar loadingPB;
    //creating a string for our post id.
    private String postID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        //initializing all our variables on below line.
        Button addPostBtn = findViewById(R.id.idBtnAddPost);
        PostNameEdt = findViewById(R.id.idEdtpostName);
        postDescEdt = findViewById(R.id.idEdtPostDescription);
        postDateEdt = findViewById(R.id.idEdtDate);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        postImgEdt = findViewById(R.id.idEdtPostImageLink);
        postLinkEdt = findViewById(R.id.idEdtPostLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //on below line we are getting our modal class on which we have passed.
        postRVModal = getIntent().getParcelableExtra("post");
        Button deletepostBtn = findViewById(R.id.idBtnDeletePost);

        if (postRVModal != null) {
            //on below line we are setting data to our edit text from our modal class.
            PostNameEdt.setText(postRVModal.getpostName());
            postDateEdt.setText(postRVModal.getpostPrice());
            bestSuitedEdt.setText(postRVModal.getBestSuitedFor());
            postImgEdt.setText(postRVModal.getpostImg());
            postLinkEdt.setText(postRVModal.getpostLink());
            postDescEdt.setText(postRVModal.getpostDescription());
            postID = postRVModal.getpostId();
        }

        //on below line we are initialing our database reference and we are adding a child as our post id.
        databaseReference = firebaseDatabase.getReference("posts").child(postID);
        //on below line we are adding click listener for our add post button.
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line we are making our progress bar as visible.
                loadingPB.setVisibility(View.VISIBLE);
                //on below line we are getting data from our edit text.
                String postName = PostNameEdt.getText().toString();
                String postDesc = postDescEdt.getText().toString();
                String postPrice = postDateEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String postImg = postImgEdt.getText().toString();
                String postLink = postLinkEdt.getText().toString();
                //on below line we are creating a map for passing a data using key and value pair.
                Map<String, Object> map = new HashMap<>();
                map.put("postName", postName);
                map.put("postDescription", postDesc);
                map.put("postPrice", postPrice);
                map.put("bestSuitedFor", bestSuited);
                map.put("postImg", postImg);
                map.put("postLink", postLink);
                map.put("postId", postID);

                //on below line we are calling a database reference on add value event listener and on data change method
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        //adding a map to our database.
                        databaseReference.updateChildren(map);
                        //on below line we are displaying a toast message.
                        Toast.makeText(EditpostActivity.this, "post Updated..", Toast.LENGTH_SHORT).show();
                        //opening a new activity after updating our coarse.
                        startActivity(new Intent(EditpostActivity.this, AlertActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //displaying a failure message on toast.
                        Toast.makeText(EditpostActivity.this, "Fail to update post..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //adding a click listener for our delete post button.
        deletepostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling a method to delete a post.
                deletepost();
            }
        });

    }

    private void deletepost() {
        //on below line calling a method to delete the post.
        databaseReference.removeValue();
        //displaying a toast message on below line.
        Toast.makeText(this, "post Deleted..", Toast.LENGTH_SHORT).show();
        //opening a main activity on below line.
        startActivity(new Intent(EditpostActivity.this, AlertActivity.class));
    }
}