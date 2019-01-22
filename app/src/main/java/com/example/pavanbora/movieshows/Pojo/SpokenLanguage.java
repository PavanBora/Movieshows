
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {

    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SpokenLanguage() {
    }

    /**
     * 
     * @param iso6391
     * @param name
     */
    public SpokenLanguage(String iso6391, String name) {
        super();
        this.iso6391 = iso6391;
        this.name = name;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public SpokenLanguage withIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpokenLanguage withName(String name) {
        this.name = name;
        return this;
    }

}
