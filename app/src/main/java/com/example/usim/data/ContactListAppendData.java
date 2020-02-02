package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class ContactListAppendData {
    @SerializedName("c_name")
    private String c_name;

    @SerializedName("c_number")
    private String c_number;

    public ContactListAppendData(String c_name, String c_number) {
        this.c_name = c_name;
        this.c_number = c_number;
    }
}
