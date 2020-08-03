package com.ncl.team20.seatonvalley.components.activities_components;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ncl.team20.seatonvalley.components.basic.ConnectionDetector;
import com.ncl.team20.seatonvalley.adapters.PostsRecyclerViewAdapter;

/**
 * Gets the content of the Events Activity by calling the appropriate methods.
 *
 * @author Stelios Ioannou
 * @since 01/03/2018
 * Edited on: 2/03/2018 by Stelios Ioannou
 */

public class EventsActivityComponent extends GetPosts {

    protected void getContent(@NonNull RecyclerView recyclerView, @NonNull ConnectionDetector detector, Context context, int posts) {
        if (detector.isInternetAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            getEvents(posts);
            adapter = new PostsRecyclerViewAdapter(list, context, 3);
            recyclerView.setAdapter(adapter);
        } else {
            getEvents(posts);
            adapter = new PostsRecyclerViewAdapter(list, context, 3);
            recyclerView.setAdapter(adapter);
            this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    //Gets Posts,With the Category of News
    private void getEvents(int posts) {
        getPosts(posts, "17", this);
    }

}
