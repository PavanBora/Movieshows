
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewMain {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List <ReviewResult> results = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReviewMain() {
    }

    /**
     * 
     * @param id
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public ReviewMain(int id, int page, List<ReviewResult> results, int totalPages, int totalResults) {
        super();
        this.id = id;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReviewMain withId(int id) {
        this.id = id;
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ReviewMain withPage(int page) {
        this.page = page;
        return this;
    }

    public List<ReviewResult> getResults() {
        return results;
    }

    public void setResults(List<ReviewResult> results) {
        this.results = results;
    }

    public ReviewMain withResults(List<ReviewResult> results) {
        this.results = results;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ReviewMain withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ReviewMain withTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

}
