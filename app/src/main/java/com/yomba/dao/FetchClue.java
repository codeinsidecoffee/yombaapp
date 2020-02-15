package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.ClueBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchClue extends AsyncTask<Void,Void,Void> {
    Context context;
    String  login_id,user_id,play_id,task_id,scenario;
    String TAG="ClueInfo";

    OnFetchClueListener listener;
    public FetchClue(Context context, String login_id, String user_id, String play_id, String task_id, String scenario,OnFetchClueListener listener) {
        this.context = context;
        this.login_id = login_id;
        this.user_id = user_id;
        this.play_id = play_id;
        this.task_id = task_id;
        this.scenario = scenario;
        this.listener=listener;
    }

    public  interface  OnFetchClueListener{
        void onFetchClueClick(ClueBean clueBean);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<ClueBean> call=retroFitService.fetchClue(login_id,user_id,play_id,task_id,scenario);
        call.enqueue(new Callback<ClueBean>() {
            @Override
            public void onResponse(Call<ClueBean> call, Response<ClueBean> response) {
                if(response.code()==200){
                    ClueBean clueBean=response.body();
                    listener.onFetchClueClick(clueBean);
                }else{
                    Log.e(TAG, "onResponse: "+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ClueBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
