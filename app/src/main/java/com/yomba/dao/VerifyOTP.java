package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.Const;
import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTP extends AsyncTask<Void,Void,Void> {
    Context context;
    String verification_type,verification_code,scenario,login_id,device_token,order_id;

    OnVerifyOTPListener listener;
    String TAG="VerifyOTP";

    public interface OnVerifyOTPListener{
        void onVerifyOTP(UserInfo userInfo);
    }

    public VerifyOTP(Context context, String verification_type, String verification_code, String scenario,String login_id,String order_id, OnVerifyOTPListener listener) {
        this.context = context;
        this.verification_type = verification_type;
        this.verification_code = verification_code;
        this.scenario = scenario;
        this.login_id = login_id;
        this.listener = listener;
        this.order_id=order_id;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.verifyOTPCode(verification_type,verification_code,scenario,login_id,order_id,Const.FireBaseTokenID);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code()==200){
                    UserInfo userInfo = response.body();
                    listener.onVerifyOTP(userInfo);
                }else{
                    Log.e(TAG, "onResponse: Something Went Wrong" );
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
