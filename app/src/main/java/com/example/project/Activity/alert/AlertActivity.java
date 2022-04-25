package com.example.project.Activity.alert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.CameraFirebaseActivity;
import com.example.project.Activity.MainActivity;
import com.example.project.Activity.setting.SettingActivity;
import com.example.project.Activity.sos.SosActivity;
import com.example.project.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity implements postRVAdapter.postClickInterface {

    //creating variables for fab, firebase database, progress bar, list, adapter,firebase auth, recycler view and relative layout.
    private FloatingActionButton addpostFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView postRV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private ArrayList<com.example.project.Activity.alert.postRVModal> postRVModalArrayList;
    private postRVAdapter postRVAdapter;
    private RelativeLayout homeRL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        //initializing all our variables.
        postRV = findViewById(R.id.idRVposts);
        homeRL = findViewById(R.id.idRLBSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        addpostFAB = findViewById(R.id.idFABAddpost);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        postRVModalArrayList = new ArrayList<>();
        //on below line we are getting database reference.
        databaseReference = firebaseDatabase.getReference("posts");
        //on below line adding a click listener for our floating action button.

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email="";
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
           email = user.getEmail();
        }
        email.trim();
        String admin="jveereshnaik@gmail.com";

        if(email.equals(admin)){
            Toast.makeText(getApplicationContext(),"inside if : "+email,Toast.LENGTH_LONG).show();
            addpostFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //opening a new activity for adding a post.
                    Intent i = new Intent(AlertActivity.this, com.example.project.Activity.alert.AddpostActivity.class);
                    startActivity(i);
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"inside else  :  "+email,Toast.LENGTH_LONG).show();
            addpostFAB.setVisibility(View.INVISIBLE);
        }

        //on below line initializing our adapter class.
        postRVAdapter = new postRVAdapter(postRVModalArrayList, this, this::onpostClick);
        //setting layout malinger to recycler view on below line.
        postRV.setLayoutManager(new LinearLayoutManager(this));
        //setting adapter to recycler view on below line.
        postRV.setAdapter(postRVAdapter);
        //on below line calling a method to fetch posts from database.
        getposts();
    }

    private void getposts() {
        //on below line clearing our list.
        postRVModalArrayList.clear();
        //on below line we are calling add child event listener method to read the data.
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //on below line we are hiding our progress bar.
                loadingPB.setVisibility(View.GONE);
                //adding snapshot to our array list on below line.
                postRVModalArrayList.add(snapshot.getValue(com.example.project.Activity.alert.postRVModal.class));
                //notifying our adapter that data has changed.
                postRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //this method is called when new child is added we are notifying our adapter and making progress bar visibility as gone.
                loadingPB.setVisibility(View.GONE);
                postRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //notifying our adapter when child is removed.
                postRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //notifying our adapter when child is moved.
                postRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bottomNavigation();


    }

    @Override
    public void onpostClick(int position) {
        //calling a method to display a bottom sheet on below line.
        displayBottomSheet(postRVModalArrayList.get(position));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //adding a click listner for option selected on below line.
        int id = item.getItemId();
        switch (id) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void displayBottomSheet(com.example.project.Activity.alert.postRVModal modal) {
        //on below line we are creating our bottom sheet dialog.
        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        //on below line we are inflating our layout file for our bottom sheet.
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, homeRL);
        //setting content view for bottom sheet on below line.
        bottomSheetTeachersDialog.setContentView(layout);
        //on below line we are setting a cancelable
        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        //calling a method to display our bottom sheet.
        bottomSheetTeachersDialog.show();
        //on below line we are creating variables for our text view and image view inside bottom sheet
        //and initialing them with their ids.
        TextView postNameTV = layout.findViewById(R.id.idTVpostName);
        TextView postDescTV = layout.findViewById(R.id.idTVpostDesc);
        TextView suitedForTV = layout.findViewById(R.id.idTVSuitedFor);
        TextView priceTV = layout.findViewById(R.id.idTVpostPrice);
        ImageView postIV = layout.findViewById(R.id.idIVpost);
        //on below line we are setting data to different views on below line.
        postNameTV.setText(modal.getpostName());
        postDescTV.setText(modal.getpostDescription());
        suitedForTV.setText("Suited for " + modal.getBestSuitedFor());
        priceTV.setText(modal.getpostPrice());
        Picasso.get().load(modal.getpostImg()).into(postIV);
        Button viewBtn = layout.findViewById(R.id.idBtnVIewDetails);
        Button editBtn = layout.findViewById(R.id.idBtnEditpost);


        //adding on click listener for our edit button.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email="";
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            email = user.getEmail();
        }
        email.trim();
        String admin="jveereshnaik@gmail.com";

        if(email.equals(admin)){
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on below line we are opening our EditpostActivity on below line.
                    Intent i = new Intent(AlertActivity.this, com.example.project.Activity.alert.EditpostActivity.class);
                    //on below line we are passing our post modal
                    i.putExtra("post", modal);
                    startActivity(i);
                }
            });
            //adding click listener for our view button on below line.
            viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on below line we are navigating to browser for displaying post details from its url
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(modal.getpostLink()));
                    startActivity(i);
                }
            });
        }else{
            editBtn.setVisibility(View.INVISIBLE);
            //adding click listener for our view button on below line.
            viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on below line we are navigating to browser for displaying post details from its url
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(modal.getpostLink()));
                    startActivity(i);
                }
            });
        }


    }


    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout sosBtn = findViewById(R.id.sosBtn);
        LinearLayout settings = findViewById(R.id.settings);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, CameraFirebaseActivity.class);
                startActivity(intent);
                //openAction();
            }
        });


        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, SosActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }


}