package com.mugil.lakshmiproteins.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mugil.lakshmiproteins.R;

/**
 * Created by Administrator on 04-01-2018.
 */

public class Dashboard_Fragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment,container,false);


        return view;
    }
}
