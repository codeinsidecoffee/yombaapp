package com.yomba.services;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yomba.model.Const;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG="FirebaseToken";
   // private MyBroadCastReciever broadCastReciever;
   private LocalBroadcastManager broadcaster;




    @Override
    public void onCreate() {
//        broadCastReciever=new MyBroadCastReciever();
        broadcaster = LocalBroadcastManager.getInstance(this);

    }


    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);
        Const.FireBaseTokenID=token;
        Log.e(TAG, "onComplete:Service "+Const.FireBaseTokenID );
    }

    public MyFirebaseMessagingService() {


        getFireBaseID();

    }

    public static void getFireBaseID() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()){
                            Log.w(TAG, "getInstance ID Failed "+task.getException() );
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.e(TAG, "onComplete:Service Method "+token );

                    }
                });


    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String call_api=remoteMessage.getData().get("callapi");
        String play_id=remoteMessage.getData().get("play_id");
        String clue_number=remoteMessage.getData().get("clue_number");
        String title=remoteMessage.getData().toString();
        Log.e(TAG, "onMessageReceived: callapi "+call_api );
        Log.e(TAG, "onMessageReceived: play_id "+play_id );
        Log.e(TAG, "onMessageReceived: clue number "+clue_number );
        Log.e(TAG, "onMessageReceived: title "+title );


        Intent intent= new Intent("remoteMessage");
        intent.putExtra("call_api", call_api);
        intent.putExtra("play_id", play_id);
        intent.putExtra("clue_number", clue_number);
        intent.putExtra("title", title);
        broadcaster.sendBroadcast(intent);

    }



}
