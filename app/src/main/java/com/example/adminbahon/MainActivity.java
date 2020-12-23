package com.example.adminbahon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText userNameET, passwordET;
    private Button login;
    String userNameFB, passwordFB;

    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login");

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        userNameET = findViewById(R.id.userNameET);
        passwordET = findViewById(R.id.passwordET);
        login = findViewById(R.id.logInButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("Admin");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userNameFB = snapshot.child("user").getValue().toString();
                passwordFB = snapshot.child("password").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void adminLogin() {

        String userName  = userNameET.getText().toString().trim();
        String password  = passwordET.getText().toString().trim();

        if(userName.isEmpty()){
            userNameET.setError("Enter an user name");
            userNameET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordET.setError("Enter a password");
            passwordET.requestFocus();
            return;
        }

        if(userName.equals(userNameFB) && password.equals(passwordFB)){
            Intent dash = new Intent(this, InformationActivity.class);
            startActivity(dash);


        }

    }


}

