package com.yomba.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.yomba.R;

public class CommonMethod {

    private static AlertDialog dialog;
    private static AlertDialog mdialog;

    public static void GoFrom(Context sourceActivity, AppCompatActivity destinationActivity){
        Intent intent =new Intent(sourceActivity,destinationActivity.getClass());
        sourceActivity.startActivity(intent);

    }
    public static void AlertMe(Context context, String message,final Runnable runnable,Boolean isCancel){
        dismissAlertDialog();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutInflateView = layoutInflater.inflate(R.layout.custom_toast_layout, null);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(layoutInflateView);
        dialog=builder.create();
        TextView toastmsg;
        Button btn_ok,btn_cancel;
        CardView cancel_card;
        toastmsg = layoutInflateView.findViewById(R.id.toastmsg);
        btn_ok = layoutInflateView.findViewById(R.id.btn_ok);
        btn_cancel = layoutInflateView.findViewById(R.id.btn_cancel);
        cancel_card = layoutInflateView.findViewById(R.id.cancel_card);
        toastmsg.setText(message);
        if(isCancel){
            cancel_card.setVisibility(View.VISIBLE);
        }
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(runnable==null){
                    dialog.dismiss();
                }else {
                    runnable.run();
                    dialog.dismiss();

                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();


    }
    public static void dismissAlertDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
    public static void ManageClue(Context context, String message,final Runnable runnable,Boolean isCancel){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutInflateView = layoutInflater.inflate(R.layout.custom_toast_layout, null);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(layoutInflateView);
        mdialog=builder.create();
        TextView toastmsg;
        Button btn_ok,btn_cancel;
        CardView cancel_card;
        toastmsg = layoutInflateView.findViewById(R.id.toastmsg);
        btn_ok = layoutInflateView.findViewById(R.id.btn_ok);
        btn_cancel = layoutInflateView.findViewById(R.id.btn_cancel);
        cancel_card = layoutInflateView.findViewById(R.id.cancel_card);
        btn_ok.setText(context.getString(R.string.yes));
        btn_cancel.setText(context.getString(R.string.no));
        toastmsg.setText(message);
        if(isCancel){
            cancel_card.setVisibility(View.VISIBLE);
        }
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(runnable==null){
                    mdialog.dismiss();
                }else {
                    runnable.run();
                    mdialog.dismiss();

                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdialog.dismiss();
            }
        });
        mdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mdialog.setCancelable(false);
        mdialog.show();


    }

    public static void HideMyKeyboard(Context context) {
        if(((AppCompatActivity)context).getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) ((AppCompatActivity)context).getSystemService(((Activity)context).INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(((AppCompatActivity)context).getCurrentFocus().getWindowToken(), 0);
        }
    }


    public static boolean checkInerntetConection(AppCompatActivity activity) {
        if (activity != null)
            if (isConnectedWifi(activity) || isConnectedMobile(activity)) {
                return true;
            }
        return false;
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
