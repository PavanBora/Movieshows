
package com.example.pavanbora.movieshows.Pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingMainPojo {

    @SerializedName("results")
    @Expose
    private List<TopResult> results = null;
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
    public UpcomingMainPojo() {
    }

    /**
     * 
     * @param results
     * @param dates
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public UpcomingMainPojo(List<TopResult> results, int page, int totalResults, Dates dates, int totalPages) {
        super();
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.dates = dates;
        this.totalPages = totalPages;
    }

    public List<TopResult> getResults() {
        return results;
    }

    public void setResults(List<TopResult> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
