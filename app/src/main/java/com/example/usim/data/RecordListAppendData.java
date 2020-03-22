package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class RecordListAppendData {
    @SerializedName("r_name")
    private String r_name;

//    @SerializedName("records")
//    private String records;
//
//    public RecordListAppendData(String r_name, String records) {
//        this.r_name = r_name;
//        this.records = records;
//    }
        public RecordListAppendData(String r_name) {
        this.r_name = r_name;
    }
}
