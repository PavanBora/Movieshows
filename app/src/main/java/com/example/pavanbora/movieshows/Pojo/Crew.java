
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crew {

    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_path")
    @Expose
    private Object profilePath;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Crew() {
    }

    /**
     * 
     * @param id
     * @param profilePath
     * @param department
     * @param name
     * @param job
     * @param gender
     * @param creditId
     */
    public Crew(String creditId, String department, int gender, int id, String job, String name, Object profilePath) {
        super();
        this.creditId = creditId;
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profilePath = profilePath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Crew withCreditId(String creditId) {
        this.creditId = creditId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Crew withDepartment(String department) {
        this.department = department;
        return this;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Crew withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Crew withId(int id) {
        this.id = id;
        return this;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Crew withJob(String job) {
        this.job = job;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Crew withName(String name) {
        this.name = name;
        return this;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public Crew withProfilePath(Object profilePath) {
        this.profilePath = profilePath;
        return this;
    }

}
