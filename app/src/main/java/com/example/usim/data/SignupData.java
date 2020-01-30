package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class SignupData {
    @SerializedName("u_name")
    private String u_name;

    @SerializedName("u_id")
    private String u_id;

    @SerializedName("u_pw")
    private String u_pw;

    public SignupData(String u_name, String u_id, String u_pw) {
        this.u_name = u_name;
        this.u_id = u_id;
        this.u_pw = u_pw;
    }
}
