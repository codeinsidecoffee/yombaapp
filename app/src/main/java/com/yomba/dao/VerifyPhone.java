package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhone extends AsyncTask<Void,Void,Void> {
    Context context;
    String phone,scenario,login_id,order_id;
    OnVerifyPhoneListener listener;
    String TAG="VerifyPhone";

    public VerifyPhone(Context context, String phone, String scenario, String login_id,String order_id, OnVerifyPhoneListener listener) {
        this.context = context;
        this.phone = phone;
        this.scenario = scenario;
        this.login_id = login_id;
        this.listener = listener;
        this.order_id=order_id;
    }

    public  interface OnVerifyPhoneListener{
        void onVerifyPhone(UserInfo userInfo);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.verifyPhone(phone,scenario,login_id,order_id);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code()==200){
                    UserInfo userInfo = response.body();
                    listener.onVerifyPhone(userInfo);
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
