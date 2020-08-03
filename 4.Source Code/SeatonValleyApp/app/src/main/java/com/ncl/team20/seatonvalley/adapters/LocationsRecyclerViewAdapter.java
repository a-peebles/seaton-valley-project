package com.ncl.team20.seatonvalley.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ncl.team20.seatonvalley.components.places.DisplayPlaceDetails;
import com.ncl.team20.seatonvalley.R;
import com.ncl.team20.seatonvalley.data.places.ModelPlace;

import java.util.ArrayList;


/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * Last Edit: 23/03/2018 by Stelios Ioannou
 * <p>
 * This class is used to display the place,and make them clickable to open to their respective gOOGLE mAP.
 */
public class LocationsRecyclerViewAdapter extends RecyclerView.Adapter {

    private final ArrayList<ModelPlace> dataset;

    private final Context context;

    public LocationsRecyclerViewAdapter(ArrayList<ModelPlace> mlist, Context context) {
        this.dataset = mlist;
        this.context = context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        //ImageTypeView Fields
        final TextView name;
        final TextView address;
        final TextView rating;
        final TextView open;
        final CardView card;

        //GoogleMap object to be used for Creating Maps.

        //Current Position of the Card.
        int currentPosition;


        public ImageTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            //Gets the Layout Fields from the itemView
            this.name = itemView.findViewById(R.id.place_name);
            this.address = itemView.findViewById(R.id.place_address);
            this.rating = itemView.findViewById(R.id.place_rating);
            this.card = itemView.findViewById(R.id.card);
            this.open = itemView.findViewById(R.id.place_open_now);
        }

    }

    //Create the ImageTypeView Holder.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_details, parent, false);
        return new ImageTypeViewHolder(viewOne);
    }

    //Displays the place details in the ImageTypeView holder and makes it clickable to open to it's relevant link.
    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final ModelPlace object = dataset.get(position);

        ((ImageTypeViewHolder) holder).name.setText(object.name);
        ((ImageTypeViewHolder) holder).address.setText(object.vicinity);

        if (object.rating == null) {
            ((ImageTypeViewHolder) holder).rating.setText(R.string.rating_not_available);
        } else {
            ((ImageTypeViewHolder) holder).rating.setText(context.getString(R.string.rating) + String.valueOf(object.rating) + context.getString(R.string.max_rating));
        }

        //Checks if it has a value
        if (!object.openWasNull) {
            //If Open show Open,else show Closed.
            if (object.openNow) {
                ((ImageTypeViewHolder) holder).open.setText(R.string.open);
                ((ImageTypeViewHolder) holder).open.setTextColor(Color.parseColor(context.getString(R.string.open_color)));
            } else {
                ((ImageTypeViewHolder) holder).open.setText(R.string.closed);
                ((ImageTypeViewHolder) holder).open.setTextColor(Color.parseColor(context.getString(R.string.closed_color)));
            }
        } else {
            //Else removes the option if it's open or closed.
            ((ImageTypeViewHolder) holder).open.setVisibility(View.GONE);
        }

        //On Card Click - Opens Google Map Link.
        ((ImageTypeViewHolder) holder).card.setOnClickListener(v -> {
            Intent intent = new Intent(context, DisplayPlaceDetails.class);
            intent.putExtra("itemPosition", position);
            context.startActivity(intent);
        });

        //On Name Click - Open Google Map Link.
        ((ImageTypeViewHolder) holder).name.setOnClickListener(v -> {
            Intent intent = new Intent(context, DisplayPlaceDetails.class);
            intent.putExtra("itemPosition", position);
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
