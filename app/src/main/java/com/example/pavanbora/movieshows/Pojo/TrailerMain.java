
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerMain {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private List<TrailerResult> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrailerMain() {
    }

    /**
     * 
     * @param id
     * @param results
     */
    public TrailerMain(int id, List<TrailerResult> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrailerMain withId(int id) {
        this.id = id;
        return this;
    }

    public List<TrailerResult> getResults() {
        return results;
    }

    public void setResults(List<TrailerResult> results) {
        this.results = results;
    }

    public TrailerMain withResults(List<TrailerResult> results) {
        this.results = results;
        return this;
    }

}
