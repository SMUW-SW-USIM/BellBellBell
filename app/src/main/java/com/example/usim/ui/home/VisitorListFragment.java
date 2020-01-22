package com.example.usim.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.usim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitorListFragment extends Fragment {


    public VisitorListFragment() {
        // Required empty public constructor
    }

    public static VisitorListFragment newInstance(){
        Bundle args = new Bundle();

        VisitorListFragment fragment = new VisitorListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visitor_list, container, false);
        mListView = (ListView)view.findViewById(R.id.listVisitor);
        dataSetting();
        return view;
    }
    private void dataSetting(){
        VisitorAdapter mMyAdapter = new VisitorAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.mainimg),
                    "심다은",
                    "여성",
                    23,
                    "방문횟수 " + i +" 회");
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }


}
