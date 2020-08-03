package com.ncl.team20.seatonvalley.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ncl.team20.seatonvalley.components.basic.ConnectionDetector;
import com.ncl.team20.seatonvalley.R;
import com.ncl.team20.seatonvalley.components.activities_components.EventsActivityComponent;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * Last Edit: 02/03/2018 by Stelios Ioannou
 * <p>
 * This class is used to display events and extends EventsActivityComponent to do so.
 */
public class EventsActivity extends EventsActivityComponent {

    //Creates a detector object.
    private final ConnectionDetector detector = new ConnectionDetector(EventsActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Custom Animation on Create
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

        setContentView(R.layout.activity_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.events_title);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_events);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_events);
        progressBar = findViewById(R.id.progress_bar_events);
        progressBar.setVisibility(View.GONE);

        int posts = PreferenceManager.getDefaultSharedPreferences(EventsActivity.this)
                .getInt("events-posts", 10);

        //Gets the latest content of Events.
        getContent(recyclerView, detector, this,posts);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(EventsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    //Ensures that on resume,that the correct element is selected.
    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_events);
    }


}
