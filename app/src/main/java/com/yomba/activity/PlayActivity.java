package com.yomba.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import com.yomba.BuildConfig;
import com.yomba.R;
import com.yomba.adapter.AnswerAdapter;
import com.yomba.adapter.InstructionViewAdpater;
import com.yomba.adapter.TextViewAdapter;
import com.yomba.dao.FetchClue;
import com.yomba.dao.LogoutGame;
import com.yomba.dao.OpenSolve;
import com.yomba.dao.TaskStatus;
import com.yomba.dao.VerifyTaskAns;
import com.yomba.model.AnswerBean;
import com.yomba.model.ClueBean;
import com.yomba.model.Const;
import com.yomba.model.InstructionBean;
import com.yomba.model.PrefBean;
import com.yomba.model.TaskInfoBean;
import com.yomba.model.UserInfo;
import com.yomba.utils.CommonMethod;
import com.yomba.view.WorkaroundMapFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.yomba.utils.CommonMethod.AlertMe;
import static com.yomba.utils.CommonMethod.GoFrom;
import static com.yomba.utils.CommonMethod.ManageClue;

public class PlayActivity extends BaseActivity implements TaskStatus.OnTaskStatusListener, AnswerAdapter.OnAnswerViewClickListener, OnMapReadyCallback, View.OnClickListener, VerifyTaskAns.OnVerifyTaskAnsListener, FetchClue.OnFetchClueListener, OpenSolve.OnOpenSolveListener, LogoutGame.OnLogoutGameListener {

    Context context;
    String TAG = "PlayActivity";
    View fragmentDiscover, fragmentCrackIt, fragmentFinish;

    ImageView static_map;
    EditText edt_single_ans;
    ClueBean clueBean;
    TextView txt_total_task, txt_start_task;
    ProgressBar progressbar;
    CardView card_media_answer;
    ImageView img_ans;
    SeekBar seekbarthumb, seekbarvalue;
    SwipeRefreshLayout pulltorefresh;
    Double lastlocation = 00.00;
    int worngPopupOpen = 0;

    //*******************Custom TextView Variable Start***************************************

    RecyclerView customTextRecycler;
    RecyclerView.LayoutManager textviewManager;

    //*******************Custom TextView Variable End***************************************


    //*******************Google Map Variable Start***************************************

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker, mEndLocationMarker;
    ArrayList<LatLng> points = new ArrayList<LatLng>();
    private static final int REQ_PERMISSION = 1;
    String currentAnswer = "";
    LinearLayout linear_exit;
    NestedScrollView nestedScrollView;
    RelativeLayout relative_map;

    //********************Google Map Variable End****************************

    //********************* Media Type Variable (Footer Section)Start****************************
    ImageView img_yomba;
    private int INTENT_CAMERA_CODE = 100;
    String currentPhotoPath = "";
    private Bitmap logoBitmap, imageBitmap, finalBitmap;
    private static final int REQ_PERMISSION_CAMERA = 11111;
    LinearLayout footer;
    //********************* Media Type Variable (Footer Section) End****************************
    WebView dynamic_map;
    RecyclerView instructionView;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout media_answer;
    CardView card_map_image, card_map;
    RecyclerView multipleAnsRecycler;
    RecyclerView.LayoutManager ansLayoutManager;
    CardView card_finish;
    List<AnswerBean> selectedAns = new ArrayList<>();

    private ArrayList<AnswerBean> answerList;
    private Double endLat;
    private Double endLong;
    Button btn_next, btn_skip;
    TextView txt_clue, txt_solve;
    TextView txt_task_type, txt_your_answer;
    private String current_taskID = "";
    private String current_playID = "";
    private String current_skip = Const.FALSE;
    private String currentAnswer_TYPE = "";
    private RelativeLayout heading;
    private LinearLayout currentHeading, linear_score;
    TextView txt_distance;
    String finaldistance;
    SummeryActivity summeryActivity = new SummeryActivity();
    View thumbView;
    String[] permissionList = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private boolean ismapactivity = false;
    private Float currentZoom = 13.0F;
    private int prevRemainingDistance = 0;
    private AlertDialog displayDialog;
    private boolean isAlreadyDisplay = false;
    private int vibrateCount = 0;
    private String currentScore = "";
    private String previousScore = "";


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
        context = this;
        View rootView = getLayoutInflater().inflate(R.layout.activity_play, frameLayout);
        prefBean = sharedPrefrenceManager.getSharedPreferences();
        prefBean.setWaitForGame(false);
        prefBean.setGameStarted(true);
        prefBean.setGameFinish(false);
        prefBean.setInviteScreen(false);
        sharedPrefrenceManager.setSharedPreferences(prefBean);
        if (prefBean.getLoginID().equals("") && prefBean.getScenario().equals("") && prefBean.getUserID().equals("")) {
            visitEntercode();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissionList, REQ_PERMISSION);

            }
        }

        initView();

    }

    private void visitEntercode() {
        EnterCodeActivity enterCodeActivity = new EnterCodeActivity();
        prefBean = new PrefBean();
        prefBean.setWaitForGame(false);
        prefBean.setGameStarted(false);
        prefBean.setGameFinish(true);
        prefBean.setInviteScreen(false);
        sharedPrefrenceManager.setSharedPreferences(prefBean);
        GoFrom(this, enterCodeActivity);
        finish();
    }

    private void initView() {
        worngPopupOpen = 0;
        currentAnswer = "";
        vibrateCount=0;
        prefBean = sharedPrefrenceManager.getSharedPreferences();
        Log.e(TAG, "initView: " + prefBean.toString());
        pulltorefresh = findViewById(R.id.pulltorefresh);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        relative_map = findViewById(R.id.relative_map);
        footer = findViewById(R.id.footer);
        fragmentDiscover = findViewById(R.id.fragmentDiscover);
        fragmentFinish = findViewById(R.id.fragmentFinish);
        fragmentCrackIt = findViewById(R.id.fragmentCrackIt);
        heading = findViewById(R.id.heading);
        txt_distance = findViewById(R.id.txt_distance);
        card_finish = findViewById(R.id.card_finish);
        linear_score = findViewById(R.id.linear_score);
        currentHeading = findViewById(R.id.currentHeading);
        static_map = findViewById(R.id.static_map);
        dynamic_map = findViewById(R.id.dynamic_map);
        card_map = findViewById(R.id.card_map);
        card_map_image = findViewById(R.id.card_map_image);
        media_answer = findViewById(R.id.media_answer);
        edt_single_ans = findViewById(R.id.edt_single_ans);
        img_yomba = findViewById(R.id.img_yomba);
        btn_next = findViewById(R.id.btn_next);
        txt_clue = findViewById(R.id.txt_clue);
        btn_skip = findViewById(R.id.btn_skip);
        txt_your_answer = findViewById(R.id.txt_your_answer);
        txt_total_task = findViewById(R.id.txt_total_task);
        txt_task_type = findViewById(R.id.txt_task_type);
        seekbarthumb = findViewById(R.id.seekbarthumb);
        seekbarvalue = findViewById(R.id.seekbarvalue);
        seekbarvalue.setVisibility(View.GONE);
        progressbar = findViewById(R.id.progressbar);
        txt_solve = findViewById(R.id.txt_solve);
        txt_solve.setPaintFlags(txt_solve.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        multipleAnsRecycler = findViewById(R.id.multipleAnsRecycler);
        instructionView = findViewById(R.id.instructionView);
        layoutManager = new LinearLayoutManager(this);
        instructionView.setLayoutManager(layoutManager);
        ansLayoutManager = new GridLayoutManager(context, 2);
        multipleAnsRecycler.setLayoutManager(ansLayoutManager);
        card_media_answer = findViewById(R.id.card_media_answer);
        linear_exit = findViewById(R.id.linear_exit);
        txt_start_task = findViewById(R.id.txt_start_task);
        img_ans = findViewById(R.id.img_ans);
        card_media_answer.setVisibility(View.GONE);
        img_ans.setVisibility(View.GONE);
        img_yomba.setOnClickListener(this);
        current_skip = Const.FALSE;
        if (displayDialog != null) {
            displayDialog.dismiss();
        }
        CommonMethod.dismissAlertDialog();
        customTextRecycler = findViewById(R.id.customTextRecycler);
        textviewManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        customTextRecycler.setLayoutManager(textviewManager);

        String login_id = prefBean.getLoginID();
        String user_id = prefBean.getUserID();
        String game_id = prefBean.getGameID();
        String play_id = prefBean.getPlayID();
        String scenario = prefBean.getScenario();


        if (Const.isGameCompleted.equals(Const.TRUE)) {
            relative_progress.setVisibility(View.GONE);
            heading.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 30, 0, 0);
            linear_score.setLayoutParams(params);
            media_answer.setVisibility(View.GONE);
            fragmentCrackIt.setVisibility(View.GONE);
            fragmentDiscover.setVisibility(View.GONE);
            fragmentFinish.setVisibility(View.VISIBLE);
            instructionView.setVisibility(View.GONE);
            seekbarvalue.setVisibility(View.GONE);
            progressbar.setVisibility(View.GONE);
            seekbarvalue.setVisibility(View.GONE);
            txt_start_task.setVisibility(View.GONE);
            txt_total_task.setVisibility(View.GONE);
            seekbarthumb.setVisibility(View.GONE);
            footer.setVisibility(View.GONE);
            displayScore(Const.currentTASKInfo.getData().getGameScore());

        } else {
            relative_progress.setVisibility(View.VISIBLE);
            new TaskStatus(context, login_id, user_id, game_id, play_id, scenario, this).execute();
//            new TaskStatus(context, "8", "60", "7", "40", "invitee", this).execute();
        }

        btn_next.setOnClickListener(this);
        txt_clue.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
        txt_solve.setOnClickListener(this);
        linear_exit.setOnClickListener(this);
        card_finish.setOnClickListener(this);
        btn_skip.setVisibility(View.GONE);
        clueBean = null;
        thumbView = LayoutInflater.from(this).inflate(R.layout.seekbar_value_item, null, false);

        pulltorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                pulltorefresh.setRefreshing(false);
            }
        });
        edt_single_ans.setText("");

    }

    @Override
    public void onTaskStatus(TaskInfoBean taskInfoBean) {
        relative_progress.setVisibility(View.GONE);
        Const.isGameCompleted = taskInfoBean.getData().getIsGameCompleted();
        Const.currentTASKInfo = taskInfoBean;
        try {
            Const.RemainingClue = taskInfoBean.getData().getTaskinfo().getRemainingClue();
        }catch (Exception e){
            e.printStackTrace();
        }


        points = new ArrayList<>();
        mMap = null;
        currentScore = taskInfoBean.getData().getGameScore();
        if (!previousScore.equals("")) {
            if (!currentScore.equals(previousScore)) {
                int earnedPoint = Integer.parseInt(currentScore) - Integer.parseInt(previousScore);
                Toast.makeText(context, "You have earned " + earnedPoint + " points", Toast.LENGTH_SHORT).show();
                previousScore = currentScore;
            }
        }
        {
            previousScore = currentScore;
        }

        if (taskInfoBean.getData().getTaskinfo().getCanBeSkip() != null) {
            String canskip = taskInfoBean.getData().getTaskinfo().getCanBeSkip();
            if (canskip.equals(Const.TRUE)) {
                btn_skip.setVisibility(View.VISIBLE);
            } else {
                btn_skip.setVisibility(View.GONE);
            }
        }
        String totaltask = taskInfoBean.getData().getTotalTask() + "";
        String currenttask = taskInfoBean.getData().getCurrentTaskNo() + "";
        txt_total_task.setText(totaltask);
        progressbar.setMax(Integer.parseInt(totaltask));
        progressbar.setProgress(Integer.parseInt(currenttask));

        seekbarthumb.setProgress(Integer.parseInt(currenttask));
        seekbarthumb.setMax(Integer.parseInt(totaltask));
        seekbarthumb.setEnabled(false);


        seekbarvalue.setMax(Integer.parseInt(totaltask));
        seekbarvalue.setEnabled(false);

        if (Integer.parseInt(currenttask) == 0 || (Integer.parseInt(currenttask)) == (Integer.parseInt(totaltask))) {
            seekbarvalue.setVisibility(View.GONE);
        } else {
            seekbarvalue.setProgress(Integer.parseInt(currenttask));
            seekbarvalue.setThumb(getThumb(Integer.parseInt(currenttask)));
            seekbarvalue.setVisibility(View.VISIBLE);

        }
        if (Const.isGameCompleted.equals(Const.TRUE)) {
            initView();
            progressbar.setProgress(Integer.parseInt(currenttask));

            seekbarthumb.setProgress(Integer.parseInt(currenttask));

        } else {
            Const.currentTASKInfo = taskInfoBean;
            current_taskID = taskInfoBean.getData().getTaskinfo().getTaskId();
            current_playID = prefBean.getPlayID();
            currentAnswer_TYPE = taskInfoBean.getData().getTaskinfo().getAnswer();
            String current_tasktype = taskInfoBean.getData().getTaskinfo().getTaskType1();
            if (current_tasktype.equals("1")) {
                txt_task_type.setText("Discover");
            } else {
                txt_task_type.setText("Crack It");
            }

//            if(taskInfoBean.getData().getTaskinfo().getLatitude()!=null ||
//            taskInfoBean.getData().getTaskinfo().getLongitude()!=null){
//                checkMyLocation();
//            }
            Const.LOGIN_INFO = taskInfoBean.getData().getLogininfo();
            TaskInfoBean.Taskinfo currentTaskData = taskInfoBean.getData().getTaskinfo();
            txt_clue.setText(getString(R.string.CLUES) + "  (" + currentTaskData.getRemainingClue() + ")");
            txt_clue.setPaintFlags(txt_clue.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            manageInstructionTask(currentTaskData);
            manageCurrentTask(currentTaskData);
            displayScore(taskInfoBean.getData().getGameScore());

        }


    }

    private void displayScore(String message) {
        List<String> totalLetter = new ArrayList<>();
        for (int m = 0; m < message.length(); m++) {
            String currentValue = String.valueOf(message.charAt(m));
            totalLetter.add(currentValue);
        }
        TextViewAdapter adapter = new TextViewAdapter(context, totalLetter, Const.PLAIN);
        customTextRecycler.setAdapter(adapter);

    }

    public Drawable getThumb(int progress) {
        ((TextView) thumbView.findViewById(R.id.txt_current_task)).setText(progress + "");

        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight());
        thumbView.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void manageInstructionTask(TaskInfoBean.Taskinfo currentTaskData) {
        if (currentTaskData.getInstruction1Data().equals("") && currentTaskData.getInstruction2Data().equals("")) {
            instructionView.setVisibility(View.GONE);
        } else {

            List<InstructionBean> instructionList = new ArrayList<>();
            if (!currentTaskData.getInstruction1Data().equals("")) {
                instructionList.add(new InstructionBean(currentTaskData.getInstruction1(),
                        currentTaskData.getInstruction1MediaType(),
                        currentTaskData.getInstruction1Data()));
            }
            if (!currentTaskData.getInstruction2Data().equals("")) {
                instructionList.add(new InstructionBean(currentTaskData.getInstruction2(),
                        currentTaskData.getInstruction2MediaType(),
                        currentTaskData.getInstruction2Data()));
            }

            for (int i = 0; i < instructionList.size(); i++) {
                Log.e(TAG, "manageInstructionTask: " + instructionList.get(i).getInstruction_data() + "Media Type: " + instructionList.get(i).getInstruction_media_type());
            }
            setUpInstructionAdapter(instructionList);

        }
    }

    private void setUpInstructionAdapter(List<InstructionBean> instructionList) {
        if (instructionList.size() > 0) {
            InstructionViewAdpater adpater = new InstructionViewAdpater(context, instructionList);
            instructionView.setAdapter(adpater);
            instructionView.setVisibility(View.VISIBLE);
        } else {
            instructionView.setVisibility(View.GONE);
        }

    }

    private void manageCurrentTask(TaskInfoBean.Taskinfo currentTaskData) {
        if (currentTaskData.getAnswer().equals("1")) {
            media_answer.setVisibility(View.GONE);
            txt_your_answer.setVisibility(View.VISIBLE);
        } else if (currentTaskData.getAnswer().equals("2")) {
            media_answer.setVisibility(View.VISIBLE);
            txt_your_answer.setVisibility(View.GONE);

        }
        if (currentTaskData.getTaskType1().equals("1")) {
            //Discover Task Code
            manageDiscoverTask(currentTaskData);
            if (currentTaskData.getMapType().equals("1")) {
                displayMap(currentTaskData);
            }


        } else if (currentTaskData.getTaskType1().equals("2")) {
            //Crack It Task Code
            fragmentDiscover.setVisibility(View.GONE);


        }
        manageAnswer(currentTaskData);

    }

    private void displayMap(TaskInfoBean.Taskinfo currentTaskData) {

        endLat = Double.valueOf(currentTaskData.getLatitude());
        endLong = Double.valueOf(currentTaskData.getLongitude());
        LatLng endLatLng = new LatLng(endLat, endLong);

        points.add(endLatLng);
        points.add(endLatLng);


//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        if (mMap == null) {
            SupportMapFragment mapFragment = (WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }


    }

    private void manageAnswer(TaskInfoBean.Taskinfo currentTaskData) {
        if (currentTaskData.getAnswer().equals("1")) {
            TaskInfoBean.AnswerData currentAnswerData = currentTaskData.getAnswerData();
            if (currentTaskData.getAnswerType().equals("1")) {
                manageMultipleChoiceAnswer(currentAnswerData);
                multipleAnsRecycler.setVisibility(View.VISIBLE);
                edt_single_ans.setVisibility(View.GONE);
            } else if (currentTaskData.getAnswerType().equals("2")) {
                multipleAnsRecycler.setVisibility(View.GONE);
                edt_single_ans.setVisibility(View.VISIBLE);

            }
        } else if (currentTaskData.getAnswer().equals("2")) {
            multipleAnsRecycler.setVisibility(View.GONE);
            edt_single_ans.setVisibility(View.GONE);
            //Confusion Regarding Answer
        }
    }

    private void manageMultipleChoiceAnswer(TaskInfoBean.AnswerData currentAnswerData) {
        answerList = new ArrayList<>();

        answerList.add(new AnswerBean("A", currentAnswerData.getA(), false));
        answerList.add(new AnswerBean("B", currentAnswerData.getB(), false));
        answerList.add(new AnswerBean("C", currentAnswerData.getC(), false));
        answerList.add(new AnswerBean("D", currentAnswerData.getD(), false));

        setUpAnswerAdapter();

    }

    private void setUpAnswerAdapter() {
        AnswerAdapter answerAdapter = new AnswerAdapter(context, answerList, this);
        multipleAnsRecycler.setAdapter(answerAdapter);
        answerAdapter.notifyDataSetChanged();

    }


    private void manageDiscoverTask(TaskInfoBean.Taskinfo currentTaskData) {
        fragmentDiscover.setVisibility(View.VISIBLE);
        fragmentFinish.setVisibility(View.GONE);

        if (currentTaskData.getMapType().equals("1")) {
//            String iframe= "<iframe src="+currentTaskData.getMapdata()+" width=100% height=200 frameborder=0 style=border:0</iframe>";
//            dynamic_map.getSettings().setJavaScriptEnabled(true);
//            dynamic_map.loadDataWithBaseURL(null,iframe,"text/html","utf-8",null);
            dynamic_map.setVisibility(View.GONE);
            static_map.setVisibility(View.GONE);
            card_map_image.setVisibility(View.GONE);
            card_map.setVisibility(View.VISIBLE);
        } else if (currentTaskData.getMapType().equals("2")) {
            if (currentTaskData.getMapdata() != null) {
                Glide.with(context)
                        .load(currentTaskData.getMapdata())
                        .into(static_map);

                static_map.setVisibility(View.VISIBLE);
                card_map_image.setVisibility(View.VISIBLE);
            } else {
                static_map.setVisibility(View.GONE);
            }

//            card_map_image.setVisibility(View.VISIBLE);
            dynamic_map.setVisibility(View.GONE);
            card_map.setVisibility(View.GONE);

        }
    }

    @Override
    public void onAnswerViewClick(AnswerBean currentAns) {
        if (currentAns.isCheckStatus()) {
            Toast.makeText(context, "You select " + currentAns.getKey(), Toast.LENGTH_SHORT).show();
            currentAnswer = currentAns.getKey();
        } else {
            Toast.makeText(context, "Please Select One Iteam", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "onAnswerViewClick: " + answerList.toString());
        setUpAnswerAdapter();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .setListener(new WorkaroundMapFragment.OnTouchListener() {
                    @Override
                    public void onTouch() {
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                });
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                float zoomLevel = mMap.getCameraPosition().zoom;
                if (zoomLevel != currentZoom) {
                    currentZoom = zoomLevel;
                }
            }
        });
        checkMyLocation();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setMarker(endLat, endLong, "You have to Reach Here", BitmapDescriptorFactory.HUE_RED, "END");
            }
        }, 2000);


    }

    private void checkMyLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissionList, REQ_PERMISSION);
                return;
            }
        }
        mMap.setMyLocationEnabled(true);
        ismapactivity = true;
        getMyLastLocation();


    }

    private void getMyLastLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria locationCritera = new Criteria();
        locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
        locationCritera.setAltitudeRequired(false);
        locationCritera.setBearingRequired(false);
        locationCritera.setCostAllowed(true);
        locationCritera.setPowerRequirement(Criteria.NO_REQUIREMENT);

        String providerName = locationManager.getBestProvider(locationCritera, true);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(providerName);

        try {
            setMarker(location.getLatitude(), location.getLongitude(), "You are Here", BitmapDescriptorFactory.HUE_VIOLET, "START");
            LatLng curLatLang = new LatLng(location.getLatitude(), location.getLongitude());
            LatLng endLatLang = points.get(0);
            points.set(1, curLatLang);
            Log.i("--- nimesh", "" + location.getLatitude());
            Log.i("--- nimesh", "" + location.getLongitude());
            double distance = SphericalUtil.computeDistanceBetween(curLatLang, endLatLang);

            int remainingDistance = (int) distance;
            if (prevRemainingDistance == 0) {
                prevRemainingDistance = remainingDistance;
                txt_distance.setText(prevRemainingDistance + " m");
            }

            if (prevRemainingDistance > remainingDistance - 5) {
                prevRemainingDistance = remainingDistance;
//                Toast.makeText(this, " remaining Distance : " +remainingDistance +" m", Toast.LENGTH_LONG).show();
            }

            if (remainingDistance > 1000) {
                Double cDistance = distance / 1000;

                finaldistance = new DecimalFormat("##.##").format(cDistance) + " km";
            } else {
                finaldistance = remainingDistance + " m";
                if (remainingDistance < 500) {
                    if (currentZoom == 13.0F) {
                        currentZoom = 17.0F;
                    }
                }

            }
            txt_distance.setText(context.getString(R.string.remaining_distance) + " " + finaldistance);

            Handler handler = new Handler();

            if (distance < 5) {
                if (vibrateCount < 4) {
                    vibrateMyPhone();
                    notifyme();
                }

                setMarker(endLat, endLong, "You have to Reach Here", BitmapDescriptorFactory.HUE_GREEN, "END");
            }

//        else {
//            if (lastlocation == distance) {
//                Toast.makeText(this, "You Rich at your Place", Toast.LENGTH_SHORT).show();
//            } else {
//                lastlocation = distance;
            int currentdistance = (int) distance;
            if (currentdistance != 0) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ismapactivity) {
                            getMyLastLocation();
                        }
                    }
                }, 1000);
            }

//            }

//        }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void notifyme() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMarker(double latitude, double longitude, String mytitle, Float markercolor, String action) {
        LatLng myloc = new LatLng(latitude, longitude);
        Marker tempMarker;
        // vibrateMyPhone();
        tempMarker = mMap.addMarker(new MarkerOptions()
                .position(myloc)
                .title(mytitle)
                .icon(BitmapDescriptorFactory.defaultMarker(markercolor)));

        if (action.equals("END")) {
            if (mEndLocationMarker != null) {
                mEndLocationMarker.remove();
            }
            mEndLocationMarker = tempMarker;
        }
        if (action.equals("START")) {
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }
            mCurrLocationMarker = tempMarker;
        }

        Log.e(TAG, "setMarker: CurrentZoom -=> " + currentZoom);

//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(myloc)
//                .zoom(currentZoom)
//                .bearing(90)
//                .tilt(30)
//                .build();
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, currentZoom));
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean openDialogOnce = true;
        if (requestCode == REQ_PERMISSION) {
            boolean isPermitted = false;
            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];

                isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;

                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                    if (!showRationale) {
                        //execute when 'never Ask Again' tick and permission dialog not show
                    } else {
                        if (openDialogOnce) {
                            alertView();
                        }
                    }
                }
            }
            initView();


        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_yomba) {
            // checkMyLocation();
            captureImage();
        } else if (view.getId() == R.id.btn_next) {
            if (displayDialog != null) {
                displayDialog.dismiss();
            }
            if (Const.isGameCompleted.equals(Const.TRUE)) {
                GoFrom(context, summeryActivity);
                finish();
            } else {

                if (Const.currentTASKInfo.getData().getTaskinfo().getAnswer().equals("1")) {


                    if (Const.currentTASKInfo.getData().getTaskinfo().getAnswerType().equals("2")) {
                        currentAnswer = edt_single_ans.getText().toString();
                        edt_single_ans.setText("");
                    }
                    if (currentAnswer.equals("")) {
                        AlertMe(context, getString(R.string.play_msg1), null, false);
                    } else {
                        verifyTaskAnswer();
                    }


                } else {
                    current_skip = Const.FALSE;
                    if (Const.BOS != null) {
                        verifyTaskAnswer();
                    } else {
                        AlertMe(context, getString(R.string.play_msg2) +
                                getString(R.string.play_msg3), null, false);
                    }

                }
            }


        } else if (view.getId() == R.id.txt_clue) {
            if (Const.isGameCompleted.equals(Const.TRUE)) {
                GoFrom(context, summeryActivity);
                finish();
            } else {
                if (Const.currentTASKInfo != null) {
                    isAlreadyDisplay = true;
                    processClueData();
                }
            }


        } else if (view.getId() == R.id.btn_skip) {
            current_skip = Const.TRUE;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    verifyTaskAnswer();
                }
            };
            AlertMe(context, getString(R.string.play_msg4), runnable, true);


        } else if (view.getId() == R.id.txt_solve) {

            manageSolveAction();
//            long remaining_clue;
//            if (clueBean != null) {
//                remaining_clue = Const.TotalClue - Integer.parseInt(clueBean.getData().getClueNumber());
//            } else {
//                remaining_clue = Const.currentTASKInfo.getData().getTaskinfo().getRemainingClue();
//            }
//            if (remaining_clue == 0) {

//            } else {
//                AlertMe(context, "Visit All Clue First", null, false);
//            }
        } else if (view.getId() == R.id.linear_exit) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    startNewGame();
                }
            };
            AlertMe(context, getString(R.string.play_msg5), runnable, true);
        } else if (view.getId() == R.id.card_finish) {
            GoFrom(context, summeryActivity);
            finish();
        }
    }

    private void manageSolveAction() {
        isAlreadyDisplay = true;
        processOpenSolve();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                selectAnsweronSkip();
            }
        };
        AlertMe(context, Const.currentTASKInfo.getData().getTaskinfo().getAnswerExplanation(), runnable, false);
    }

    private void selectAnsweronSkip() {
        String answer_main_type = Const.currentTASKInfo.getData().getTaskinfo().getAnswer();
        String answer_sub_type = Const.currentTASKInfo.getData().getTaskinfo().getAnswerType();
        String final_answer = Const.currentTASKInfo.getData().getTaskinfo().getFinalAnswer();
        currentAnswer = final_answer;
        if (answer_main_type.equals("1") && answer_sub_type.equals("2")) {
            edt_single_ans.setText(final_answer);
        } else if (answer_main_type.equals("1") && answer_sub_type.equals("1")) {
            AnswerBean currentanswer = null;
            int position = 0;
            answerList.get(0).setCheckStatus(false);
            answerList.get(1).setCheckStatus(false);
            answerList.get(2).setCheckStatus(false);
            answerList.get(3).setCheckStatus(false);
            if (final_answer.equals("A")) {
                currentanswer = answerList.get(0);
                position = 0;
            } else if (final_answer.equals("B")) {
                currentanswer = answerList.get(1);
                position = 1;
            } else if (final_answer.equals("C")) {
                currentanswer = answerList.get(2);
                position = 2;
            } else if (final_answer.equals("D")) {
                currentanswer = answerList.get(3);
                position = 3;
            }

            currentanswer.setCheckStatus(true);
            answerList.set(position, currentanswer);
            setUpAnswerAdapter();
        }
        btn_skip.setVisibility(View.GONE);
    }

    private void processOpenSolve() {
        new OpenSolve(context, Const.currentTASKInfo.getData().getLogininfo().getPlayId(), Const.currentTASKInfo.getData().getTaskinfo().getTaskId(), this).execute();
    }

    private void startNewGame() {
        relative_progress.setVisibility(View.VISIBLE);
        new LogoutGame(context, prefBean.getScenario(), prefBean.getOrderID(), prefBean.getLoginID(), this).execute();

    }


    private void processClueData() {
//        displayClue();
        relative_progress.setVisibility(View.VISIBLE);
        new FetchClue(context, prefBean.getLoginID(), prefBean.getUserID(), prefBean.getPlayID(), current_taskID, prefBean.getScenario(), this).execute();

    }


    private void verifyTaskAnswer() {
        ismapactivity = false;
        relative_progress.setVisibility(View.VISIBLE);
        new VerifyTaskAns(context, prefBean.getLoginID(), prefBean.getUserID(), current_playID, current_taskID, prefBean.getScenario(), currentAnswer, current_skip, currentAnswer_TYPE, this).execute();
    }


    private void alertView() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(getString(R.string.play_msg6))
                .setInverseBackgroundForced(true)
                //.setIcon(R.drawable.ic_info_black_24dp)
                .setMessage(getString(R.string.play_msg7))

                .setNegativeButton(getString(R.string.play_msg8), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.play_msg9), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                        checkMyLocation();
                    }
                }).show();
    }


    private void captureImage() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, INTENT_CAMERA_CODE);
            }
        }


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "temp";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );


        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public static Bitmap scaleDown(Bitmap realImage, int width, int height) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, false);
        return newBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == INTENT_CAMERA_CODE) {

            if (resultCode == RESULT_OK) {

                logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_yomba);
                try {
                    Log.e(TAG, "onActivityResult: " + currentPhotoPath);
                    imageBitmap = null;
                    File f = new File(currentPhotoPath);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Const.SelectedFileName = f.getName();
                    imageBitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                new manageBitMap().execute();


            } else if (resultCode == RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.user_cancelled_image_capture), Toast.LENGTH_SHORT).show();

            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.sorry_failed_to_capture_image), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onOpenSolve(UserInfo currentUserInfo) {
        if (currentUserInfo.getStatus() == 1) {
            Log.e(TAG, "onOpenSolve: " + currentUserInfo.getMessage());
        }
    }

    @Override
    public void onLogoutGame(UserInfo logoutGame) {
        relative_progress.setVisibility(View.GONE);
        if (logoutGame.getStatus() == 1) {
            EnterCodeActivity enterCodeActivity = new EnterCodeActivity();
            prefBean = new PrefBean();
            sharedPrefrenceManager.setSharedPreferences(prefBean);
            GoFrom(context, enterCodeActivity);
            finish();
        } else {
            Toast.makeText(context, getString(R.string.play_msg10), Toast.LENGTH_SHORT).show();
        }
    }


    public class manageBitMap extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            finalBitmap = Bitmap.createBitmap(imageBitmap.getWidth(), imageBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(finalBitmap);


            logoBitmap = scaleDown(logoBitmap, 440, 440);

            logoBitmap.setHasAlpha(true);

            canvas.drawBitmap(imageBitmap, 0, 0, null);
            canvas.drawBitmap(logoBitmap, 100, 100, null);


//                static_map.setImageBitmap(finalBitmap);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Glide.with(context)
                    .load(finalBitmap)
                    .into(img_ans);
            img_ans.setVisibility(View.VISIBLE);
            Const.BOS = new ByteArrayOutputStream();

            card_media_answer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void OnVerifyTaskAns(TaskInfoBean taskInfoBean) {
        relative_progress.setVisibility(View.GONE);
        if (taskInfoBean.getStatus() == 1) {
            if (taskInfoBean.getData().getIsTaskVerified().equals(Const.TRUE)) {
                String currentGameCompleted = Const.FALSE;
                if (taskInfoBean.getData().getIsGameCompleted() != null) {
                    currentGameCompleted = taskInfoBean.getData().getIsGameCompleted();
                }
                if (currentGameCompleted.equals(Const.FALSE)) {
                    if (current_skip.equals(Const.FALSE)) {
                        showRightAlert();
                    } else {
                        initView();
                    }

                } else {
                    if (taskInfoBean.getData().getIsGameCompleted().equals(Const.TRUE)) {
                        Const.currentTASKInfo = taskInfoBean;
                        Log.e(TAG, "completedTask: " + taskInfoBean.toString());
                        String currenttask = taskInfoBean.getData().getCurrentTaskNo() + "";
                        progressbar.setProgress(Integer.parseInt(currenttask));

                        seekbarthumb.setProgress(Integer.parseInt(currenttask));
                    }
                    initView();

                }
            } else {


                edt_single_ans.setText("");
                //AlertMe(context, taskInfoBean.getMessage(), null);
                showWrongAlert();


            }

        } else {
            AlertMe(context, taskInfoBean.getMessage(), null, false);
        }
    }

    private void showWrongAlert() {
        dismissAlert();
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(R.layout.custom_wrong_alert_dialog,
                null);
        //  Here is main code to bind xml with java
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layoutView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        Button btn_retry;
        TextView txt_worng;
        btn_retry = layoutView.findViewById(R.id.btn_retry);
        txt_worng = layoutView.findViewById(R.id.txt_worng);
        txt_worng.setText(Const.currentTASKInfo.getData().getTaskinfo().getNegativeFeedback() + "");
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                if (Const.RemainingClue != 0) {
                    if (worngPopupOpen > 1) {
                        isAlreadyDisplay = true;
                        worngPopupOpen = 0;
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (Const.isGameCompleted.equals(Const.TRUE)) {
                                    GoFrom(context, summeryActivity);
                                    finish();
                                } else {
                                    if (Const.currentTASKInfo != null) {
                                        isAlreadyDisplay = true;
                                        processClueData();
                                    }
                                }
                            }
                        };
                        ManageClue(context, getString(R.string.dontforgettoopenclue), runnable, true);
                    } else {
                        worngPopupOpen++;
                    }
                } else {
                    if (worngPopupOpen > 1) {
                        isAlreadyDisplay = true;
                        worngPopupOpen = 0;
                        current_skip = Const.TRUE;
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                manageSolveAction();
                            }
                        };
                        ManageClue(context, getString(R.string.noclueleft), runnable, true);
                    } else {
                        worngPopupOpen++;
                    }
                }

            }
        }, 3000);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        if (Const.currentTASKInfo.getData().getTaskinfo().getRemainingClue() == Const.currentTASKInfo.getData().getTaskinfo().getTotalClue()) {
            worngPopupOpen = worngPopupOpen + 1;
        }

    }

    private void showRightAlert() {
        dismissAlert();
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(R.layout.custom_right_alert_dialog,
                null);
        //  Here is main code to bind xml with java
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layoutView);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        Button btn_right;
        TextView txt_amazing;
        btn_right = layoutView.findViewById(R.id.btn_right);
        txt_amazing = layoutView.findViewById(R.id.txt_amazing);
        txt_amazing.setText(Const.currentTASKInfo.getData().getTaskinfo().getPositiveFeedback() + "");
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                initView();
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                openAnswerExplanation();
            }
        }, 3000);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void dismissAlert() {
        if (displayDialog != null) {
            displayDialog.dismiss();
        }
        CommonMethod.dismissAlertDialog();
    }

    private void openAnswerExplanation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                initView();
            }
        };

        AlertMe(context, Const.currentTASKInfo.getData().getTaskinfo().getAnswerExplanation(), runnable, false);
    }

    @Override
    public void onFetchClueClick(ClueBean clueBean) {
        relative_progress.setVisibility(View.GONE);
        this.clueBean = clueBean;
        if (clueBean.getStatus() == 1) {
            Long remaining_clue = clueBean.getData().getRemainingClue();
            Const.RemainingClue = remaining_clue;
            Log.e(TAG, "onFetchClueClick: " + remaining_clue);

                txt_clue.setText(getString(R.string.CLUES) + "  (" + remaining_clue + ")");

            txt_clue.setPaintFlags(txt_clue.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            if (displayDialog != null) {
                displayDialog.dismiss();
            }
            CommonMethod.dismissAlertDialog();
            displayClue();
        } else {
            AlertMe(context, clueBean.getMessage(), null, false);
        }
    }

    private void displayClue() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutInflateView = layoutInflater.inflate(R.layout.clue_raw_item, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layoutInflateView);
        displayDialog = builder.create();
        TextView txt_clue_no;
        Button btn_ok;
        ImageView image_media_clue;
        TextView txt_media_clue;
        final VideoView video_media_clue;

        txt_clue_no = layoutInflateView.findViewById(R.id.txt_clue_no);
        btn_ok = layoutInflateView.findViewById(R.id.btn_ok);
        image_media_clue = layoutInflateView.findViewById(R.id.image_media_clue);
        txt_media_clue = layoutInflateView.findViewById(R.id.txt_media_clue);
        video_media_clue = layoutInflateView.findViewById(R.id.video_media_clue);
        txt_clue_no.setText(getString(R.string.get_your_clue) + " " + clueBean.getData().getClueNumber());
        String currentMediaType = clueBean.getData().getClueMediaType();
        if (currentMediaType.equals("")) {
            txt_media_clue.setText(clueBean.getData().getClueData());
            txt_media_clue.setVisibility(View.VISIBLE);
            image_media_clue.setVisibility(View.GONE);
            video_media_clue.setVisibility(View.GONE);
        } else if (currentMediaType.equals("1") || currentMediaType.equals("2") || currentMediaType.equals("3")) {
            if (currentMediaType.equals("2")) {

                Glide.with(context)
                        .load(clueBean.getData().getClueMediaType())
                        .into(image_media_clue);
                Glide.with(context)
                        .load(context.getResources().getDrawable(R.drawable.ic_media_image))
                        .into(image_media_clue);
                final MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(clueBean.getData().getClueMediaType());
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image_media_clue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mp.isPlaying()) {
                            mp.pause();
                        } else {
                            mp.start();
                        }
                    }
                });
            } else {
                Glide.with(context)
                        .load(clueBean.getData().getClueData())
                        .into(image_media_clue);
            }
            txt_media_clue.setVisibility(View.GONE);
            image_media_clue.setVisibility(View.VISIBLE);
            video_media_clue.setVisibility(View.GONE);
        } else if (currentMediaType.equals("4")) {
            video_media_clue.setVideoPath(clueBean.getData().getClue());
            video_media_clue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (video_media_clue.isPlaying()) {
                        video_media_clue.pause();
                    } else {
                        video_media_clue.start();
                    }
                }
            });
            txt_media_clue.setVisibility(View.GONE);
            image_media_clue.setVisibility(View.GONE);
            video_media_clue.setVisibility(View.VISIBLE);
        }
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog.dismiss();
            }
        });


        displayDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        displayDialog.setCancelable(false);
        displayDialog.show();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String call_api = intent.getStringExtra("call_api");
            String play_id = intent.getStringExtra("play_id");
            String clue_number = intent.getStringExtra("clue_number");
            String title = intent.getStringExtra("title");
            Log.e(TAG, "onMessageReceived: clue api " + call_api);
            Log.e(TAG, "onMessageReceived: play id " + play_id);
            Log.e(TAG, "onMessageReceived:clue number " + clue_number);
            if (call_api.equals("open_clue")) {

                if (Const.currentTASKInfo != null) {
                    processClueData();
                }

//                ClueBean tempClueBean=new ClueBean();
//                if(clue_number.equalsIgnoreCase("1")){
//
//                    tempClueBean.setData(new ClueBean.Data("1",
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue1MediaType(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue1Data(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue1()));
//                }else if(clue_number.equals("2")){
//                    tempClueBean.setData(new ClueBean.Data("2",
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue2MediaType(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue2Data(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue2()));
//                }else if(clue_number.equals("3")){
//                    tempClueBean.setData(new ClueBean.Data("3",
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue3MediaType(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue3Data(),
//                            Const.currentTASKInfo.getData().getTaskinfo().getClue3()));
//                }
//                Log.e(TAG, "onMessageReceived: "+tempClueBean.toString() );
//                int remaining_clue = Const.TotalClue-Integer.parseInt(clue_number);
//                if(remaining_clue ==0){
//                    btn_clue.setText(getString(R.string.CLUES));
//                } else{
//                    btn_clue.setText(remaining_clue+" "+getString(R.string.CLUES));
//                }
//                clueBean=tempClueBean;
//                displayClue();


            } else if (call_api.equals("task_status")) {
//                initView();
                showRightAlert();
            } else if (call_api.equals("game_completed")) {

                relative_progress.setVisibility(View.GONE);
                media_answer.setVisibility(View.GONE);
                fragmentCrackIt.setVisibility(View.GONE);
                fragmentDiscover.setVisibility(View.GONE);
                fragmentFinish.setVisibility(View.VISIBLE);
                instructionView.setVisibility(View.GONE);
                // visitSummery();
            }

            Log.e(TAG, "onMessageReceived: Title" + title);
        }
    };

    private void visitSummery() {
        GoFrom(this, summeryActivity);
        finish();
    }

    private void vibrateMyPhone() {
        long[] VIBRATE_PATTERN = {500, 1000, 1500};
        vibrateCount++;
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(VIBRATE_PATTERN, -1));
        } else {
            vibrator.vibrate(VIBRATE_PATTERN, -1);
        }
    }
}
