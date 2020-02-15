package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.CountryBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchCountryList extends AsyncTask<Void,Void,Void> {
    Context context;
    String login_id;
String TAG="CountryList";
    OnFetchCountryListener listener;


    public FetchCountryList(Context context, String login_id, OnFetchCountryListener listener) {
        this.context = context;
        this.login_id = login_id;
        this.listener = listener;
    }

    public  interface OnFetchCountryListener{
        void onFetchCountry(CountryBean countryBean);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService=RetrofitClient.getAppData();
        Call<CountryBean> call=retroFitService.countryList(login_id);
        call.enqueue(new Callback<CountryBean>() {
            @Override
            public void onResponse(Call<CountryBean> call, Response<CountryBean> response) {

                if(response.code()==200){
                    CountryBean countryBean=response.body();
                    listener.onFetchCountry(countryBean);
                }else{
                    Log.e(TAG, "onResponse: Not Found" );
                }
            }

            @Override
            public void onFailure(Call<CountryBean> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
        return null;
    }
}
