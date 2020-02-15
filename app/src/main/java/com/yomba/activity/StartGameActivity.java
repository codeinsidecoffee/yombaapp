package com.yomba.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.adapter.InviteeListAdapater;
import com.yomba.dao.FetchInviteeList;
import com.yomba.dao.StartGame;
import com.yomba.model.ClueBean;
import com.yomba.model.Const;
import com.yomba.model.InviteeListBean;
import com.yomba.model.TaskInfoBean;
import com.yomba.model.UserInfo;
import com.yomba.utils.SharedPrefrenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;

public class StartGameActivity extends BaseActivity implements View.OnClickListener,FetchInviteeList.OnFetchInviteeListener, InviteeListAdapater.OnInviteeClickListener, StartGame.OnStartGameListener {

    Context context;
    TextView txt_start,txt_about,txt_game_name,txt_wait,txt_story,txt_start_point,txt_end_point,txt_total_player;
    ImageView img_game;
    UserInfo userInfo;
    RecyclerView invitee_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    AboutGameActivity aboutGameActivity=new AboutGameActivity();
    SummeryActivity summeryActivity=new SummeryActivity();
    PlayActivity playActivity=new PlayActivity();
    String totalPlayer="";

    SwipeRefreshLayout pulltorefresh;


    private String TAG="InviteeList";

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter("remoteMessage"));
    }


    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_start_game, frameLayout);
        context=this;
        Const.playerIDs=new ArrayList<>();
        userInfo= Const.userInfo;
        prefBean=sharedPrefrenceManager.getSharedPreferences();
        prefBean.setWaitForGame(true);
        prefBean.setGameStarted(false);
        prefBean.setGameFinish(false);
        prefBean.setInviteScreen(false);
        sharedPrefrenceManager.setSharedPreferences(prefBean);
        initView(rootView);

        Log.e(TAG, "onCreate: "+prefBean.toString() );

    }

    private void initView(View rootView) {
        invitee_recyclerview=findViewById(R.id.invitee_recyclerview);
        layoutManager=new GridLayoutManager(context,2);
        invitee_recyclerview.setLayoutManager(layoutManager);

        pulltorefresh=findViewById(R.id.pulltorefresh);
        txt_start=findViewById(R.id.txt_start);
        txt_about=findViewById(R.id.txt_about);
        txt_total_player=findViewById(R.id.txt_total_player);
        txt_game_name=findViewById(R.id.txt_game_name);
        img_game=findViewById(R.id.img_game);
        txt_story=findViewById(R.id.txt_story);
        txt_start_point=findViewById(R.id.txt_start_point);
        txt_end_point=findViewById(R.id.txt_end_point);
        txt_wait=findViewById(R.id.txt_wait);

        relative_progress.setVisibility(View.GONE);
        txt_start.setOnClickListener(this);
        txt_about.setOnClickListener(this);

        if(prefBean.getScenario().equals(Const.INVITEE)){
            //txt_start.setVisibility(View.GONE);
            txt_wait.setText(getString(R.string.game_start_will_soon));
        }

        pulltorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchInfo();
                pulltorefresh.setRefreshing(false);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchInfo();
    }

    private void fetchInfo() {
        relative_progress.setVisibility(View.VISIBLE);
        new FetchInviteeList(context,prefBean.getOrderID(),prefBean.getGameID(),this).execute();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.txt_about){
            GoFrom(context,aboutGameActivity);
        }else if(view.getId()==R.id.txt_start) {
            if (prefBean.getScenario().equals(Const.INVITEE)) {
                AlertMe(context, getString(R.string.about_msg1)+prefBean.getBuyerNickName()+getString(R.string.about_msg2), null, false);
            } else {
                if (Const.playerIDs.size() > 0) {
                    if (Const.visitedAboutGame) {
                        processStartGame();
                    } else {
                        AlertMe(context, getString(R.string.about_msg5), null, false);
                    }

                } else {
                    totalPlayer = getString(R.string.select_player_to_play);
                    AlertMe(context, totalPlayer, null, false);
                }

            }
        }
    }

    private void processStartGame() {
        String login_id=prefBean.getLoginID();
        String user_id=prefBean.getUserID();
        String game_id=prefBean.getGameID();
        String player_ids="";
        for(int p=0;p<Const.playerIDs.size();p++) {
            if(p==Const.playerIDs.size()-1){
                player_ids = player_ids + Const.playerIDs.get(p);
            }else{
                player_ids = player_ids + Const.playerIDs.get(p) + ",";
            }
        }
        Log.e(TAG, "processStartGame: final Player IDs"+player_ids );
        relative_progress.setVisibility(View.VISIBLE);
        new StartGame(context,login_id,user_id,game_id,player_ids,this).execute();
    }

    @Override
    public void onFetchInvitee(InviteeListBean inviteeListBean) {
        Const.playerIDs=new ArrayList<>();
        if(inviteeListBean.getStatus()==1) {
            String currentPlayid=inviteeListBean.getData().getLogininfo().getPlayId();
            if(currentPlayid.equals("")){
                List<InviteeListBean.Friendlist> friendlist = inviteeListBean.getData().getFriendlist();
                List<InviteeListBean.Friendlist> inviteeListInfo = new ArrayList<>();
                inviteeListInfo.add(new InviteeListBean.Friendlist(prefBean.getBuyerEmail(),prefBean.getBuyerNickName()));
                inviteeListInfo.addAll(friendlist);
                Const.GAME_INFO = inviteeListBean.getData().getGameinfo();
                setUpDataAdapter(inviteeListInfo);
            }else{
                prefBean.setPlayID(currentPlayid);
                sharedPrefrenceManager.setSharedPreferences(prefBean);
                visitPlayActivity();
            }

        }else{
            Toast.makeText(context, inviteeListBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
        relative_progress.setVisibility(View.GONE);
    }

    private void setUpDataAdapter(List<InviteeListBean.Friendlist> inviteeListInfo) {
        txt_game_name.setText(Const.GAME_INFO.getGName());
        txt_start_point.setText(Const.GAME_INFO.getStpoint());
        txt_end_point.setText(Const.GAME_INFO.getEndpoint());
        txt_story.setText(Const.GAME_INFO.getStory());
//        totalPlayer=getString(R.string.select_player_to_play);
//        if(prefBean.getScenario().equals(Const.INVITEE)){
            totalPlayer=inviteeListInfo.size()+" "+getString(R.string.players_join_game);
//        }
        txt_total_player.setText(totalPlayer);
        txt_total_player.setVisibility(View.VISIBLE);
        Const.currentUserType=prefBean.getScenario();
        Glide.with(context)
                .load(Const.GAME_INFO.getThumbnailImage())
                .into(img_game);
        if(inviteeListInfo.size()>0) {
            InviteeListAdapater adapater = new InviteeListAdapater(context, inviteeListInfo, this);
            invitee_recyclerview.setAdapter(adapater);
            invitee_recyclerview.setNestedScrollingEnabled(false);

        }
    }

    @Override
    public void onBackPressed() {
        ExitApp();
    }
    private void ExitApp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.douwanttoexit);
        builder.setIcon(R.mipmap.ic_launcher);
        //final AlertDialog dialog = builder.create();
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public void onInviteeClick(boolean isChecked, String inviteeId) {
        if(isChecked){
            Const.playerIDs.add(inviteeId);
        }else{

            if(Const.playerIDs.contains(inviteeId)){
                Const.playerIDs.remove(inviteeId);
            }
        }
       findPlayerId();
    }

    @Override
    public void onInviteeClick() {
        findPlayerId();
    }

    private void findPlayerId() {
        if(Const.playerIDs.size()>0) {
            totalPlayer = (Const.playerIDs.size()+1) + " " + getString(R.string.playerareconnected);
            txt_total_player.setText(totalPlayer);
        }else{
            totalPlayer=getString(R.string.select_player_to_play);
            txt_total_player.setText(totalPlayer);

        }
        txt_total_player.setVisibility(View.VISIBLE);
        String ids=Const.playerIDs.toString().substring(1,Const.playerIDs.toString().length()-1);
        Log.e(TAG, "onInviteeClick: "+ids);
    }

    @Override
    public void onStartGameClick(TaskInfoBean taskInfoBean) {
        relative_progress.setVisibility(View.GONE);
        prefBean.setGameStarted(true);
        prefBean.setPlayID(taskInfoBean.getData().getLogininfo().getPlayId());
        sharedPrefrenceManager.setSharedPreferences(prefBean);
        Const.LOGIN_INFO=taskInfoBean.getData().getLogininfo();
        if(taskInfoBean.getStatus()==1){
            GoFrom(context,playActivity);
            finish();
        }else{
            AlertMe(context,taskInfoBean.getMessage(),null,false);
        }
    }

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String call_api = intent.getStringExtra("call_api");
            String play_id = intent.getStringExtra("play_id");

            prefBean.setPlayID(play_id);
            sharedPrefrenceManager.setSharedPreferences(prefBean);

            String clue_number = intent.getStringExtra("clue_number");
            String title = intent.getStringExtra("title");
            Log.e(TAG, "onMessageReceived: clue api "+ call_api);
            Log.e(TAG, "onMessageReceived:clue number "+ clue_number);

            if(call_api.equals("open_clue")){

                visitPlayActivity();

            }else if(call_api.equals("task_status")){
               visitPlayActivity();
            }else if(call_api.equals("game_completed")){

                visitSummery();
            }

            Log.e(TAG, "onMessageReceived: Title"+title);
        }
    };

    private void visitPlayActivity() {
        GoFrom(context,playActivity);
        finish();
    }

    private void visitSummery() {
        GoFrom(this,summeryActivity);
        finish();
    }
}
