package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    TextView username,email;
    Menu logout;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView= findViewById(R.id.recycler_view);
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        String userData= sharedPreferences.getString("userData","Nothing");
        if(userData.equals("Nothing")){
            Log.v("SharedPrefernce","No Data available");
        }
        else{
            Gson gson= new Gson();
            Type type= new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> listOfUsers=gson.fromJson(userData,type);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new UserAdapter(listOfUsers));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menulogout){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}