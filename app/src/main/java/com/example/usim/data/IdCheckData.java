package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class IdCheckData {
    @SerializedName("u_id")
    String u_id;

    public IdCheckData(String u_id) {
        this.u_id = u_id;
    }
}
