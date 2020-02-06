package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VisitorCurrentResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    public List<visitorcurrentlist> data = new ArrayList();

    public class visitorcurrentlist{
        @SerializedName("v_name")
        public String v_name;
        @SerializedName("v_gender")
        public Integer v_gender;
        @SerializedName("v_times")
        public Integer v_times;
        @SerializedName("v_age")
        public Integer v_age;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<visitorcurrentlist> getData() {
        return data;
    }
}
