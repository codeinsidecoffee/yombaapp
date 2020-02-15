package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutGame extends AsyncTask<Void,Void,Void> {
    Context context;
    String scenario,order_id,login_id;

    OnLogoutGameListener listener;
    String TAG="LogoutGame";

    public LogoutGame(Context context, String scenario, String order_id, String login_id,OnLogoutGameListener listener) {
        this.context = context;
        this.scenario = scenario;
        this.order_id = order_id;
        this.login_id = login_id;
        this.listener = listener;
    }

    public interface OnLogoutGameListener{
        void onLogoutGame(UserInfo logoutGame);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<UserInfo> call=retroFitService.logoutGame(scenario,order_id,login_id);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code()==200) {
                    UserInfo logoutGame = response.body();
                    listener.onLogoutGame(logoutGame);
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
