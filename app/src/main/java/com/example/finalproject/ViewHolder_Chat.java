package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_Chat extends RecyclerView.ViewHolder {
    public TextView show_message, txtSeen;
    public ImageView profileimage;

    public ViewHolder_Chat(View itemView){
        super(itemView);

        txtSeen = itemView.findViewById(R.id.txtSeen);
        show_message = itemView.findViewById(R.id.show_message);
        profileimage = itemView.findViewById(R.id.profileimage);
    }
}
