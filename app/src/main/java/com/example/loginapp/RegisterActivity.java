package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password, confirmPassWord;
    Button register;
    TextView error;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.etusernameReg);
        email = findViewById(R.id.etEmailreg);
        password = findViewById(R.id.etPassreg);
        confirmPassWord = findViewById(R.id.etconfirpassreg);
        register = findViewById(R.id.register);
        error = findViewById(R.id.tverror);
        String pass = password.getText().toString();

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 10) {
                    password.setError("Password should be of atmost 10 characters");
                }
            }
        });

        confirmPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                if (password.getText().toString().equals(s.toString())) {
                    check = true;
                    error.setText("Password matched");
                    error.setTextColor(R.color.green);
                } else {
                    System.out.println(password.getText().toString() + " " + s.toString());
                    error.setText("Password does not matched");
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            @Override
            public void onClick(View v) {

                if ((username.getText().toString().length() == 0) || (email.getText().toString().length() == 0) || (password.getText().toString().length() == 0)) {
                    System.out.println("entered");
                    error.setText("enter all the details");
                } else if (!check && password.getText().toString().length() > 0) {
                    confirmPassWord.setError("Enter correct Password");
                } else if (!checkemailformat(email.getText().toString()) && email.getText().toString().length() > 0) {
                    email.setError("enter the email in correct format");
                } else {
                    error.setText("");
                    User user = new User();
                    user.setUserName(username.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());

                    for (User u : Singleton.getInstance().getUsers()) {
                        if (username.getText().toString().equals(u.getUserName()) && password.getText().toString().equals(u.getPassword())) {
                            error.setText("User Already Present");
                            return;
                        }
                    }
                    Gson gson = new Gson();

                    SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Singleton.getInstance().registerUser(user);
                    String listOfUsers = gson.toJson(Singleton.getInstance().getUsers());
                    editor.putString("userData", listOfUsers).apply();
                    finish();

                }
            }
        });

    }

    Boolean checkemailformat(String email) {
        Boolean flag = false;
        for (int i = 0; i < email.length(); i++) {
            if (flag == false && email.charAt(i) == '@' && email.charAt(i + 1) != '.') {
                flag = true;
            }
            if (flag) {
                if (email.charAt(i) == '.') {
                    if ((i + 3 == email.length() - 1) && (email.charAt(i + 1) == 'c') && (email.charAt(i + 2) == 'o') && (email.charAt(i + 3) == 'm')) {

                    } else return false;
                }
            }
        }
        if (!flag) return false;
        else return true;
    }
}