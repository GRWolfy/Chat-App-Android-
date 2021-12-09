package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView username;
    public ImageView profileimage;
    public ImageView online, offline;

    public ViewHolder(View itemView){
        super(itemView);

        username = itemView.findViewById(R.id.username);
        profileimage = itemView.findViewById(R.id.profileimage);
        online = itemView.findViewById(R.id.online);
        offline = itemView.findViewById(R.id.offline);
    }
}