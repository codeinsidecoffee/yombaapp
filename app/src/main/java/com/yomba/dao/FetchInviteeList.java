package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.InviteeListBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchInviteeList extends AsyncTask<Void,Void,Void> {
    Context context;
    String order_id;
    String game_id;
    private String TAG="FetchInviteeList";

    OnFetchInviteeListener listener;

    public FetchInviteeList(Context context, String order_id,String game_id, OnFetchInviteeListener listener) {
        this.context = context;
        this.order_id = order_id;
        this.game_id=game_id;
        this.listener = listener;
    }

    public  interface OnFetchInviteeListener{
        void onFetchInvitee(InviteeListBean inviteeListBean);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<InviteeListBean> call=retroFitService.inviteeList(order_id,game_id);
        call.enqueue(new Callback<InviteeListBean>() {
            @Override
            public void onResponse(Call<InviteeListBean> call, Response<InviteeListBean> response) {
                    if(response.code()==200){
                        InviteeListBean inviteeListBean=response.body();
                        listener.onFetchInvitee(inviteeListBean);
                    }
            }

            @Override
            public void onFailure(Call<InviteeListBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
