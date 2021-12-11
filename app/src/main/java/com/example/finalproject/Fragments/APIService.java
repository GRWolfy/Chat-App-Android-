package com.example.finalproject.Fragments;

import com.example.finalproject.Notifications.MyResponse;
import com.example.finalproject.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorizatoin:key=AAAA3sqqXG8:APA91bFcR1Am_A68TNyEX5SmbH6f79mFqJIyvh8urzLYOwQV6m-12BBK0A5_mgpdL2se_FCK61jXOrAQV1nMreYV6mUTtEgC0jmBnEH6KELvODOm_vds-8LMq3oGpEOHpksqp2AoMCHU"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
