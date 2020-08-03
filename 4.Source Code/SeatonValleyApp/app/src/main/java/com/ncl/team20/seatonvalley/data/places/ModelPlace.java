package com.ncl.team20.seatonvalley.data.places;


import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * This is class is used to store the place details.
 */
public class ModelPlace {

    public final String name;
    public final String vicinity; //address
    public final Double rating;
    public final Boolean openNow;
    public final Boolean openWasNull;

    public ModelPlace(String mname, String mvicinity, Double mrating, Boolean mopenNow, Boolean mopenWasNull, LatLng mlocation) {
        this.name = mname;
        this.vicinity = mvicinity;
        this.rating = mrating;
        this.openNow = mopenNow;
        this.openWasNull = mopenWasNull;
    }

    //Gets the Locations
    public interface RetrofitArrayApi {
        @NonNull
        @GET
        Call<Place> getPostInfo(@Url String url);
    }

}

