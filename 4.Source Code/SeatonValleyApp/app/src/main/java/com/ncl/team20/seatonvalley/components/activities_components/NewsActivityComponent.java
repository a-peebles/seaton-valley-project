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
 * Gets the content of the News Activity by calling the appropriate methods.
 * Extends the New
 *
 * @author Stelios Ioannou
 * @since 01/03/2018
 * Edited on: 2/03/2018 by Stelios Ioannou
 */

public class NewsActivityComponent extends GetPosts {

    protected void getContent(@NonNull RecyclerView recyclerView, @NonNull ConnectionDetector detector, Context context, int posts) {
        if (detector.isInternetAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            getNews(posts);
            adapter = new PostsRecyclerViewAdapter(list, context, 2);
            recyclerView.setAdapter(adapter);
        } else {
            getNews(posts);
            adapter = new PostsRecyclerViewAdapter(list, context, 2);
            recyclerView.setAdapter(adapter);
            this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    //Gets Posts,With the Category of News
    private void getNews(int posts) {
        getPosts(posts, "16", this);
    }



}
