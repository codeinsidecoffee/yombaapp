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

public class TextViewAdapter extends RecyclerView.Adapter<TextViewAdapter.OnTextViewHolder> {
    Context context;
    List<String> totalLetter;
    String textType;
    public TextViewAdapter(Context context, List<String> totalLetter, String textType) {
        this.context=context;
        this.totalLetter=totalLetter;
        this.textType=textType;
    }

    @NonNull
    @Override
    public OnTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_text_raw_item,parent,false);
       OnTextViewHolder viewHolder=new OnTextViewHolder(view);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnTextViewHolder holder, int position) {
        String currentText = totalLetter.get(position);
        holder.txt_value.setText(currentText);
        if(textType.equals(Const.PLAIN)){
            holder.txt_colon.setVisibility(View.GONE);

        }else{
            if(position!=totalLetter.size()-1) {
                holder.txt_colon.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return totalLetter.size();
    }

    public class OnTextViewHolder extends RecyclerView.ViewHolder{
            TextView txt_value,txt_colon;

        public OnTextViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_value=itemView.findViewById(R.id.txt_value);
            txt_colon=itemView.findViewById(R.id.txt_colon);
        }
    }
}
