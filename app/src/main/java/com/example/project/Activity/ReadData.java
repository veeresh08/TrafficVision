package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ReadData extends AppCompatActivity {


    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read_data);

        Button readdataBtn = findViewById(R.id.readdataBtn);
        EditText numberplate = findViewById(R.id.numberplate);

        readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = numberplate.getText().toString();
                if (!username.isEmpty()){

                    readData(username);
                }else{

                    Toast.makeText(ReadData.this,"PLease Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void readData(String username) {

        TextView tvViolation = findViewById(R.id.tvViolation);
        TextView tvLocation = findViewById(R.id.tvLocation);
        TextView tvAmount = findViewById(R.id.tvAmount);
        reference = FirebaseDatabase.getInstance().getReference("Fine");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(ReadData.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String violation = String.valueOf(dataSnapshot.child("violation").getValue());
                        String location = String.valueOf(dataSnapshot.child("location").getValue());
                        String amount = String.valueOf(dataSnapshot.child("amount").getValue());
                        tvViolation.setText(violation);
                        tvLocation.setText(location);
                        tvAmount.setText(amount);


                    }else {

                        Toast.makeText(ReadData.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }


                }else {

                    Toast.makeText(ReadData.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}