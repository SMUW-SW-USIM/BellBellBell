package com.example.usim.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.usim.R;
import com.example.usim.ui.emergencyContact.EmergencyContactActivity;
import com.example.usim.ui.record.RecordAddActivity;

public class HomeFragment extends Fragment {

    Button btnRecord, btnEmergency;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(){
        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_home, container, false );
        btnRecord = (Button)view.findViewById(R.id.btnRecord);
        btnEmergency  = (Button)view.findViewById(R.id.btnEmergemcy);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecordAddActivity.class);
                startActivity(intent);
            }
        });
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EmergencyContactActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}