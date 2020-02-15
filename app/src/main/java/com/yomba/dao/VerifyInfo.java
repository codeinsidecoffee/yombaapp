package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyInfo extends AsyncTask<Void,Void,Void> {
    Context context;
    String TAG="VerifyInfo";
    String scenario,first_name,last_name,nickname,login_id,order_id;
    OnVerifyInfoListener listener;

    public VerifyInfo(Context context, String scenario, String first_name, String last_name, String nickname, String login_id,String order_id, OnVerifyInfoListener listener) {
        this.context = context;
        this.scenario = scenario;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.login_id = login_id;
        this.listener = listener;
        this.order_id=order_id;
    }

    public  interface OnVerifyInfoListener {
        void onVerifyInfo(UserInfo userInfo);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.verifyInfo(scenario,first_name,last_name,nickname,login_id,order_id);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                if(response.code()==200) {
                    UserInfo userInfo = response.body();
                    listener.onVerifyInfo(userInfo);
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
