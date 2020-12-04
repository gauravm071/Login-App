package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    TextView username,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username= findViewById(R.id.username);
        email= findViewById(R.id.email);
        Intent intent = getIntent();
        username.setText("Username : "+intent.getStringExtra("username"));
        email.setText("Email : "+intent.getStringExtra("email"));
    }
}