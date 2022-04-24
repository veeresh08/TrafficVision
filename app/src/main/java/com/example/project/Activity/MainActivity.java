package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.setting.SettingActivity;
import com.example.project.Activity.sos.SosActivity;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation();






    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout sosBtn = findViewById(R.id.sosBtn);
        LinearLayout settings = findViewById(R.id.settings);
        ConstraintLayout pay_now = findViewById(R.id.pay_now);
        ConstraintLayout report_now = findViewById(R.id.report_now);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();


            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
            TextView tvName = findViewById(R.id.tvName);

            tvName.setText("Hi "+email);
        }

        report_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StolenVehicle.class);
                startActivity(intent);
                //openAction();
            }
        });


        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReadData.class);
                startActivity(intent);
                //openAction();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraFirebaseActivity.class);
                startActivity(intent);
                //openAction();
            }
        });


        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SosActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
    public void openAction(){
        setContentView(R.layout.activity_camera_firebase);

    }

    public void openHome(){
        setContentView(R.layout.activity_show_detail);

    }



}
