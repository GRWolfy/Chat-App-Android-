package com.example.finalproject.DAO;

import android.content.Intent;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import com.example.finalproject.MainActivity;
import com.example.finalproject.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;

public class Users_DAO {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference;
    

}
