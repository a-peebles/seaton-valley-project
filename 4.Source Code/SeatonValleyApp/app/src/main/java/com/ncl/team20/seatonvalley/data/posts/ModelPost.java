package com.ncl.team20.seatonvalley.data.posts;


import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * This is class is used to store the post details.
 */

public class ModelPost {

    public final String title;
    public final String description;
    private final int id;

    public ModelPost(String mtitle, String mdescription, int mid) {

        this.title = mtitle;
        this.description = mdescription;
        this.id = mid;
    }

    //Returns the id of the post
    public int getId() {
        return id;
    }

    //Gets the Posts
    public interface RetrofitArrayApi {
        @NonNull
        @GET
        Call<List<Post>> getPostInfo(@Url String url);
    }

    //Overrides the equals method in order to compare posts.
    //Post id is unique. Used by list.contains to avoid duplicates.
    @Override
    public boolean equals(@NonNull Object obj) {
        //They are equal if they have the same post id.
        if (this.getClass() == obj.getClass()) {
            ModelPost other = (ModelPost) obj;
            if (this.id == other.id) {
                return true;
            }
        }
        return false;
    }
}

