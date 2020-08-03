package com.ncl.team20.seatonvalley.data.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * Simple POJO object to be used to save the post description.
 */
public class Exerpt {

    @SerializedName("rendered")
    @Expose
    private String rendered;

    public String getRendered() {
        return rendered;
    }

}