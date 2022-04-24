package com.example.project.Activity.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class passwordRest extends AppCompatActivity {
    EditText uemail;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_rest);

        uemail=(EditText) findViewById(R.id.uemail);
        Button btnupdate = findViewById(R.id.btnupdate);





        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                sendemail();
            }
        });
    }

    public void sendemail(){

       // Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
        String email = uemail.getText().toString();
        String emailAddress = email;

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"sent successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}

