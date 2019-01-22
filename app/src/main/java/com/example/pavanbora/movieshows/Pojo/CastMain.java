
package com.example.pavanbora.movieshows.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CastMain {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CastMain() {
    }

    /**
     * 
     * @param id
     * @param cast
     * @param crew
     */
    public CastMain(int id, List<Cast> cast, List<Crew> crew) {
        super();
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CastMain withId(int id) {
        this.id = id;
        return this;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public CastMain withCast(List<Cast> cast) {
        this.cast = cast;
        return this;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public CastMain withCrew(List<Crew> crew) {
        this.crew = crew;
        return this;
    }

}
