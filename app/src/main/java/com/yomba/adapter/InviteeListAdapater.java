package com.yomba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.model.InviteeListBean;

import java.util.List;

public class InviteeListAdapater extends RecyclerView.Adapter<InviteeListAdapater.OnInviteeListViewHolder> {
    Context context;
    List<InviteeListBean.Friendlist> inviteeListInfo;
    OnInviteeClickListener listener;
    public InviteeListAdapater(Context context, List<InviteeListBean.Friendlist> inviteeListInfo,OnInviteeClickListener listener) {
        this.context=context;
        this.inviteeListInfo=inviteeListInfo;
        this.listener=listener;
    }

    public interface OnInviteeClickListener{
        void onInviteeClick(boolean isChecked, String inviteeId);

        void onInviteeClick();
    }

    @NonNull
    @Override
    public OnInviteeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.master_invitee_list_raw_item,parent,false);
        OnInviteeListViewHolder viewHolder=new OnInviteeListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OnInviteeListViewHolder holder, int position) {
        final InviteeListBean.Friendlist currentInviteeInfo = inviteeListInfo.get(position);
        holder.txt_nickname.setText(currentInviteeInfo.getNickname());
        holder.txt_email.setText(currentInviteeInfo.getInviteeEmail());


        final String inviteeId= currentInviteeInfo.getInviteeId();

        if(position!=0){
            //add all invitee to play
            if(Const.currentUserType.equals(Const.BUYER)){
                Const.playerIDs.add(inviteeId);
            }
            holder.masterInvitee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Const.currentUserType.equals(Const.BUYER)) {
                        boolean chbStatus;
                        if (holder.chb_user.isChecked()) {
                            chbStatus = false;
                        } else {
                            chbStatus = true;
                        }
                        holder.chb_user.setChecked(chbStatus);
                       // listener.onInviteeClick(chbStatus, inviteeId);

                    }
                }
            });


            if(position==inviteeListInfo.size()){
                 listener.onInviteeClick();
            }
            //Below Code use to select Invitee from List
//            if(Const.currentUserType.equals(Const.BUYER)){
//                if(Const.playerIDs.contains(currentInviteeInfo.getInviteeId())){
//                    holder.chb_user.setChecked(true);
//                }
//                holder.chb_user.setVisibility(View.VISIBLE);
//                holder.img_user.setVisibility(View.GONE);
//
//            }else{
                holder.img_user.setVisibility(View.VISIBLE);
                holder.chb_user.setVisibility(View.GONE);
//            }
        }else{
            Glide.with(context)
                    .load(R.drawable.ic_buyer)
                    .into(holder.img_user);

            holder.img_user.setVisibility(View.VISIBLE);
            holder.chb_user.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return inviteeListInfo.size();
    }


    public class OnInviteeListViewHolder  extends RecyclerView.ViewHolder{
        TextView txt_nickname,txt_email;
        CheckBox chb_user;
        ImageView img_user;
        LinearLayout masterInvitee;
        public OnInviteeListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nickname=itemView.findViewById(R.id.txt_nickname);
            txt_email=itemView.findViewById(R.id.txt_email);
            chb_user=itemView.findViewById(R.id.chb_user);
            img_user=itemView.findViewById(R.id.img_user);
            masterInvitee=itemView.findViewById(R.id.masterInvitee);
        }
    }
}
