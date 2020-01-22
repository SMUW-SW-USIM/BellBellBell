package com.example.usim.ui.record;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.usim.R;

import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends Fragment {


    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recording, container, false);

        Button addbtn = (Button)view.findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RecordAddActivity.class);
                startActivity(intent);
            }
        });

        ListView listview = (ListView)view.findViewById(R.id.listRecording);
        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                String selected_item = (String)adapterView.getItemAtPosition(position);
            }
        });
        list.add("두번째 녹음");

        return view;
    }

}