package com.yomba.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.model.PrefBean;
import com.yomba.services.NetworkChangeReceiver;
import com.yomba.utils.Language;
import com.yomba.utils.SharedPrefrenceManager;

public class BaseActivity extends AppCompatActivity {

    Context context;
    PrefBean prefBean;
    public FrameLayout frameLayout;
    SharedPrefrenceManager sharedPrefrenceManager;
    RelativeLayout relative_progress;
    ImageView progress_dialog;
    public RelativeLayout sidetouch;
    String language="en";
    BroadcastReceiver networkChangeReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context=this;
        networkChangeReciver=new NetworkChangeReceiver();
        registerBrodcastReciver(networkChangeReciver);
        initView();

        manageSharedPref();

    }


    private void registerBrodcastReciver(BroadcastReceiver networkChangeReciver) {
        registerReceiver(this.networkChangeReciver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void manageSharedPref() {

        sharedPrefrenceManager=new SharedPrefrenceManager(Const.PREF_FILE,context);
        prefBean=sharedPrefrenceManager.getSharedPreferences();
        if(prefBean==null){

            prefBean.setCurrentLang(language);
            prefBean.setGameID("");
            prefBean.setLogin(false);
            prefBean.setGameFinish(false);
            prefBean.setGameStarted(false);
            prefBean.setWaitForGame(false);
            prefBean.setInviteScreen(false);
            sharedPrefrenceManager.setSharedPreferences(prefBean);
        }else{
            language=prefBean.getCurrentLang();
        }
        Log.e("anc", "onCreate: "+prefBean.getCurrentLang() );
        Language.setLanguage(context,language);

    }

    private void initView() {
        frameLayout =findViewById(R.id.frameLayout);
        sidetouch =findViewById(R.id.sidetouch);
        relative_progress =findViewById(R.id.relative_progress);
        progress_dialog =findViewById(R.id.progress_dialog);
        Glide.with(this.context)
                .load(R.drawable.ic_progress)
                .into(progress_dialog);

        MyFocusChangeListener focusChangeListener=new MyFocusChangeListener();
        sidetouch.setOnTouchListener(focusChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        boolean isInternetOn = checkInerntetConection(this);
//        if(isInternetOn){
//            finish();
//        }else{
//            ConnectionLostActivity connectionLostActivity=new ConnectionLostActivity();
//            GoFrom(this,connectionLostActivity);
//        }
    }

    private class MyFocusChangeListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


            return false;
        }
    }




}
