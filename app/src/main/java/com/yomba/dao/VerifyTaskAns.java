package com.yomba.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yomba.model.Const;
import com.yomba.model.TaskInfoBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyTaskAns extends AsyncTask<Void, Void, Void> {
    Context context;
    String TAG = "VerifyTaskAns";
    String login_id, user_id, play_id, task_id, scenario, answer, skip, answer_type;
    OnVerifyTaskAnsListener listener;

    public VerifyTaskAns(Context context, String login_id, String user_id, String play_id, String task_id, String scenario, String answer, String skip, String answer_type, OnVerifyTaskAnsListener listener) {
        this.context = context;
        this.login_id = login_id;
        this.user_id = user_id;
        this.play_id = play_id;
        this.task_id = task_id;
        this.scenario = scenario;
        this.answer = answer;
        this.skip = skip;
        this.answer_type = answer_type;
        this.listener = listener;
    }

    public interface OnVerifyTaskAnsListener {
        void OnVerifyTaskAns(TaskInfoBean taskInfoBean);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RetroFitService retroFitService = RetrofitClient.getAppData();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (answer_type.equals("1")) {
            builder.addFormDataPart(Const.ANSWER, answer);
        } else {
            try {
                builder.addFormDataPart(Const.ANSWER, Const.SelectedFileName, RequestBody.create(MultipartBody.FORM, Const.BOS.toByteArray()));
            }catch (Exception e){
                Log.e(TAG, "doInBackground: "+e.getMessage() );
            }

        }
        builder.addFormDataPart(Const.LOGIN_ID, login_id)
                .addFormDataPart(Const.USER_ID, user_id)
                .addFormDataPart(Const.PLAY_ID, play_id)
                .addFormDataPart(Const.TASK_ID, task_id)
                .addFormDataPart(Const.SCENARIO, scenario)
                .addFormDataPart(Const.ANSWER_TYPE,answer_type)
                .addFormDataPart(Const.SKIP, skip);


        final RequestBody requestBody = builder.build();

        Call<TaskInfoBean> call = retroFitService.verifyTaskAns(requestBody);
        call.enqueue(new Callback<TaskInfoBean>() {
            @Override
            public void onResponse(Call<TaskInfoBean> call, Response<TaskInfoBean> response) {
                if(response.code()==200) {
                    TaskInfoBean taskInfoBean = response.body();
                    listener.OnVerifyTaskAns(taskInfoBean);
                }else{
                    Log.e(TAG, "onResponse: "+response.toString() );
                }
            }

            @Override
            public void onFailure(Call<TaskInfoBean> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return null;
    }
}
