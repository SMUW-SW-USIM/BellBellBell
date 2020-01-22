package com.example.usim;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Mainpage_01 extends Fragment {


    public Fragment_Mainpage_01() {
        // Required empty public constructor
    }

    public static Fragment_Mainpage_01 newInstance(){
        Bundle args = new Bundle();

        Fragment_Mainpage_01 fragment = new Fragment_Mainpage_01();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.fragment_fragment__mainpage_01, container, false);
        return layout;
    }

}
