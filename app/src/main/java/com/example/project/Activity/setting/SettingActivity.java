package com.example.project.Activity.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.Activity.CameraFirebaseActivity;
import com.example.project.Activity.MainActivity;
import com.example.project.Activity.ReadData;
import com.example.project.Activity.StolenVehicle;
import com.example.project.Activity.alert.AlertActivity;
import com.example.project.Activity.loginRegister.LoginActivity;
import com.example.project.Activity.sos.SosActivity;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ConstraintLayout update_profile = findViewById(R.id.update_profile);
        ConstraintLayout changepwd = findViewById(R.id.changepwd);
        Button logout_btn = findViewById(R.id.logout_btn);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ProfileActivity.class);
                startActivity(intent);
                //openAction();
            }
        });

        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, passwordRest.class);
                startActivity(intent);
                //openAction();
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //openAction();
            }
        });


        bottomNavigation();


    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout sosBtn = findViewById(R.id.sosBtn);
        LinearLayout alertBtn = findViewById(R.id.alertBtn);
        LinearLayout settings = findViewById(R.id.settings);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, CameraFirebaseActivity.class);
                startActivity(intent);
                //openAction();
            }
        });


        sosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, SosActivity.class);
                startActivity(intent);
            }
        });

        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AlertActivity.class);
                startActivity(intent);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

}