package com.yomba.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.yomba.R;
import com.yomba.adapter.CountryAdapter;
import com.yomba.dao.VerifyCode;
import com.yomba.model.Const;
import com.yomba.model.CountryBean;
import com.yomba.model.UserInfo;
import com.yomba.services.NetworkChangeReceiver;
import com.yomba.utils.CommonMethod;
import com.yomba.utils.Language;
import com.yomba.utils.SharedPrefrenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;
import static com.yomba.utils.CommonMethod.HideMyKeyboard;

public class EnterCodeActivity extends BaseActivity implements View.OnClickListener, VerifyCode.OnVerifyCodeListener {
    EditText edt1, edt2, edt3, edt4, edt5, edt6;
    TextView txt_resend;
    Spinner select_lang;
    Button btn_verify;
    Context context;
    private String TAG = "VerifyCode";
    EnterEmailActivity enterEmailActivity = new EnterEmailActivity();
    PlayActivity playActivity = new PlayActivity();
    StartGameActivity startGameActivity = new StartGameActivity();
    SummeryActivity summeryActivity = new SummeryActivity();
    HomeActivity homeActivity=new HomeActivity();
    BroadcastReceiver networkChangeReciver;

    int size = 1;
    //StringBuilder currentCode = new StringBuilder();
    String[] currentCode = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_enter_code, frameLayout);
        context = this;

        prefBean=sharedPrefrenceManager.getSharedPreferences();
        manageUserLastAction();
        intiView(rootView);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstance ID Failed " + task.getException());
                            return;
                        }
                        Const.FireBaseTokenID = task.getResult().getToken();
                        Log.e(TAG, "onComplete: " + Const.FireBaseTokenID);


                    }
                });
        if (getIntent().getExtras() != null) {
            String cPlayid="";
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);

                Log.e(TAG, "Key: " + key + " Value: " + value);
                if(key.equals("play_id")) {
                     cPlayid= getIntent().getExtras().getString(key);
                }
                if (key.equals("callapi")) {
                    Const.Notification_Category = value;
                    prefBean = sharedPrefrenceManager.getSharedPreferences();

                    Log.e(TAG, "onCreate: " + prefBean.toString());
                    if (value.equals(Const.GAME_COMPLETED_ALERT)){
                        GoFrom(this, summeryActivity);
                        finish();
                        return;
                    }else if (value.equals(Const.TASK_STATUS_ALERT)) {

                        prefBean.setGameStarted(true);
                        prefBean.setPlayID(cPlayid);
                        sharedPrefrenceManager.setSharedPreferences(prefBean);
                        if (prefBean.getGameStarted()) {
                            GoFrom(this, playActivity);
                            finish();
                            return;
                        }
                    }else if(value.equals(Const.OPEN_CLUE_ALERT)){
                        GoFrom(this, playActivity);
                        finish();
                        return;
                    }
                }

            }
        }


        //  Toast.makeText(context, sharedPrefrenceManager.getSharedPreferences().getCurrentLang(), Toast.LENGTH_SHORT).show();
    }
    private void manageUserLastAction() {
        Log.e(TAG, "manageUserLastAction: "+prefBean.toString() );
        if(prefBean.getGameStarted()){
            GoFrom(this, playActivity);
            finish();
        }else if(prefBean.getWaitForGame()){
            GoFrom(this, startGameActivity);
            finish();
        }else if(prefBean.getInviteScreen()){
            GoFrom(this, homeActivity);
            finish();
        }
    }

    private void intiView(View rootView) {
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        edt6 = findViewById(R.id.edt6);

        currentCode[0]="";
        currentCode[1]="";
        currentCode[2]="";
        currentCode[3]="";
        currentCode[4]="";
        currentCode[5]="";

        btn_verify = findViewById(R.id.btn_verify);
        select_lang = findViewById(R.id.select_lang);
        relative_progress.setVisibility(View.GONE);
        btn_verify.setOnClickListener(this);


        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edtChar = edt1.getText().toString();
                if (edtChar.length() == 1) {

                    edt2.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt1.requestFocus();
                }
                currentCode[0]=edtChar;


            }
        });

        edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edtChar = edt2.getText().toString();
                if (edtChar.length() == 1) {
                    edt3.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt1.requestFocus();
                }

                currentCode[1]=edtChar;


            }
        });
        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edtChar = edt3.getText().toString();
                if (edtChar.length() == 1) {
                    edt4.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt2.requestFocus();
                }
                currentCode[2]=edtChar;


            }
        });
        edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edtChar = edt4.getText().toString();
                if (edtChar.length() == 1) {
                    edt5.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt3.requestFocus();
                }
                currentCode[3]=edtChar;

            }
        });
        edt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edtChar = edt5.getText().toString();
                if (edtChar.length() == 1) {
                    edt6.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt4.requestFocus();
                }
                currentCode[4]=edtChar;


            }
        });

        edt6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "beforeTextChanged: " );
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e(TAG, "afterTextChanged: " );
                String edtChar = edt6.getText().toString();
                if (edtChar.length() == 1) {
                    edt6.requestFocus();
                } else if (edtChar.length() == 0) {
                    edt5.requestFocus();
                }

                currentCode[5]=edtChar;

            }
        });
        prePareData();
    }

    private void prePareData() {

        final List<CountryBean.Datum> countryList = new ArrayList<>();

        countryList.add(new CountryBean.Datum("1",
                "https://yomba.jodhaa.co.in/admin/assets/images/flags/US/flat/64.png",
                "US",
                "en",
                "United States",
                "+1"));
        countryList.add(new CountryBean.Datum("2",
                "https://yomba.jodhaa.co.in/admin/assets/images/flags/IL/flat/64.png",
                "IL",
                "iw",
                "Israel",
                "+972"));
        Const.currentAction = "lang";
        CountryAdapter adapter = new CountryAdapter(context, countryList, Const.currentAction);
        select_lang.setAdapter(adapter);
        final String previousLang = prefBean.getCurrentLang();
        select_lang.setSelection(adapter.getPosition(prefBean.getCurrentLang()));
        select_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (!previousLang.equals(countryList.get(position).getName())) {
                    prefBean.setCurrentLang(countryList.get(position).getName());
                    sharedPrefrenceManager.setSharedPreferences(prefBean);
                    Language.setLanguage(context, countryList.get(position).getName());

                    Intent intent = new Intent(context, EnterCodeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(context, getString(R.string.somethingwrong), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_verify) {
            HideMyKeyboard(context);
            if(currentCode[0].equals("")||currentCode[1].equals("")||currentCode[2].equals("")||currentCode[3].equals("")||currentCode[4].equals("")||currentCode[5].equals("")){
                AlertMe(context, getString(R.string.sixdegitcode), null,false);
            } else {
                //    Toast.makeText(context,currentCode, Toast.LENGTH_LONG).show();

                String reg_code ="";
                for(int c=0;c<currentCode.length;c++){
                    reg_code=reg_code+currentCode[c];
                }
                relative_progress.setVisibility(View.VISIBLE);
                new VerifyCode(reg_code, this).execute();
            }
        }
    }

    @Override
    public void onVerifyCode(UserInfo userInfo) {
        relative_progress.setVisibility(View.GONE);
        if (userInfo.getStatus() == 1) {
            Const.buyerInfo = userInfo;
            prefBean.setLoginID(userInfo.getData().getLoginId());
            prefBean.setOrderID(userInfo.getData().getOrderId());
            prefBean.setUserID(userInfo.getData().getUserId());
            prefBean.setGameID(userInfo.getData().getGameId());
            prefBean.setScenario(userInfo.getData().getScenario());
            prefBean.setBuyerEmail(userInfo.getData().getBuyerEmail());
            prefBean.setBuyerNickName(userInfo.getData().getNickname());
            sharedPrefrenceManager.setSharedPreferences(prefBean);
            CommonMethod.GoFrom(context, enterEmailActivity);
            finish();

        } else {
            CommonMethod.AlertMe(context, userInfo.getMessage(), null,false);
        }
    }
}
