package com.yomba.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.yomba.activity.ConnectionLostActivity;
import com.yomba.model.Const;
import com.yomba.utils.CommonMethod;

import static com.yomba.utils.CommonMethod.GoFrom;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!ServiceManager.isAppIsInBackground(context)) {
            if (isConnectedWifi(context) || isConnectedMobile(context)) {
                if(Const.InternetNotFound)
                {
                    Const.InternetNotFound=false;
                    ((ConnectionLostActivity) context).finish();

                }

            }else{
                Log.e("nimesh", "onReceive: Internet is off" );
                ConnectionLostActivity connectionLostActivity=new ConnectionLostActivity();
                GoFrom(context,connectionLostActivity);

            }
            ////Toast.ma(AdsService.this, "Service is running", Toast.LENGTH_SHORT).show();
        }


    }
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnectedOrConnecting() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnectedOrConnecting() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}