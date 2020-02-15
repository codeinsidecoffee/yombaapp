package com.yomba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yomba.R;
import com.yomba.model.GameScoreDetail;

import java.util.List;

public class SummaryDetailsAdapter extends RecyclerView.Adapter<SummaryDetailsAdapter.OnSummaryDetailsViewHolder> {
    Context context;
    List<GameScoreDetail> gameScoreDetails;
    public SummaryDetailsAdapter(Context context, List<GameScoreDetail> gameScoreDetails) {
        this.context=context;
        this.gameScoreDetails=gameScoreDetails;
    }

    @NonNull
    @Override
    public OnSummaryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_details_raw_item,parent,false);
        OnSummaryDetailsViewHolder viewHolder=new OnSummaryDetailsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnSummaryDetailsViewHolder holder, int position) {
        GameScoreDetail curerentTaskInfo = gameScoreDetails.get(position);
        holder.txt_used_clue.setText(curerentTaskInfo.getTotalUsedClue());
        if(curerentTaskInfo.getIsTaskSkip().equals("true")){
            holder.txt_skipped.setText("Yes");
            holder.txt_skipped.setBackgroundResource(R.drawable.terms_condition_shape);
        }else{
            holder.txt_skipped.setText("No");
            holder.txt_skipped.setBackgroundResource(R.drawable.solv_btn_shape);
        }

        if(curerentTaskInfo.getIsOpenSolve().equals("true")){
            holder.txt_used_save_me.setText("Yes");
            holder.txt_used_save_me.setBackgroundResource(R.drawable.terms_condition_shape);
        }else{
            holder.txt_used_save_me.setText("No");
            holder.txt_used_save_me.setBackgroundResource(R.drawable.solv_btn_shape);
        }


        int total_points=Integer.parseInt(curerentTaskInfo.getTaskPoints())+Integer.parseInt(curerentTaskInfo.getTaskConsecutivePoints());
        holder.txt_task_point.setText(curerentTaskInfo.getPoints() +" "+context.getString(R.string.outof)+" "+curerentTaskInfo.getTaskPoints());
        holder.txt_consecutive_point.setText(curerentTaskInfo.getConsecutivePoints() +" "+context.getString(R.string.outof)+" "+curerentTaskInfo.getTaskConsecutivePoints());
        holder.txt_score_per_Task.setText(curerentTaskInfo.getTotalTaskPoints() +" "+context.getString(R.string.outof)+" "+total_points);
        holder.txt_task.setText(curerentTaskInfo.getHeadline());
        holder.txt_wrong_answer.setText(curerentTaskInfo.getTotalWrongAnswer());
    }

    @Override
    public int getItemCount() {
        return gameScoreDetails.size();
    }

    public class OnSummaryDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView txt_skipped,txt_used_clue,txt_used_save_me,txt_task_point,txt_consecutive_point,txt_score_per_Task,txt_task,txt_wrong_answer;
        public OnSummaryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_skipped=itemView.findViewById(R.id.txt_skipped);
            txt_used_clue=itemView.findViewById(R.id.txt_used_clue);
            txt_used_save_me=itemView.findViewById(R.id.txt_used_save_me);
            txt_task_point=itemView.findViewById(R.id.txt_task_point);
            txt_consecutive_point=itemView.findViewById(R.id.txt_consecutive_point);
            txt_score_per_Task=itemView.findViewById(R.id.txt_score_per_Task);
            txt_task=itemView.findViewById(R.id.txt_task);
            txt_wrong_answer=itemView.findViewById(R.id.txt_wrong_answer);
        }
    }
}
