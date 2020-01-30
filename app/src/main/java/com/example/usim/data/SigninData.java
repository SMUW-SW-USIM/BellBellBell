package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class SigninData {
    @SerializedName("u_id")
    String u_id;

    @SerializedName("u_pw")
    String u_pw;

    public SigninData(String u_id, String u_pw) {
        this.u_id = u_id;
        this.u_pw = u_pw;
    }
}
