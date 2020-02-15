package com.yomba.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.model.InstructionBean;
import java.io.IOException;
import java.util.List;

public class InstructionViewAdpater extends RecyclerView.Adapter<InstructionViewAdpater.OnInstructionViewHolder> {

    private Context context;
    private List<InstructionBean> instructionList;

    public InstructionViewAdpater(Context context, List<InstructionBean> instructionList) {
        this.context=context;
        this.instructionList=instructionList;

    }


    @NonNull
    @Override
    public OnInstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_raw_item,null);
        OnInstructionViewHolder viewHolder=new OnInstructionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OnInstructionViewHolder holder, int position) {
        InstructionBean currentInstruction = instructionList.get(position);
        String media_type=currentInstruction.getInstruction_media_type();
        if(media_type.equals("")){
            holder.txt_media_data.setText(currentInstruction.getInstruction_data());
            holder.txt_media_data.setVisibility(View.VISIBLE);
            holder.image_media_data.setVisibility(View.GONE);
            holder.video_media_data.setVisibility(View.GONE);
        }else if(media_type.equals("1")|| media_type.equals("2") || media_type.equals("3")){
            if(media_type.equals("2")){
                Glide.with(context)
                        .load(context.getResources().getDrawable(R.drawable.ic_media_image))
                        .into(holder.image_media_data);
                final MediaPlayer mp=new MediaPlayer();
                try {
                    mp.setDataSource(currentInstruction.getInstruction_data());
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                holder.image_media_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mp.isPlaying()){
                            mp.pause();
                        }else{
                            mp.start();
                        }
                    }
                });
            }else{
            Glide.with(context)
                    .load(currentInstruction.getInstruction_data())
                    .into(holder.image_media_data);
            }
            holder.txt_media_data.setVisibility(View.GONE);
            holder.image_media_data.setVisibility(View.VISIBLE);
            holder.video_media_data.setVisibility(View.GONE);
        }else if(media_type.equals("4")){
            holder.video_media_data.setVideoPath(currentInstruction.getInstruction_data());
            holder.video_media_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.video_media_data.isPlaying()){
                        holder.video_media_data.pause();
                    }else{
                        holder.video_media_data.start();
                    }
                }
            });
            holder.txt_media_data.setVisibility(View.GONE);
            holder.image_media_data.setVisibility(View.GONE);
            holder.video_media_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return instructionList.size();
    }

    public class OnInstructionViewHolder  extends RecyclerView.ViewHolder{
        TextView txt_media_data;
        ImageView image_media_data;
        VideoView video_media_data;

        public OnInstructionViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_media_data=itemView.findViewById(R.id.txt_media_data);
            image_media_data=itemView.findViewById(R.id.image_media_data);
            video_media_data=itemView.findViewById(R.id.video_media_data);
        }
    }
}
