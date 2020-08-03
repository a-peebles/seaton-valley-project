package com.ncl.team20.seatonvalley.data.places;

import android.support.annotation.Nullable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * <p>
 * Simple POJO object to  be used to store the OpeningHours attributes of a place.
 */
@SuppressWarnings("NullableProblems")
public class OpeningHours {

    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @Nullable
    @SerializedName("weekday_text")
    @Expose
    private List<Object> weekdayText = null;

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    @Nullable
    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

}