package com.ncl.team20.seatonvalley.components.mail;


/*
  @author Stelios Ioannou,Olivija Guzelyte
 * @since 20/02/2018
 * Last edited : 22/03/2018 by Stelios Ioannou
 * <p>
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

//Class used to send mails.

@SuppressLint("StaticFieldLeak")
public class SendMail extends AsyncTask {

    private ProgressDialog statusDialog;
    private final Activity sendMailActivity;

    public SendMail(Activity activity) {
        sendMailActivity = activity;
    }

    //Before Executing - Displays the relevant dialogs.
    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    //Sending the mail.
    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    protected Object doInBackground(Object... args) {
        try {
            Log.i("SendMail", "About to instantiate Mail...");
            //noinspection unchecked
            publishProgress("Processing input....");
            Mail androidEmail = new Mail(args[0].toString(),
                    args[1].toString(), args[2].toString(), args[3].toString(),
                    args[4].toString());
            //noinspection unchecked
            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            //noinspection unchecked
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Sent.");
        } catch (Exception e) {
            //noinspection unchecked
            publishProgress(e.getMessage());
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }


    //After successful execution,display a confirmation message and then after 2 second it dismisses it and goes back to Main Activity. 
    @Override
    public void onPostExecute(Object result) {
        statusDialog.setMessage("Mail sent.");
        Log.i("SendMail", "Mail Sent.");
        new Handler().postDelayed(() -> {
            statusDialog.dismiss();
            sendMailActivity.finish();
        }, 250);
    }

}