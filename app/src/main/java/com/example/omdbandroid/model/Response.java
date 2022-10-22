package com.example.omdbandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response {
    @SerializedName("results")
    @Expose
    private List<Result> results ;

    public List<Result> getResults() {
        return results;
    }

}
