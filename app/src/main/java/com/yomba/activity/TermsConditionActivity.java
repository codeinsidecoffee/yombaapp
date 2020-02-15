package com.yomba.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.model.UserInfo;

public class TermsConditionActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_back;
    Context context;
    UserInfo userInfo;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condition);
        context=this;
        userInfo = Const.buyerInfo;
        initView();
    }

    private void initView() {
        webView=findViewById(R.id.webView);
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        webView.loadDataWithBaseURL(null,userInfo.getData().getTermsCondition(),"text/html","utf-8",null);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_back){
            finish();
        }
    }


}
