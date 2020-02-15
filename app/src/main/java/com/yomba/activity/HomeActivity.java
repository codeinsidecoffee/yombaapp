package com.yomba.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.model.UserInfo;
import com.yomba.utils.CommonMethod;

import java.util.Objects;

import static com.yomba.utils.CommonMethod.GoFrom;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    Button btn_next;
    Context context;
    TextView txt_reg_code;
    ImageView img_copycode, img_sendgmail, img_sendwhatsapp;
    UserInfo userInfo;
    StartGameActivity startGameActivity = new StartGameActivity();
    private String TAG = "HomeActivity";
    SummeryActivity summeryActivity = new SummeryActivity();
    PlayActivity playActivity = new PlayActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_home, frameLayout);
        context = this;
        userInfo = Const.userInfo;
        prefBean = sharedPrefrenceManager.getSharedPreferences();
        prefBean.setWaitForGame(true);
        prefBean.setGameStarted(false);
        prefBean.setGameFinish(false);
        prefBean.setInviteScreen(false);
        sharedPrefrenceManager.setSharedPreferences(prefBean);
        initView();

    }

    private void initView() {
        btn_next = findViewById(R.id.btn_next);
        txt_reg_code = findViewById(R.id.txt_reg_code);
        img_copycode = findViewById(R.id.img_copycode);
        img_sendgmail = findViewById(R.id.img_sendgmail);
        img_sendwhatsapp = findViewById(R.id.img_sendwhatsapp);

        txt_reg_code.setText(userInfo.getData().getUserRegistrationCode());

        btn_next.setOnClickListener(this);
        img_copycode.setOnClickListener(this);
        img_sendgmail.setOnClickListener(this);
        img_sendwhatsapp.setOnClickListener(this);
    }

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

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String call_api = intent.getStringExtra("call_api");
            String play_id = intent.getStringExtra("play_id");

            prefBean.setPlayID(play_id);
            sharedPrefrenceManager.setSharedPreferences(prefBean);

            String clue_number = intent.getStringExtra("clue_number");
            String title = intent.getStringExtra("title");
            Log.e(TAG, "onMessageReceived: clue api " + call_api);
            Log.e(TAG, "onMessageReceived:clue number " + clue_number);

            if (call_api.equals("open_clue")) {

                visitPlayActivity();

            } else if (call_api.equals("task_status")) {
                visitPlayActivity();
            } else if (call_api.equals("game_completed")) {

                visitSummery();
            }

            Log.e(TAG, "onMessageReceived: Title" + title);
        }
    };

    private void visitPlayActivity() {
        GoFrom(context, playActivity);
        finish();
    }

    private void visitSummery() {
        GoFrom(this, summeryActivity);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
            CommonMethod.GoFrom(context, startGameActivity);
            finish();
        } else if (view.getId() == R.id.img_copycode) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("user_reg_code",getsendValue() );
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, getString(R.string.copiedcode), Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.img_sendgmail) {
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.letsplay) + userInfo.getData().getGName() + getString(R.string.game));
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getsendValue());
            emailIntent.setType("message/rfc822");

            try {
                startActivity(Intent.createChooser(emailIntent,
                        "Send email using..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(context,
                        "No email clients installed.",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.img_sendwhatsapp) {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, getsendValue());
            try {
                Objects.requireNonNull(context).startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
            }
        }


    }
    public String getsendValue(){
        return getString(R.string.joinus) + userInfo.getData().getGName() +
                getString(R.string.home_msg1)+
                userInfo.getData().getUserRegistrationCode() +
                "\n" + getString(R.string.gamepurchaseOn) + " " + userInfo.getData().getPurchaseDate()+
                "\n"+getString(R.string.downloadappfromappstore) +
                "\n Download Android App : \n https://play.google.com/store/apps/details?id=com.yomba" +
                "\n Download IOS App : \n https://apps.apple.com/us/app/yomba/id1490403950?ls=1&mt=8";

    }
}
