package com.yomba.activity;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.services.NetworkChangeReceiver;

public class ConnectionLostActivity extends AppCompatActivity {
    ImageView img_owl;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_lost);
        context = this;
        Const.InternetNotFound=true;
        initView();
    }



    private void initView() {
        img_owl=findViewById(R.id.img_owl);
        Glide.with(context)
                .load(R.drawable.connectionlost)
                .into(img_owl);


    }


}
