package com.example.loginapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login;
    TextView clickReg,incorrectmessage;
    int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName= findViewById(R.id.etUserName);
        password= findViewById(R.id.etPassword);
        login= findViewById(R.id.login);
        clickReg= findViewById(R.id.tvclickReg);
        incorrectmessage= findViewById(R.id.incorrectmessage);

        loaddata();
        clickReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, RegisterActivity.class);
                userName.setText("");
                password.setText("");
                incorrectmessage.setText("");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String myUserName= userName.getText().toString();
                String myPassword= password.getText().toString();
                if(myUserName.length()==0 || myPassword.length()==0){
                    incorrectmessage.setText("Please enter username and Password to Login");
                }
                else{
                    Boolean found=false;
                    ArrayList<User> usersList= Singleton.getInstance().getUsers();
                    User foundUser=new User();
                    for(User curUser: usersList){
                        if(userName.getText().toString().equals(curUser.userName) && (password.getText().toString().trim().equals(curUser.password))){
                            foundUser=curUser;
                            found=true;
                            break;
                        }
                    }
                    if(found){
                        Intent intent= new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }
                    else{
                        incorrectmessage.setText("Incorrect username or password");
                    }
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            if(data==null){
                Log.v("Userdata","NULL");
            }
            else{
                String user= data.getStringExtra("user");
                Gson gson= new Gson();
                User curUser= gson.fromJson(user,User.class);
                SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Singleton.getInstance().registerUser(curUser);
                String listOfUsers= gson.toJson(Singleton.getInstance().getUsers());
                editor.putString("userData",listOfUsers).apply();
            }
        }
    }

    void loaddata(){
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        String userData= sharedPreferences.getString("userData","Nothing");
        if(userData!="Nothing"){
            Gson gson= new Gson();
            Type type= new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> listOfUsers=gson.fromJson(userData,type);
            for(User user: listOfUsers){
                Singleton.getInstance().registerUser(user);
            }
        }
        else{
            Log.v("Userdata","No User Found");
        }
    }
}