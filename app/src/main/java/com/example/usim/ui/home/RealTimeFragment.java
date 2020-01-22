package com.example.usim.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.usim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeFragment extends Fragment {


    public RealTimeFragment() {
        // Required empty public constructor
    }
    public static RealTimeFragment newInstance(){
        Bundle args = new Bundle();

        RealTimeFragment fragment = new RealTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_time, container, false);
    }

}
