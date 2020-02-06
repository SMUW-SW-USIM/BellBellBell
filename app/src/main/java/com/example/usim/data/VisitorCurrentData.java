package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class VisitorCurrentData {
    @SerializedName("v_faceId")
    private String v_faceId;


    public VisitorCurrentData(String v_faceId) {
        this.v_faceId = v_faceId;
    }
}
