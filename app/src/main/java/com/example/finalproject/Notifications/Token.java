package com.example.finalproject.Notifications;

import com.google.android.gms.tasks.Task;

public class Token {
    private  String token;

    public Token(Task<String> refreshToken) {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
