package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class RecordListAppendData {
    @SerializedName("r_name")
    private String r_name;

    @SerializedName("r_info")
    private String r_info;

    public RecordListAppendData(String r_name, String r_info) {
        this.r_name = r_name;
        this.r_info = r_info;
    }
}
