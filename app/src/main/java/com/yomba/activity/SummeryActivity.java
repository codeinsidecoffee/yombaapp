package com.yomba.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yomba.R;
import com.yomba.adapter.SummaryDetailsAdapter;
import com.yomba.adapter.SummaryTextViewAdapter;
import com.yomba.adapter.TextViewAdapter;
import com.yomba.dao.FetchSummary;
import com.yomba.model.Const;
import com.yomba.model.GameScoreDetail;
import com.yomba.model.PrefBean;
import com.yomba.model.SummaryBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.yomba.utils.CommonMethod.AlertMe;

public class SummeryActivity extends BaseActivity implements FetchSummary.OnFetchSummaryListener, View.OnClickListener {

    Context context;

    RecyclerView overalltime,alltimerank,currentmonthrank,clueused,savemeused,gameplayno,totalscore,final_score,recyclerview_info;
    RecyclerView.LayoutManager overalltimeLayout,alltimerankLayout,currentmonthrankLayout,clueusedLayout,savemeusedLayout,gameplaynoLayout,totalscoreLayout,final_scoreLayout,recyclerview_infoLayout;
    ImageView img_info;
    RelativeLayout relative_info,relative_container;

    private List<GameScoreDetail> gameScoreDetails;
    String yourScore;
    private AlertDialog summaryDialog;
    ImageView img_facebook,img_insta,img_whatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_summery, frameLayout);
        context=this;

        prefBean=sharedPrefrenceManager.getSharedPreferences();
        initView();
        fetchSummaryData();

    }

    @SuppressLint("WrongConstant")
    private  void initView(){
        overalltime=findViewById(R.id.overalltime);
        overalltimeLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        overalltime.setLayoutManager(overalltimeLayout);

        alltimerank=findViewById(R.id.alltimerank);
        alltimerankLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        alltimerank.setLayoutManager(alltimerankLayout);

        currentmonthrank=findViewById(R.id.currentmonthrank);
        currentmonthrankLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        currentmonthrank.setLayoutManager(currentmonthrankLayout);

        clueused=findViewById(R.id.clueused);
        clueusedLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        clueused.setLayoutManager(clueusedLayout);

        savemeused=findViewById(R.id.savemeused);
        savemeusedLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        savemeused.setLayoutManager(savemeusedLayout);

        gameplayno=findViewById(R.id.gameplayno);
        gameplaynoLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        gameplayno.setLayoutManager(gameplaynoLayout);

        totalscore=findViewById(R.id.totalscore);
        totalscoreLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        totalscore.setLayoutManager(totalscoreLayout);



        img_info=findViewById(R.id.img_info);
        img_info.setOnClickListener(this);


        relative_container=findViewById(R.id.relative_container);
//        relative_info=findViewById(R.id.relative_info);

        relative_container.setVisibility(View.VISIBLE);
//        relative_info.setVisibility(View.GONE);

        img_facebook=findViewById(R.id.img_facebook);
        img_insta=findViewById(R.id.img_insta);
        img_whatsApp=findViewById(R.id.img_whatsApp);

        img_insta.setOnClickListener(this);
        img_facebook.setOnClickListener(this);
        img_whatsApp.setOnClickListener(this);



    }

    private void fetchSummaryData() {
        relative_progress.setVisibility(View.VISIBLE);
        new FetchSummary(context,prefBean.getPlayID(),prefBean.getUserID(),prefBean.getScenario(),this).execute();
        prefBean=new PrefBean();
        prefBean.setWaitForGame(false);
        prefBean.setGameStarted(false);
        prefBean.setGameFinish(true);
        prefBean.setInviteScreen(false);
        sharedPrefrenceManager.setSharedPreferences(prefBean);

    }

    @Override
    public void onFetchSummary(SummaryBean summaryBean) {
        relative_progress.setVisibility(View.GONE);
        gameScoreDetails=summaryBean.getData().getGameScoreDetail();
        if(summaryBean.getStatus()==1){
            manageSummary(summaryBean);
        }else{
            AlertMe(context, summaryBean.getMessage(), null, false);
        }

    }

    private void manageSummary(SummaryBean summaryBean) {
        relative_progress.setVisibility(View.VISIBLE);
        displayScore(summaryBean.getData().getOverallTime(),"OverAllTime");
        displayScore(summaryBean.getData().getAllTimeRank(),"AllTimeRank");
        displayScore(summaryBean.getData().getCurrentMonthRank(),"CurrentMonthRank");
        displayScore(summaryBean.getData().getClueUsed(),"ClueUsed");
        displayScore(summaryBean.getData().getSavemeUsed(),"SaveMeUsed");
        displayScore(summaryBean.getData().getTotalPlayedGames(),"GamePlayNo");
        displayScore(summaryBean.getData().getGameScore(),"TotalScore");
        yourScore=summaryBean.getData().getGameScore();
        relative_progress.setVisibility(View.GONE);

    }

    private void displayScore(String message,String textSection) {
        List<String> totalLetter=new ArrayList<>();
        for(int m=0;m<message.length();m++){
            String currentValue= String.valueOf(message.charAt(m));
            totalLetter.add(currentValue);
        }




        if(textSection.equals("TotalScore")){
            TextViewAdapter currentAdapter=new TextViewAdapter(context,totalLetter,Const.PLAIN);
            totalscore.setAdapter(currentAdapter);
            currentAdapter.notifyDataSetChanged();
        }else if(textSection.equals("FinalScore")){
            TextViewAdapter currentAdapter=new TextViewAdapter(context,totalLetter,Const.PLAIN);
            final_score.setAdapter(currentAdapter);
            currentAdapter.notifyDataSetChanged();
        }else{
            SummaryTextViewAdapter adapter=new SummaryTextViewAdapter(context,totalLetter,Const.PLAIN);
            if(textSection.equals("OverAllTime")){
                adapter=new SummaryTextViewAdapter(context,totalLetter,Const.CLOCK);
                overalltime.setAdapter(adapter);
            }
            else if(textSection.equals("AllTimeRank")){
                alltimerank.setAdapter(adapter);
            }else if(textSection.equals("CurrentMonthRank")){
                currentmonthrank.setAdapter(adapter);
            } else if(textSection.equals("ClueUsed")){
                clueused.setAdapter(adapter);
            } else if(textSection.equals("SaveMeUsed")){
                savemeused.setAdapter(adapter);
            } else if(textSection.equals("GamePlayNo")){
                gameplayno.setAdapter(adapter);
            }

            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.img_info){
//            relative_info.setVisibility(View.VISIBLE);
//            relative_container.setVisibility(View.GONE);
            manageDetailSummaryAdapter();

        }else if(view.getId()==R.id.img_insta){
            postInInstagram(takeSnapshot());
        }else if(view.getId()==R.id.img_facebook){
            postInFacebook(takeSnapshot());

        }else if(view.getId()==R.id.img_whatsApp){
            postInWhatsApp(takeSnapshot());

        }
    }

    private void postInFacebook(File imagePath) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (intent != null)
        {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setPackage("com.facebook.katana");
            try {
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), imagePath.getPath(), "I am Happy", "Share happy !")));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            shareIntent.setType("image/jpeg");

            startActivity(shareIntent);
        }
        else
        {
            // bring user to the market to download the app.
            // or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id="+"com.facebook.katana"));
            startActivity(intent);
        }

    }

    private void postInWhatsApp(File imagePath) {
        Uri imgUri = Uri.parse(imagePath.getAbsolutePath());
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
        whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
        whatsappIntent.setType("image/jpeg");
        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id="+"com.whatsapp"));
            startActivity(intent);
        }
    }

    private File takeSnapshot() {
        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "temp.jpg";

            // create bitmap screen capture
            View view = getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            return imageFile;
        } catch (Throwable e) {

            // Several error may come out with file handling or DOM
            e.printStackTrace();
            return  null;
        }
    }

    private void postInInstagram(File imagePath) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (intent != null)
        {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setPackage("com.instagram.android");
            try {
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), imagePath.getPath(), "I am Happy", "Share happy !")));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            shareIntent.setType("image/jpeg");

            startActivity(shareIntent);
        }
        else
        {
            // bring user to the market to download the app.
            // or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id="+"com.instagram.android"));
            startActivity(intent);
        }

    }

    @SuppressLint("WrongConstant")
    private void manageDetailSummaryAdapter() {
        if(gameScoreDetails!=null) {

            LayoutInflater layoutInflater=getLayoutInflater();
            View layoutView=layoutInflater.inflate(R.layout.qucik_summary_item, (ViewGroup) findViewById(R.id.mycustomAlert));
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setView(layoutView);

            summaryDialog=builder.create();

            Button btn_ok;

            btn_ok=layoutView.findViewById(R.id.btn_ok);


            final_score=layoutView.findViewById(R.id.final_score);
            final_scoreLayout=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
            final_score.setLayoutManager(final_scoreLayout);


            recyclerview_info=layoutView.findViewById(R.id.recyclerview_info);
            recyclerview_infoLayout=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            recyclerview_info.setLayoutManager(recyclerview_infoLayout);


            SummaryDetailsAdapter adapter = new SummaryDetailsAdapter(context, gameScoreDetails);
            recyclerview_info.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            displayScore(yourScore,"FinalScore");

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    summaryDialog.dismiss();
                }
            });
            summaryDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            summaryDialog.setCancelable(false);
            summaryDialog.show();
        }
    }
}
