package com.yomba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yomba.R;
import com.yomba.model.Const;

import java.util.List;

public class SummaryTextViewAdapter extends RecyclerView.Adapter<SummaryTextViewAdapter.OnSummaryTextViewHolder> {

    Context context;
    List<String> totalLetter;
    String textType;

    public SummaryTextViewAdapter(Context context, List<String> totalLetter, String textType) {
        this.context = context;
        this.totalLetter = totalLetter;
        this.textType = textType;
    }

    @NonNull
    @Override
    public OnSummaryTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_raw_item,parent,false);
        OnSummaryTextViewHolder viewHolder=new OnSummaryTextViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnSummaryTextViewHolder holder, int position) {
        String currentText = totalLetter.get(position);
        holder.txt_value.setText(currentText);
        if(textType.equals(Const.PLAIN)){
            holder.txt_colon.setVisibility(View.GONE);
        }else{

                if(currentText.equals(":")) {

                    if (position != totalLetter.size() - 1) {
                        holder.txt_colon.setVisibility(View.VISIBLE);
                        holder.txt_value.setVisibility(View.GONE);
                    }
                }

        }
    }

    @Override
    public int getItemCount() {
        return totalLetter.size();
    }

    public class OnSummaryTextViewHolder  extends RecyclerView.ViewHolder{
        TextView txt_value,txt_colon;
        public OnSummaryTextViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_value=itemView.findViewById(R.id.txt_value);
            txt_colon=itemView.findViewById(R.id.txt_colon);
        }
    }
}
