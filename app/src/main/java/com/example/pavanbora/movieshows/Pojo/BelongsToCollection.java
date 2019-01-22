
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BelongsToCollection {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BelongsToCollection() {
    }

    /**
     * 
     * @param id
     * @param posterPath
     * @param name
     * @param backdropPath
     */
    public BelongsToCollection(int id, String name, String posterPath, String backdropPath) {
        super();
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BelongsToCollection withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BelongsToCollection withName(String name) {
        this.name = name;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public BelongsToCollection withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

}
