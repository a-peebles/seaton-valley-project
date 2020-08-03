package com.ncl.team20.seatonvalley.components.basic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ncl.team20.seatonvalley.R;

/**
 * @author Stelios Ioannou
 * @since 20/02/2018
 * Last Edit: 02/03/2018 by Stelios Ioannou
 * <p>
 * This abstract class is used as an Extension to other classes when there is a change in Connection.
 * Displays the relevant message in each scenario in the Broadcast Receiver.
 * This class extends the Drawer abstract class.
 */
public abstract class Connection extends Drawer {

    @Nullable
    protected final BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo currentNetworkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
            if (currentNetworkInfo != null && currentNetworkInfo.isConnectedOrConnecting()) {
                recreate();
                Toast.makeText(getApplicationContext(), R.string.on_connection, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.on_no_connection,
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("");
        this.registerReceiver(mConnReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(mConnReceiver);
    }

}
