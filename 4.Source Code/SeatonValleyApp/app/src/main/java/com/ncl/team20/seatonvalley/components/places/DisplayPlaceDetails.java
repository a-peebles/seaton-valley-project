package com.ncl.team20.seatonvalley.components.places;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ncl.team20.seatonvalley.components.basic.ConnectionDetector;
import com.ncl.team20.seatonvalley.R;
import com.ncl.team20.seatonvalley.components.basic.Connection;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * Last Edit: 28/03/2018 by Stelios Ioannou
 * <p>
 * This class extends the Connection abstract class.
 * It's used to open in Google Map View the location of the place.
 */
@SuppressWarnings("ConstantConditions")
public class DisplayPlaceDetails extends Connection implements OnMapReadyCallback {

    MapView mapView; //MapView to create the new map.
    private int currentPosition; //Current Position of the Place in the List.
    private String title; //Current Title of the Place

    //Creates a ConnectionDetector to determine connectivity.
    private final ConnectionDetector detector = new ConnectionDetector(DisplayPlaceDetails.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        setContentView(R.layout.activity_place_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        //noinspection ConstantConditions
        //Gets the current place information.
        //noinspection ConstantConditions
        currentPosition = i.getExtras().getInt("itemPosition");
        title = DisplayPlacesList.mListPlace.getResults().get(currentPosition).getName();
        //Sets the title of the activity to the place name.
        setTitle(title);

        //Attaches the Navigation Slider Bar.
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Delay to open the map to make the UI experience feel faster by 1s, otherwise it will take time to open the place.
        new Handler().postDelayed(() -> {
            MapView map_view = findViewById(R.id.map_place);
            map_view.onCreate(null);
            map_view.onResume();
            map_view.getMapAsync(DisplayPlaceDetails.this);
        }, 100);
    }

    /**
     * Creates the Google Map View for the specified place at the currentPosition
     *
     * @param googleMap object to create the map.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // Creates a Camera Update
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DisplayPlacesList.mListPlace.getResults().get(currentPosition).getGeometry().getLocation().getLatLng(), 18);
        //Moves to the Places view.
        googleMap.moveCamera(cameraUpdate);

        //Custom Color for the Marker
        final float marker_color = 338.0F;

        //Adds the Marker.
        googleMap.addMarker(new MarkerOptions()
                .position(DisplayPlacesList.mListPlace.getResults().get(currentPosition).getGeometry().getLocation().getLatLng())
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(marker_color))
        );
    }


    //Ovverides the onBackPressed that was defined in components,because this back button is not going directly on the home screen.
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    //Enables Back-button,implements the correct implementation that is needed for this method.
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
