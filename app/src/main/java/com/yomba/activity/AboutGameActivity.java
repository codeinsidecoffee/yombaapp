package com.yomba.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yomba.R;
import com.yomba.dao.LogoutGame;
import com.yomba.dao.StartGame;
import com.yomba.model.Const;
import com.yomba.model.InviteeListBean;
import com.yomba.model.PrefBean;
import com.yomba.model.TaskInfoBean;
import com.yomba.model.UserInfo;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;

public class AboutGameActivity extends BaseActivity implements View.OnClickListener, StartGame.OnStartGameListener, LogoutGame.OnLogoutGameListener {

    Context context;
    TextView txt_back,txt_start;
    WebView webView;
    InviteeListBean.Gameinfo gameInfo;
    PlayActivity playActivity=new PlayActivity();
    private String TAG="AboutGameActivity";
    private String login_id;
    private String user_id;
    private String game_id;
    private String player_ids;
    LinearLayout linear_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_about_game, frameLayout);
        context=this;
         gameInfo= Const.GAME_INFO;
         Const.visitedAboutGame=true;
        initView();
    }

    private void initView() {
        txt_start=findViewById(R.id.txt_start);
        txt_back=findViewById(R.id.txt_back);
        linear_exit=findViewById(R.id.linear_exit);

        txt_start.setOnClickListener(this);
        txt_back.setOnClickListener(this);
        linear_exit.setOnClickListener(this);

        webView=findViewById(R.id.webView);
        Log.e("initView", "initView: "+gameInfo.getAboutGame() );
        webView.loadDataWithBaseURL(null,gameInfo.getAboutGame(),"text/html","utf-8",null);
//        if(Const.currentUserType.equals(Const.INVITEE)){

//        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.txt_start){
            if (prefBean.getScenario().equals(Const.INVITEE)) {
                AlertMe(context,getString(R.string.about_msg1)+prefBean.getBuyerNickName()+" "+getString(R.string.about_msg2),null,false);
            } else {
                processStartGame();
            }
        }else if(view.getId()==R.id.txt_back){
            finish();
        }else if(view.getId()==R.id.linear_exit){
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    startNewGame();
                }
            };
            AlertMe(context,getString(R.string.about_msg3),runnable,true);
        }
    }

    private void processStartGame() {
        login_id=prefBean.getLoginID();
         user_id=prefBean.getUserID();
         game_id=prefBean.getGameID();
            player_ids="";
        for(int p=0;p<Const.playerIDs.size();p++) {
            if(p==Const.playerIDs.size()-1){
                player_ids = player_ids + Const.playerIDs.get(p);
            }else{
                player_ids = player_ids + Const.playerIDs.get(p) + ",";
            }
        }
//        if(Const.playerIDs.size()>0){
//            startGameNow();
//        }else{
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    startGameNow();
//                }
//            };
//            AlertMe(context, "Are You Sure You Want to Start Game Without Invitee ?", runnable, true);
//
//        }

        startGameNow();

    }

    private void startGameNow() {
        relative_progress.setVisibility(View.VISIBLE);
        new StartGame(context,login_id,user_id,game_id,player_ids,this).execute();
    }


    @Override
    public void onStartGameClick(TaskInfoBean taskInfoBean) {
        relative_progress.setVisibility(View.GONE);

        if(taskInfoBean.getStatus()==1){
            prefBean.setGameStarted(true);
            prefBean.setPlayID(taskInfoBean.getData().getLogininfo().getPlayId());
            sharedPrefrenceManager.setSharedPreferences(prefBean);
            Const.LOGIN_INFO=taskInfoBean.getData().getLogininfo();
            GoFrom(context,playActivity);
            finish();
        }else{
            AlertMe(context,taskInfoBean.getMessage(),null,false);

        }
    }
    private void manageGame(String message) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutInflateView = layoutInflater.inflate(R.layout.custom_toast_layout, null);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(layoutInflateView);
        final AlertDialog  dialog=builder.create();
        TextView toastmsg;
        Button btn_ok,btn_cancel;
        CardView cancel_card;
        toastmsg = layoutInflateView.findViewById(R.id.toastmsg);
        btn_ok = layoutInflateView.findViewById(R.id.btn_ok);
        btn_cancel = layoutInflateView.findViewById(R.id.btn_cancel);
        cancel_card = layoutInflateView.findViewById(R.id.cancel_card);
        cancel_card.setVisibility(View.VISIBLE);
        btn_cancel.setText(getString(R.string.joinNewGame));
        toastmsg.setText(message);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void startNewGame() {
        relative_progress.setVisibility(View.VISIBLE);
        new LogoutGame(context,prefBean.getScenario(),prefBean.getOrderID(),prefBean.getLoginID(),this).execute();
    }

    @Override
    public void onLogoutGame(UserInfo logoutGame) {
        relative_progress.setVisibility(View.GONE);
        if(logoutGame.getStatus()==1){
            EnterCodeActivity enterCodeActivity=new EnterCodeActivity();
            prefBean=new PrefBean();
            sharedPrefrenceManager.setSharedPreferences(prefBean);
            GoFrom(context,enterCodeActivity);
            finish();
        }else{
            Toast.makeText(context, logoutGame.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
