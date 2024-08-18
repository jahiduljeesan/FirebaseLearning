package com.example.firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding actMain;
    FirebaseDatabase User_Database;
    DatabaseReference reference;
    String name,email;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actMain = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(actMain.getRoot());

        actMain.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = actMain.etName.getText().toString();
                email = actMain.etEmail.getText().toString();

                if (name.isEmpty() || email.isEmpty()) return;

                User_Database = FirebaseDatabase.getInstance();
                reference = User_Database.getReference("Users");
                reference.child(id+"")
                        .setValue(new User(id,name,email))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "data is added", Toast.LENGTH_SHORT).show();
                                id++;
                            }
                        });

            }
        });

    }
}