
package com.example.pavanbora.movieshows.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingMain {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NowPlayingMain() {
    }

    /**
     * 
     * @param results
     * @param dates
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public NowPlayingMain(List<Result> results, int page, int totalResults, Dates dates, int totalPages) {
        super();
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.dates = dates;
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public NowPlayingMain withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public NowPlayingMain withPage(int page) {
        this.page = page;
        return this;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public NowPlayingMain withTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public NowPlayingMain withDates(Dates dates) {
        this.dates = dates;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public NowPlayingMain withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

}
