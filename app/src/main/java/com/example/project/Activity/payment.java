package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class payment extends AppCompatActivity {
    int view = R.layout.activity_payment;
    Button textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        //final LinearLayout parent = findViewById(R.id.parent);
        textView = findViewById(R.id.card_btn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(payment.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}