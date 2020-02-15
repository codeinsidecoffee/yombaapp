package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.TaskInfoBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskStatus extends AsyncTask<Void,Void,Void> {
    Context context;
    String login_id,user_id,game_id,play_id,scenario;
    OnTaskStatusListener listener;
    String TAG="TaskStatus";

    public TaskStatus(Context context, String login_id, String user_id, String game_id, String play_id, String scenario, OnTaskStatusListener listener) {
        this.context = context;
        this.login_id = login_id;
        this.user_id = user_id;
        this.game_id = game_id;
        this.play_id = play_id;
        this.scenario = scenario;
        this.listener = listener;
    }

    public  interface OnTaskStatusListener{
        void onTaskStatus(TaskInfoBean taskInfoBean);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<TaskInfoBean> call=retroFitService.taskStatus(login_id,user_id,game_id,play_id,scenario);
        call.enqueue(new Callback<TaskInfoBean>() {
            @Override
            public void onResponse(Call<TaskInfoBean> call, Response<TaskInfoBean> response) {
                if(response.code()==200){
                    TaskInfoBean taskInfoBean=response.body();
                    listener.onTaskStatus(taskInfoBean);
                }else{
                    Log.e(TAG, "onResponse: "+response.body() );
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
