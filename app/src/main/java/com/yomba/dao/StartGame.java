package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.TaskInfoBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartGame extends AsyncTask<Void,Void,Void> {
    Context context;
    String login_id;
    String user_id;
    String game_id;
    String player_ids;
    String TAG="StartGame";

    OnStartGameListener listener;
    public StartGame(Context context, String login_id, String user_id, String game_id, String player_ids,OnStartGameListener listener) {
        this.context = context;
        this.login_id = login_id;
        this.user_id = user_id;
        this.game_id = game_id;
        this.player_ids = player_ids;
        this.listener=listener;
    }

    public  interface OnStartGameListener{
        void onStartGameClick(TaskInfoBean taskInfoBean);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<TaskInfoBean>  call=retroFitService.startGame(login_id,user_id,game_id,player_ids);
        call.enqueue(new Callback<TaskInfoBean>() {
            @Override
            public void onResponse(Call<TaskInfoBean> call, Response<TaskInfoBean> response) {
                if(response.code()==200){
                        TaskInfoBean taskInfoBean=response.body();
                        listener.onStartGameClick(taskInfoBean);
                }else{
                    Log.e(TAG, "onResponse: "+response.toString() );
                }
            }

            @Override
            public void onFailure(Call<TaskInfoBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
