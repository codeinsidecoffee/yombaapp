package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.activity.SummeryActivity;
import com.yomba.model.Const;
import com.yomba.model.SummaryBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchSummary extends AsyncTask<Void,Void,Void> {
    Context context;
    String playid,userid,scenario;
    OnFetchSummaryListener listener;
    private  String TAG="FetchSummary";

    public FetchSummary(Context context, String playid, String userid, String scenario, OnFetchSummaryListener listener) {
        this.context = context;
        this.playid = playid;
        this.userid = userid;
        this.scenario = scenario;
        this.listener = listener;
    }

    public interface OnFetchSummaryListener{
         void onFetchSummary(SummaryBean summaryBean);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<SummaryBean> call=retroFitService.fetchSummary(playid,userid,scenario);
        call.enqueue(new Callback<SummaryBean>() {
            @Override
            public void onResponse(Call<SummaryBean> call, Response<SummaryBean> response) {
                if(response.code()==200) {
                    SummaryBean summaryBean = response.body();
                    listener.onFetchSummary(summaryBean);
                }

            }

            @Override
            public void onFailure(Call<SummaryBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
