package com.yomba.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yomba.R;
import com.yomba.model.AnswerBean;
import com.yomba.model.Const;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.OnAnswerViewHolder> {

    Context context;
    List<AnswerBean> answerList;
    OnAnswerViewClickListener listener;
    public AnswerAdapter(Context context, List<AnswerBean> answerList,OnAnswerViewClickListener listener) {
        this.context=context;
        this.answerList=answerList;
        this.listener=listener;
    }
    public  interface OnAnswerViewClickListener{
        void onAnswerViewClick(AnswerBean currentAns);
    }

    @NonNull
    @Override
    public OnAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_raw_item,null);
        OnAnswerViewHolder viewHolder=new OnAnswerViewHolder(view);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final OnAnswerViewHolder holder, final int position) {
        final int[] imagePath=new int[1];
        final AnswerBean currentAns = answerList.get(position);
        holder.text_ans.setText(currentAns.getValue());
        final String currentKey=currentAns.getKey();
        if(!currentAns.isCheckStatus()) {
            switch (currentKey) {
                case Const.A:
                    imagePath[0] = R.drawable.ic_a;

                    break;
                case Const.B:
                    imagePath[0] = R.drawable.ic_b;

                    break;
                case Const.C:
                    imagePath[0] = R.drawable.ic_c;

                    break;
                case Const.D:
                    imagePath[0] = R.drawable.ic_d;

                    break;
            }
        }else{
            switch (currentKey) {
                case Const.A:
                    imagePath[0] = R.drawable.a;

                    break;
                case Const.B:
                    imagePath[0] = R.drawable.b;

                    break;
                case Const.C:
                    imagePath[0] = R.drawable.c;

                    break;
                case Const.D:
                    imagePath[0] = R.drawable.d;

                    break;
            }
        }
        holder.chbRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentAns.isCheckStatus()){
                    currentAns.setCheckStatus(false);
                }else {
                    for(int i=0;i<answerList.size();i++) {
                        if(i!=position){
                            answerList.get(i).setCheckStatus(false);
                        }
                        currentAns.setCheckStatus(true);
                    }
                }
                listener.onAnswerViewClick(currentAns);
            }
        });
        holder.chb_icon.setBackgroundResource(imagePath[0]);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class OnAnswerViewHolder extends RecyclerView.ViewHolder{
        TextView text_ans;
        ImageView chb_icon;
        LinearLayout chbRoot;
        public OnAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            chbRoot=itemView.findViewById(R.id.chbRoot);
            text_ans=itemView.findViewById(R.id.text_ans);
            chb_icon=itemView.findViewById(R.id.chb_icon);
        }
    }
}
