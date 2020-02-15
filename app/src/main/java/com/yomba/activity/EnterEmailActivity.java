package com.yomba.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.dao.VerifyEmail;
import com.yomba.dao.VerifyOTP;
import com.yomba.model.Const;
import com.yomba.model.UserInfo;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;
import static com.yomba.utils.CommonMethod.HideMyKeyboard;


public class EnterEmailActivity extends BaseActivity implements View.OnClickListener,
        VerifyEmail.OnVerifyEmailListener, VerifyOTP.OnVerifyOTPListener {

    Button btn_next;
    TextView txt_link;
    EditText edt_email;
    CheckBox chb_terms;
    RelativeLayout alert_relative_progress;
    ImageView alert_progress_dialog;

    Context context;
    Boolean chbStatus = false;
    UserInfo userInfo;
    String TAG = "EnterEmailActivity";
    EnterInfoActivity enterInfoActivity = new EnterInfoActivity();
    String emailPattern = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?";

    PlayActivity playActivity = new PlayActivity();
    SummeryActivity summeryActivity = new SummeryActivity();
    TermsConditionActivity termsConditionActivity = new TermsConditionActivity();
    private AlertDialog myDetailsdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_enter_email, frameLayout);
        context = this;
        userInfo = Const.buyerInfo;
        prefBean=sharedPrefrenceManager.getSharedPreferences();
        initView(rootView);

    }

    private void initView(View rootView) {
        txt_link = findViewById(R.id.txt_link);
        btn_next = findViewById(R.id.btn_next);
        edt_email = findViewById(R.id.edt_email);
        chb_terms = findViewById(R.id.chb_terms);

        relative_progress.setVisibility(View.GONE);

        txt_link.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        chb_terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                chbStatus = isChecked;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txt_link) {
            GoFrom(context, termsConditionActivity);


        }
        if (view.getId() == R.id.btn_next) {
            HideMyKeyboard(context);
            if (chbStatus) {
                String email = edt_email.getText().toString();
                Toast.makeText(context,prefBean.getCurrentLang(), Toast.LENGTH_SHORT).show();
                if (!email.equals("")) {
                    if (email.trim().matches(emailPattern)) {
                        String order_id = prefBean.getOrderID();
                        relative_progress.setVisibility(View.VISIBLE);
                        new VerifyEmail(context, email, order_id, prefBean.getCurrentLang(),this).execute();
                    } else {
                        AlertMe(context, getString(R.string.entervalidemail), null,false);
                    }


                } else {
                    AlertMe(context, getString(R.string.enteremail), null,false);
                }

            } else {
                AlertMe(context, getString(R.string.acceptterms), null,false);

            }

        }
    }


    @Override
    public void onVerifyEmail(UserInfo userInfo) {
        relative_progress.setVisibility(View.GONE);
        if (userInfo.getStatus() == 1) {
            Const.userInfo = userInfo;
            UserInfo.Data currentInfo = userInfo.getData();
//                if (currentInfo.getIsEmailVerified().equals(Const.FALSE)) {
            prefBean.setLoginID(userInfo.getData().getLoginId());
            prefBean.setOrderID(userInfo.getData().getOrderId());
            prefBean.setUserID(userInfo.getData().getUserId());
            prefBean.setGameID(userInfo.getData().getGameId());
            prefBean.setScenario(userInfo.getData().getScenario());
            sharedPrefrenceManager.setSharedPreferences(prefBean);
            Toast.makeText(context, getString(R.string.email_msg1), Toast.LENGTH_LONG).show();
            verfiyMyDetails(getString(R.string.email_msg2));
//                } else{
//                    CommonMethod.GoFrom(context,enterInfoActivity);
//                    finish();
//                }

        } else {
            if (userInfo.getMessage().equals(getString(R.string.game_already_started))) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        EnterCodeActivity enterCodeActivity = new EnterCodeActivity();
                        GoFrom(context, enterCodeActivity);
                        finish();
                    }
                };
                AlertMe(context, userInfo.getMessage(), runnable,false);
            } else {

                AlertMe(context, userInfo.getMessage(), null,false);
            }
        }

    }

    private void verfiyMyDetails(String title_text) {

        LayoutInflater layoutInflater = getLayoutInflater();
        final View layoutView = layoutInflater.inflate(R.layout.custom_enter_code_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layoutView);

        Button btn_submit_code;
        final EditText edt_verify_code;
        TextView txt_verify_title;
        ImageView img_close_dialog;

        myDetailsdialog = builder.create();

        btn_submit_code = layoutView.findViewById(R.id.btn_submit_code);
        edt_verify_code = layoutView.findViewById(R.id.edt_verify_code);
        txt_verify_title = layoutView.findViewById(R.id.txt_verify_title);
        txt_verify_title.setText(title_text);
        img_close_dialog = layoutView.findViewById(R.id.img_close_dialog);
        alert_relative_progress = layoutView.findViewById(R.id.alert_relative_progress);
        alert_progress_dialog = layoutView.findViewById(R.id.alert_progress_dialog);
        Glide.with(context)
                .load(R.drawable.ic_progress)
                .into(alert_progress_dialog);
        alert_relative_progress.setVisibility(View.GONE);

        btn_submit_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                String verification_code = edt_verify_code.getText().toString();
                if (!verification_code.equals("")) {
                    startVerificationProcess(verification_code);
                    edt_verify_code.setText("");
                } else {
                    AlertMe(context, getString(R.string.email_msg3), null,false);
                }

            }
        });

        img_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDetailsdialog.dismiss();
            }
        });


        myDetailsdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        myDetailsdialog.setCancelable(false);
        myDetailsdialog.show();
    }


    private void startVerificationProcess(String verification_code) {
        userInfo = Const.userInfo;
        String scenario = prefBean.getScenario();
        String login_id = prefBean.getLoginID();
        String order_id=prefBean.getOrderID();
        String verification_type = Const.EMAIL;
        //relative_progress.setVisibility(View.VISIBLE);
        alert_relative_progress.setVisibility(View.VISIBLE);

        new VerifyOTP(context, verification_type, verification_code, scenario, login_id,order_id, this).execute();
    }

    @Override
    public void onVerifyOTP(UserInfo userInfo) {
        alert_relative_progress.setVisibility(View.GONE);
        if (userInfo.getStatus() == 1) {
            myDetailsdialog.dismiss();
            prefBean.setOrderID(userInfo.getData().getOrderId());
            prefBean.setGameID(userInfo.getData().getGameId());
            prefBean.setUserID(userInfo.getData().getUserId());
            prefBean.setLoginID(userInfo.getData().getLoginId());
            prefBean.setPlayID(userInfo.getData().getPlayID());
            prefBean.setScenario(userInfo.getData().getScenario());
            if(userInfo.getData().getIsGameAlreadyPlayed().equals(Const.TRUE)){
                prefBean.setGameFinish(true);
            }else{
                prefBean.setGameFinish(false);
            }if(userInfo.getData().getIsGameAlreadyStarted().equals(Const.TRUE)){
                prefBean.setGameStarted(true);
            }else{
                prefBean.setGameStarted(false);
            }

            sharedPrefrenceManager.setSharedPreferences(prefBean);
            Const.userInfo = userInfo;
            UserInfo.Data currentData = userInfo.getData();
            if(currentData.getIsPhoneVerified().equals("true")) {
                if (currentData.getIsGameAlreadyPlayed().equals("true")) {
                    GoFrom(context, summeryActivity);
                } else if (currentData.getIsGameAlreadyPlayed().equals("false") &&
                        currentData.getIsGameAlreadyStarted().equals("true")) {
                    Const.LOGIN_INFO.setLoginId(currentData.getLoginId());
                    Const.LOGIN_INFO.setUserId(currentData.getUserId());
                    Const.LOGIN_INFO.setGameId(currentData.getGameId());
                    Const.LOGIN_INFO.setPlayId(currentData.getPlayID());
                    Const.LOGIN_INFO.setScenario(currentData.getScenario());
                    Log.e(TAG, "Current Login Info: " + Const.LOGIN_INFO.toString());
                    GoFrom(context, playActivity);
                } else {
                    GoFrom(context, enterInfoActivity);
                }
            }else {
                GoFrom(context, enterInfoActivity);
            }

            finish();
        } else {
            AlertMe(context, userInfo.getMessage(), null,false);
        }

    }
}
