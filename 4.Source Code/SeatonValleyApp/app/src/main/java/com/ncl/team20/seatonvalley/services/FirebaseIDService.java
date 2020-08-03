package com.ncl.team20.seatonvalley.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * This class is used to send the Registration Token to the Firebase console.
 *
 * @author Stelios Ioannou
 * @since 20/02/2018
 * Last Edit: 02/03/2018 by Stelios Ioannou
 */
@SuppressWarnings("EmptyMethod")
public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer();
    }

    //Extra functionality to be implemented if needed in the near future.
    @SuppressWarnings("EmptyMethod")
    private void sendRegistrationToServer() {
        // Add custom implementation, as needed.
    }

}
