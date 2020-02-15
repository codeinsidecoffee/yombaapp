package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenSolve extends AsyncTask<Void,Void,Void> {
    Context context;
    String play_id,task_id;
    OnOpenSolveListener listener;
    private  String TAG="openSolve";

    public OpenSolve(Context context, String play_id, String task_id, OnOpenSolveListener listener) {
        this.context = context;
        this.play_id = play_id;
        this.task_id = task_id;
        this.listener = listener;
    }

    public  interface OnOpenSolveListener{
        void onOpenSolve(UserInfo currentUserInfo);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.openSolve(play_id,task_id);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code()==200) {
                    UserInfo currentUserInfo = response.body();
                    listener.onOpenSolve(currentUserInfo);
                }else{
                    Log.e(TAG, "onResponse: "+response.toString() );
                }

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
