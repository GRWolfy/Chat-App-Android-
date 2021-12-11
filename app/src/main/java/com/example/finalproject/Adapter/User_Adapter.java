package com.example.finalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.MessageActivity;
import com.example.finalproject.Models.Chat;
import com.example.finalproject.Models.User;
import com.example.finalproject.R;
import com.example.finalproject.ViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class User_Adapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final List<User> users;
    private  boolean isChat;

    String theLastmsg;

    public User_Adapter(Context context, List<User> users, boolean isChat){
        this.context = context;
        this.users = users;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.username.setText(user.getUsername());

        if(user.getImageURL().equals("default")){
            holder.profileimage.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(context).load(user.getImageURL()).into(holder.profileimage);
        }

        if(isChat){
            lastMessage(user.getId(),holder.lastmsg);
        }
        else {
            holder.lastmsg.setVisibility(View.GONE);
        }

        if(isChat){
            if(user.getStatus().equals("online")){
                holder.online.setVisibility(View.VISIBLE);
                holder.offline.setVisibility(View.GONE);
            }
            else{
                holder.online.setVisibility(View.GONE);
                holder.offline.setVisibility(View.VISIBLE);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private void lastMessage(String userid, TextView lastmsg){
        theLastmsg = "default";
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);

                    if(chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())){
                        theLastmsg = chat.getMessage();
                    }
                }

                switch (theLastmsg){
                    case "default":
                        lastmsg.setText("No message");
                        break;

                    default:
                        lastmsg.setText(theLastmsg);
                        break;
                }

                theLastmsg = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
