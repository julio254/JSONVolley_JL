package com.example.jsonvolley_jl;

import com.google.gson.annotations.SerializedName;

// Class that sets all the objects in my julio_json_url
public class FrenchHorns {
    @SerializedName("maker") public String hornMaker;

    @SerializedName("wrap") public String hornWrap;

    @SerializedName("year") public int hornYear;

    @SerializedName("model") public String hornModel;

    @SerializedName("description") public String hornDescription;

    public FrenchHorns(String maker, String wrap, int year, String model, String description) {
        hornMaker = maker;
        hornWrap = wrap;
        hornYear = year;
        hornModel = model;
        hornDescription = description;
    }
}
