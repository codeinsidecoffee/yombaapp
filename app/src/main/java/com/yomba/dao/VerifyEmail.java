package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyEmail extends AsyncTask<Void,Void,Void> {

    Context context;
    String email,order_id;
    OnVerifyEmailListener listener;
    String language;
    String TAG="VerifyEmail";

    public VerifyEmail(Context context, String email, String order_id,String language, OnVerifyEmailListener listener) {
        this.context = context;
        this.email = email;
        this.order_id = order_id;
        this.listener = listener;
        this.language=language;
    }

    public  interface  OnVerifyEmailListener{
        void onVerifyEmail(UserInfo userInfo);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.verifyEmail(email,order_id,language);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() == 200) {
                    UserInfo userInfo =response.body();
                    listener.onVerifyEmail(userInfo);

                }else{
                    Log.e(TAG, "onResponse: Something Went Wrong");
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
