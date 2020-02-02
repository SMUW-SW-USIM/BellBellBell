package com.example.usim.data;

import com.google.gson.annotations.SerializedName;

public class VisitorListAppendData {
    @SerializedName("v_name")
    private String v_name;

    @SerializedName("v_gender")
    private Integer v_gender;

    @SerializedName("v_age")
    private Integer v_age;

    @SerializedName("v_times")
    private Integer v_times;


    public VisitorListAppendData(String v_name, Integer v_gender, Integer v_age, Integer v_times) {
        this.v_name = v_name;
        this.v_gender = v_gender;
        this.v_age = v_age;
        this.v_times = v_times;
    }

}
