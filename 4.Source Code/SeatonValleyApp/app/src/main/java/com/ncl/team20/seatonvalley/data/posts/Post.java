package com.ncl.team20.seatonvalley.data.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * Simple POJO object that uses other POJO objects,in order to be saved as one simple POJO object.
 */
public class Post {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private Title title;

    @SerializedName("excerpt")
    @Expose
    private Exerpt excerpt;
    @SerializedName("link")
    @Expose
    private String link;

    public Integer getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Exerpt getExcerpt() {
        return excerpt;
    }

    public String getLink() {
        return link;
    }

}








