package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecordListResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    public List<recordlist> data = new ArrayList();

    public class recordlist{
        @SerializedName("r_name")
        public String r_name;
        @SerializedName("r_info")
        public String r_info;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<recordlist> getData() {
        return data;
    }
}
