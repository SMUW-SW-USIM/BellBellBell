package com.example.usim.network;

import com.example.usim.data.SigninData;
import com.example.usim.data.SigninResponse;
import com.example.usim.data.SignupData;
import com.example.usim.data.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/users/signin")
    Call<SigninResponse> userLogin(@Body SigninData data);

    @POST("/users/signup")
    Call<SignupResponse> userJoin(@Body SignupData data);
}
