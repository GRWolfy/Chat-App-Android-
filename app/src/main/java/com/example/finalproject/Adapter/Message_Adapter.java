package com.example.finalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.MessageActivity;
import com.example.finalproject.Models.Chat;
import com.example.finalproject.Models.User;
import com.example.finalproject.R;
import com.example.finalproject.ViewHolder_Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Message_Adapter extends RecyclerView.Adapter<ViewHolder_Chat> {
    public static final int  MSG_TYPE_LEFT = 0;
    public static final int  MSG_TYPE_RIGHT = 1;
    private Context context;
    private List<Chat> chats;
    private String imageurl;

    FirebaseUser firebaseUser;

    public Message_Adapter(Context context, List<Chat> chats, String imageurl){
        this.context = context;
        this.chats = chats;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public ViewHolder_Chat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
            return new ViewHolder_Chat(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            return new ViewHolder_Chat(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Chat holder, int position) {
        Chat chat = chats.get(position);
        holder.show_message.setText(chat.getMessage());

        if(imageurl.equals("default")){
            holder.profileimage.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(context).load(imageurl).into(holder.profileimage);
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public int getItemView(int position){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(chats.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        }
        else{
            return MSG_TYPE_LEFT;
        }
    }
}
