package com.yomba.dao;



import com.yomba.model.Const;
import com.yomba.utils.HttpClientService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetroFitService getAppData(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
//                .client(HttpClientService.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroFitService retroFitService= retrofit.create(RetroFitService.class);


        return retroFitService;
    }


}
