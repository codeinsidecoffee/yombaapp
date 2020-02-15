package com.yomba.dao;

import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyCode extends AsyncTask<Void,Void,Void> {
    String reg_code;
    private String TAG="VerifyCode";
    OnVerifyCodeListener listener;


    public VerifyCode(String reg_code, OnVerifyCodeListener listener) {
        this.reg_code = reg_code;
        this.listener = listener;
    }

    public interface OnVerifyCodeListener{
        void onVerifyCode(UserInfo userInfo);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.verifyCode(reg_code);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo = response.body();
                Log.e(TAG, "onResponse: Body");
                listener.onVerifyCode(userInfo);
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
