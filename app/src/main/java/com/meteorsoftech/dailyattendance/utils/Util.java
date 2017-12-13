package com.meteorsoftech.dailyattendance.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Delete on 7/20/2017.
 */

public class Util  {

    public static boolean isConnectedToInternet(Context context)
    {
         boolean connected= false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED)

        {
            connected = true;
        }
        else
            connected=false;
        return connected;

    }

}
