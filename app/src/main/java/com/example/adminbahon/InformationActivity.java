package com.example.adminbahon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button usersBtn, ridersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        this.setTitle("Dashboard");

        usersBtn = findViewById(R.id.usersBtnId);
        ridersBtn = findViewById(R.id.ridersBtnId);

        usersBtn.setOnClickListener(this);
        ridersBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usersBtnId:
                Intent users = new Intent(getApplicationContext(), UsersActivity.class);
                startActivity(users);
                break;

            case R.id.ridersBtnId:
                Intent riders = new Intent(getApplicationContext(), RidersActivity.class);
                startActivity(riders);
                break;

        }

    }
}