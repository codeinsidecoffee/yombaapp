package com.yomba.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yomba.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrackItMultipleFragment extends Fragment {


    public CrackItMultipleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crack_it_multiple, container, false);
    }

}
