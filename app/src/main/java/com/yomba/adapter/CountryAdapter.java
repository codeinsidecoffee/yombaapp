package com.yomba.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yomba.R;
import com.yomba.model.Const;
import com.yomba.model.CountryBean;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    List<CountryBean.Datum> countryList;
    Context context;
    String action;

    public int getPosition(String language){
        int pos= 0;
        for (int i = 0; i<countryList.size(); i++){
            if (countryList.get(i).getName().equalsIgnoreCase(language)){
                pos = i;
                break;
            }
        }
        return pos;
    }


    public CountryAdapter(Context context, List<CountryBean.Datum> countryList, String action) {
        this.countryList=countryList;
        this.context=context;
        this.action=action;

    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img_countryFlag;
        TextView txt_country_code;
        LinearLayout spinner;


        if(convertView==null){
            LayoutInflater inflater= LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.country_row_item,parent,false);
        }
        final CountryBean.Datum currentCountryInfo = countryList.get(position);
        txt_country_code=convertView.findViewById(R.id.txt_country_code);
        spinner=convertView.findViewById(R.id.spinner);
        img_countryFlag=convertView.findViewById(R.id.img_countryFlag);
        if(action.equals("lang")){
            txt_country_code.setText(currentCountryInfo.getName());
            txt_country_code.setTextColor(Color.BLACK);

        }else{
            String countrycode=currentCountryInfo.getIso()+"( "+currentCountryInfo.getPhonecode()+" )";
            txt_country_code.setText(countrycode);
            if(currentCountryInfo.getIso().equals("IL")){
                Const.countryPosition=position;
            }
        }

        String flagURL=currentCountryInfo.getImage();
        Glide.with(context)
                .load(flagURL)
                .into(img_countryFlag);

        return convertView;
    }
}
