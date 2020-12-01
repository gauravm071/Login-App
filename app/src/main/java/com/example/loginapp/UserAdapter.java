package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter <UserAdapter.ViewHolder> {

    ArrayList<User>listOfUsers;

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.users,parent,false);
        return new ViewHolder(view);
    }

    public UserAdapter(ArrayList<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user= listOfUsers.get(position);
        holder.username.setText(user.getUserName());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return listOfUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.tvUserName);
            email= itemView.findViewById(R.id.tvEmail);
        }
    }
}
