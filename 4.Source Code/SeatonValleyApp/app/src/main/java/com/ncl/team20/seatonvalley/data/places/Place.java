package com.ncl.team20.seatonvalley.data.places;

import android.support.annotation.Nullable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * Simple POJO object that uses other places POJO objects,in order to be saved as one simple POJO object.
 */

@SuppressWarnings("NullableProblems")
public class Place {

    @Nullable
    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @Nullable
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    @Nullable
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    @Nullable
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}