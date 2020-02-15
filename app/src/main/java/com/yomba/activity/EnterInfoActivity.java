package com.yomba.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.adapter.CountryAdapter;
import com.yomba.dao.FetchCountryList;
import com.yomba.dao.VerifyInfo;
import com.yomba.dao.VerifyOTP;
import com.yomba.dao.VerifyPhone;
import com.yomba.model.Const;
import com.yomba.model.CountryBean;
import com.yomba.model.UserInfo;


import java.util.List;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;
import static com.yomba.utils.CommonMethod.HideMyKeyboard;

public class EnterInfoActivity extends BaseActivity implements View.OnClickListener,
VerifyInfo.OnVerifyInfoListener,VerifyPhone.OnVerifyPhoneListener,VerifyOTP.OnVerifyOTPListener,
        FetchCountryList.OnFetchCountryListener{

    Context context;
    Button btn_next;
    ScrollView infoScroll;
    RelativeLayout infomainbg;
    EditText edt_firstname,edt_lastname,edt_nickname;
    LinearLayout info_section;
    UserInfo userInfo;
    String TAG="UserInfo";
    RelativeLayout alert_relative_progress;
    Spinner country_code_spinner;
    ImageView alert_progress_dialog;
    HomeActivity homeActivity = new HomeActivity();
    private String country_code;
    private List<CountryBean.Datum> countryList;
    private AlertDialog numberAlertdialog;
    private AlertDialog myDetailsdialog;
    PlayActivity playActivity = new PlayActivity();
    SummeryActivity summeryActivity = new SummeryActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_enter_info, frameLayout);

        context=this;
        userInfo= Const.userInfo;
        prefBean=sharedPrefrenceManager.getSharedPreferences();
        initView(rootView);



    }

    private void initView(View rootView) {
        infomainbg=findViewById(R.id.infomainbg);
        info_section=findViewById(R.id.info_section);
        btn_next=findViewById(R.id.btn_next);
        infoScroll=findViewById(R.id.info_scroll);

        edt_firstname=findViewById(R.id.edt_firstname);
        edt_lastname=findViewById(R.id.edt_lastname);
        edt_nickname=findViewById(R.id.edt_nickname);

        relative_progress.setVisibility(View.GONE);


        UserInfo.Data currentUser = userInfo.getData();
        if(currentUser.getScenario().equals(Const.BUYER)){


                if (currentUser.getIsPhoneVerified().equals(Const.TRUE)) {
                    GoFrom(context, homeActivity);
                    finish();

                }else{
                    info_section.setVisibility(View.GONE);
                    showEnterNumbeAlert();
                }



        }if(currentUser.getScenario().equals(Const.INVITEE)) {
            if (!currentUser.getFirstName().equals("") || !currentUser.getLastName().equals("") || !currentUser.getNickname().equals("")) {
                info_section.setVisibility(View.GONE);
                if (currentUser.getIsPhoneVerified().equals(Const.TRUE)) {
                    GoFrom(context, homeActivity);
                    finish();
                }else {
                    showEnterNumbeAlert();
                }
            } else {
                info_section.setVisibility(View.VISIBLE);

            }
        }


        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_next){
            String fname=edt_firstname.getText().toString();
            String lname=edt_lastname.getText().toString();
            String nickname=edt_nickname.getText().toString();

            String msg="";
            if(fname.equals("")){
                msg=msg+getString(R.string.enterfname);
            }
            if(lname.equals("")){
                msg=msg+getString(R.string.enterlname);
            }if(nickname.equals("")){
                msg=msg+getString(R.string.enternickname);
            }
            HideMyKeyboard(context);
            if(msg.equals("")){
                UserInfo.Data currentUser = userInfo.getData();
                if(currentUser.getFirstName().equals("") || currentUser.getLastName().equals("") || currentUser.getNickname().equals("")) {
                    relative_progress.setVisibility(View.VISIBLE);
                    edt_firstname.setText("");
                    edt_lastname.setText("");
                    edt_nickname.setText("");
                    new VerifyInfo(context, currentUser.getScenario(), fname, lname, nickname, currentUser.getLoginId(),prefBean.getOrderID(), this).execute();
                }else{
                    if(currentUser.getIsPhoneVerified().equals(Const.FALSE)){
                        showEnterNumbeAlert();
                    }else{
                        GoFrom(context,homeActivity);
                        finish();
                    }

                }

            }else{
                AlertMe(context,msg,null,false);
            }

        }
    }


    @Override
    public void onVerifyInfo(UserInfo userInfo) {
        relative_progress.setVisibility(View.GONE);
        if(userInfo.getStatus()==1){
            this.userInfo=userInfo;
            Const.userInfo=userInfo;
            info_section.setVisibility(View.GONE);
            showEnterNumbeAlert();
        }else{
            Toast.makeText(context, userInfo.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    private void showEnterCodeAlert() {
//        infoScroll.setVisibility(View.VISIBLE);
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View layoutView = layoutInflater.inflate(R.layout.custom_enter_code_dialog,
//                null);
//        //  Here is main code to bind xml with java
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(layoutView);
//        builder.setCancelable(false);
//        final AlertDialog dialog = builder.create();
//        Button btn_submit_code;
//        ImageView img_close_code;
//        final EditText edt_ver_code;
//        edt_ver_code = layoutView.findViewById(R.id.edt_verify_code);
//        btn_submit_code = layoutView.findViewById(R.id.btn_submit_code);
//        img_close_code = layoutView.findViewById(R.id.img_close_dialog);
//        btn_submit_code.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String verification_code = edt_ver_code.getText().toString();
//                startVerificationProcess(verification_code);
//
//                dialog.dismiss();
//            }
//        });
//        img_close_code.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showEnterNumbeAlert();
//                dialog.dismiss();
//            }
//        });
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//
//    }


    private void verfiyMyDetails(String title_text) {
        infoScroll.setVisibility(View.VISIBLE);
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(R.layout.custom_enter_code_dialog, null);

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
        alert_relative_progress=layoutView.findViewById(R.id.alert_relative_progress);
        alert_progress_dialog=layoutView.findViewById(R.id.alert_progress_dialog);
        alert_relative_progress.setVisibility(View.GONE);
        Glide.with(context)
                .load(R.drawable.ic_progress)
                .into(alert_progress_dialog);

        btn_submit_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verification_code = edt_verify_code.getText().toString();
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                if (!verification_code.equals("")) {
                    Toast.makeText(context, getString(R.string.processStarted), Toast.LENGTH_SHORT).show();
                    startVerificationProcess(verification_code);
                    edt_verify_code.setText("");
                } else {
                    AlertMe(context,getString(R.string.enterphone),null,false);
                }

            }
        });

        img_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDetailsdialog.dismiss();
                showEnterNumbeAlert();
            }
        });


        myDetailsdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        myDetailsdialog.setCancelable(false);
        myDetailsdialog.show();
    }

    private void startVerificationProcess(String verification_code) {
        String verification_type =Const.PHONE;
        //relative_progress.setVisibility(View.VISIBLE);
        alert_relative_progress.setVisibility(View.VISIBLE);

        new VerifyOTP(context, verification_type, verification_code, prefBean.getScenario(), prefBean.getLoginID(),prefBean.getOrderID(), this).execute();

    }

//    private void showWrongAlert() {
//        infoScroll.setVisibility(View.GONE);
//        LayoutInflater layoutInflater=getLayoutInflater();
//        View layoutView=layoutInflater.inflate(R.layout.custom_wrong_alert_dialog,
//                null);
//        //  Here is main code to bind xml with java
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setView(layoutView);
//        builder.setCancelable(false);
//        final AlertDialog  dialog=builder.create();
//        Button btn_retry;
//        btn_retry=layoutView.findViewById(R.id.btn_retry);
//        btn_retry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
////                showEnterCodeAlert();
//                verfiyMyDetails("Enter Code To  \n Verify Your Phone");
//            }
//        });
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }

//    private void showRightAlert() {
//        infoScroll.setVisibility(View.GONE);
//        LayoutInflater layoutInflater=getLayoutInflater();
//        View layoutView=layoutInflater.inflate(R.layout.custom_right_alert_dialog,
//                null);
//        //  Here is main code to bind xml with java
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setView(layoutView);
//        builder.setCancelable(false);
//        final AlertDialog  dialog=builder.create();
//        Button btn_right;
//        btn_right=layoutView.findViewById(R.id.btn_right);
//        btn_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                GoFrom(context,homeActivity);
//                finish();
//            }
//        });
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }

    private void showEnterNumbeAlert() {
        infoScroll.setVisibility(View.VISIBLE);
            LayoutInflater layoutInflater=getLayoutInflater();
            View layoutView=layoutInflater.inflate(R.layout.custom_enter_number_dialog,
                    null);
        //  Here is main code to bind xml with java
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(layoutView);
        builder.setCancelable(false);
        numberAlertdialog=builder.create();
        Button btn_submit_no;
        ImageView img_close_number;

        final EditText edt_mobile_no;

        btn_submit_no=layoutView.findViewById(R.id.btn_submit_no);
        img_close_number=layoutView.findViewById(R.id.img_close_number);
        alert_progress_dialog=layoutView.findViewById(R.id.alert_progress_dialog);
        alert_relative_progress=layoutView.findViewById(R.id.alert_relative_progress);
        alert_relative_progress.setVisibility(View.GONE);
        Glide.with(context)
                .load(R.drawable.ic_progress)
                .into(alert_progress_dialog);
        img_close_number.setVisibility(View.INVISIBLE);
        edt_mobile_no=layoutView.findViewById(R.id.edt_mobile_no);
        country_code_spinner=(Spinner) layoutView.findViewById(R.id.country_code_spinner);

        setUpCountryAdapter();

        btn_submit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                String msg="";
                if(edt_mobile_no.getText().toString().equals("")){
                    msg=msg+getString(R.string.entermobile);
                }

                if(msg.equals("")){

                    String phone=country_code+edt_mobile_no.getText().toString();
                    String scenario=userInfo.getData().getScenario();
                    String login_id=userInfo.getData().getLoginId();
                    String order_id=userInfo.getData().getOrderId();
                    processPhoneVerification(phone,scenario,login_id,order_id);



                }else{
                  //  showEnterNumbeAlert();
                    AlertMe(context,msg,null,false);
                }

            }
        });


        numberAlertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        numberAlertdialog.setCancelable(false);
        numberAlertdialog.show();

    }

    private void setUpCountryAdapter() {
        new FetchCountryList(context,Const.userInfo.getData().getLoginId(),this).execute();
    }

    private void processPhoneVerification(String phone, String scenario, String login_id,String order_id) {
       // relative_progress.setVisibility(View.VISIBLE);
        alert_relative_progress.setVisibility(View.VISIBLE);
        new VerifyPhone(context,phone,scenario,login_id,order_id,this).execute();
    }


    @Override
    public void onVerifyPhone(UserInfo userInfo) {
        alert_relative_progress.setVisibility(View.GONE);
        numberAlertdialog.dismiss();
        //relative_progress.setVisibility(View.GONE);
        if(userInfo.getStatus()==1){
            this.userInfo=userInfo;
            Const.userInfo=userInfo;
           // showEnterCodeAlert();
            verfiyMyDetails(getString(R.string.codetophone));
        }else {
            showEnterNumbeAlert();
            Toast.makeText(context,userInfo.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onVerifyOTP(UserInfo userInfo) {
        //relative_progress.setVisibility(View.GONE);
        alert_relative_progress.setVisibility(View.GONE);
        myDetailsdialog.dismiss();
        if(userInfo.getStatus()==1){
            Const.userInfo=userInfo;
            UserInfo.Data currentData = userInfo.getData();
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
                GoFrom(context,homeActivity);
            }

            finish();
            //showRightAlert();
        }else {
            verfiyMyDetails(getString(R.string.codetophone));
            //showWrongAlert();
        }
    }

    @Override
    public void onFetchCountry(CountryBean countryBean) {
        countryList = countryBean.getData();
        if(countryList.size()>0){
            Const.currentAction="country";
            CountryAdapter adapter=new CountryAdapter(context,countryList, Const.currentAction);
            country_code_spinner.setAdapter(adapter);
            country_code_spinner.setSelection(Const.countryPosition);

        }

        country_code_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                country_code=countryList.get(position).getPhonecode();
               // Toast.makeText(context, country_code, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(context, getString(R.string.somethingwrong), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
