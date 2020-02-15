package com.yomba.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yomba.model.Const;
import com.yomba.model.PrefBean;


public class SharedPrefrenceManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String PrefName;
    Context context;
    PrefBean prefBean;

    public SharedPrefrenceManager(String prefName, Context context) {
        PrefName = prefName;
        this.context = context;
        sharedPreferences=context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }


    public PrefBean getSharedPreferences() {
        prefBean=new PrefBean();
        prefBean.setCurrentLang(sharedPreferences.getString(Const.CurrentLang,""));
        prefBean.setGameID(sharedPreferences.getString(Const.GAME_ID,""));
        prefBean.setOrderID(sharedPreferences.getString(Const.ORDER_ID,""));
        prefBean.setLoginID(sharedPreferences.getString(Const.LOGIN_ID,""));
        prefBean.setUserID(sharedPreferences.getString(Const.USER_ID,""));
        prefBean.setPlayID(sharedPreferences.getString(Const.PLAY_ID,""));
        prefBean.setScenario(sharedPreferences.getString(Const.SCENARIO,""));
        prefBean.setGameFinish(sharedPreferences.getBoolean(Const.isPlayGame,false));
        prefBean.setGameStarted(sharedPreferences.getBoolean(Const.isStartGame,false));
        prefBean.setWaitForGame(sharedPreferences.getBoolean(Const.isWaitForGame,false));
        prefBean.setInviteScreen(sharedPreferences.getBoolean(Const.isInviteeScreen,false));
        prefBean.setBuyerNickName(sharedPreferences.getString(Const.BUYER_NICKNAME,""));
        prefBean.setBuyerEmail(sharedPreferences.getString(Const.BUYER_EMAIL,""));
        return prefBean;
    }

        public void setSharedPreferences(PrefBean prefBean){
        editor.putString(Const.CurrentLang,prefBean.getCurrentLang());
        editor.putString(Const.GAME_ID,prefBean.getGameID());
        editor.putString(Const.ORDER_ID,prefBean.getOrderID());
        editor.putString(Const.LOGIN_ID,prefBean.getLoginID());
        editor.putString(Const.USER_ID,prefBean.getUserID());
        editor.putString(Const.PLAY_ID,prefBean.getPlayID());
        editor.putString(Const.SCENARIO,prefBean.getScenario());
        editor.putBoolean(Const.isPlayGame,prefBean.getGameFinish());
        editor.putBoolean(Const.isStartGame,prefBean.getGameStarted());
        editor.putBoolean(Const.isWaitForGame,prefBean.getWaitForGame());
        editor.putBoolean(Const.isInviteeScreen,prefBean.getInviteScreen());
        editor.putString(Const.BUYER_NICKNAME,prefBean.getBuyerNickName());
        editor.putString(Const.BUYER_EMAIL,prefBean.getBuyerEmail());
        editor.commit();
    }

}
