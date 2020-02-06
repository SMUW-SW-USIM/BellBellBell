package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContactListResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    public List<contactlist> data = new ArrayList();

    public class contactlist{
        @SerializedName("c_name")
        public String c_name;
        @SerializedName("c_number")
        public String c_number;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<contactlist> getData() {
        return data;
    }
}
