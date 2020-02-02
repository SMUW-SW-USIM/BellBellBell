package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VisitorListResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    public List<visitorlist> data = new ArrayList();

    public class visitorlist{
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

    public List<visitorlist> getData() {
        return data;
    }
}
