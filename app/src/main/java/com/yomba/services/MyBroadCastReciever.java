package com.yomba.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yomba.activity.PlayActivity;
import com.yomba.model.ClueBean;
import com.yomba.model.Const;

import static com.yomba.utils.CommonMethod.GoFrom;

public class MyBroadCastReciever extends BroadcastReceiver {

    private String TAG="BrodcastReciver";


    public MyBroadCastReciever() {
    }

    ManageNotificationListener listener;
    public  interface ManageNotificationListener{
        void ManageNotification(ClueBean clueBean, String notification_type);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String call_api = intent.getStringExtra("call_api");
        String clue_number = intent.getStringExtra("clue_number");
        String title = intent.getStringExtra("title");
        Log.e(TAG, "onMessageReceived: "+ call_api);
        Log.e(TAG, "onMessageReceived: "+ clue_number);
        if(call_api.equals("open_clue")){

            ClueBean clueBean=new ClueBean();
            if(clue_number.equals("clue1")){

                clueBean.setData(new ClueBean.Data("1",
                        Const.currentTASKInfo.getData().getTaskinfo().getClue1MediaType(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue1Data(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue1(),
                        Const.currentTASKInfo.getData().getTaskinfo().getRemainingClue()));
            }else if(clue_number.equals("clue2")){
                clueBean.setData(new ClueBean.Data("2",
                        Const.currentTASKInfo.getData().getTaskinfo().getClue2MediaType(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue2Data(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue2(),
                        Const.currentTASKInfo.getData().getTaskinfo().getRemainingClue()));
            }else if(clue_number.equals("clue3")){
                clueBean.setData(new ClueBean.Data("3",
                        Const.currentTASKInfo.getData().getTaskinfo().getClue3MediaType(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue3Data(),
                        Const.currentTASKInfo.getData().getTaskinfo().getClue3(),
                        Const.currentTASKInfo.getData().getTaskinfo().getRemainingClue()));
            }
            Log.e(TAG, "onMessageReceived: "+clue_number );
            listener.ManageNotification(clueBean,"open_clue");

        }else if(call_api.equals("task_status")){
            PlayActivity playActivity=new PlayActivity();
            GoFrom(context,playActivity);
            Log.e(TAG, "onReceive: start my game" );


        }else if(call_api.equals("game_completed")){


            listener.ManageNotification(null,"task_status");
        }

        Log.e(TAG, "onMessageReceived: Title"+title);
    }


}
