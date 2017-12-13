package com.meteorsoftech.dailyattendance.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meteorsoftech.dailyattendance.R;

/**
 * Created by Delete on 7/27/2017.
 */

public class Fragment_Fine extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fine_fees,container,false);
        return view;


    }
}
