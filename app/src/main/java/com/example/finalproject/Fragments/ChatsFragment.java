package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Adapter.User_Adapter;
import com.example.finalproject.Models.Chat;
import com.example.finalproject.Models.ChatList;
import com.example.finalproject.Models.User;
import com.example.finalproject.Notifications.Token;
import com.example.finalproject.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class    ChatsFragment extends Fragment {
    private RecyclerView recyclerView;
    private User_Adapter adapter;
    private List<User> users;
    private List<ChatList> userList;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userList = new ArrayList<>();
        /*reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat  = dataSnapshot.getValue(Chat.class);

                    if(chat.getSender().equals(firebaseUser.getUid()) && !userList.contains(chat.getReceiver())){
                        userList.add(chat.getReceiver());
                    }

                    if(chat.getReceiver().equals(firebaseUser.getUid()) && !userList.contains(chat.getSender())){
                        userList.add(chat.getSender());
                    }
                }

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        reference = FirebaseDatabase.getInstance().getReference("ChatList").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ChatList chatList  = dataSnapshot.getValue(ChatList.class);
                    userList.add(chatList);
                }

                chatList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateToken(FirebaseMessaging.getInstance().getToken());
        return view;
    }

    /*private void readChats(){
        users = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);

                    //Display 1 user from chats
                    for(String id : userList){
                        if(user.getId().equals(id)){
                            if(users.size() != 0){
                                //for(int i = 0; i < users.size(); i++){
                                    //User userl = users.get(i);
                                for(User userl : users){
                                    if(!user.getId().equals(userl.getId())){
                                        users.add(user);
                                        break;
                                    }
                                }
                            }
                            else{
                                users.add(user);
                                break;
                            }
                        }
                    }
                }

                adapter = new User_Adapter(getContext(), users, true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    private void updateToken(Task<String> token){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(firebaseUser.getUid()).setValue(token1);
    }

    private void chatList(){
        users = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);

                    for(ChatList chatList : userList){
                        if(user.getId().equals(chatList.getId())){
                            users.add(user);
                        }
                    }
                }

                adapter = new User_Adapter(getContext(), users, true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}