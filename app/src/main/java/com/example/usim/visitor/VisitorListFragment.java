package com.example.usim.visitor;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.usim.R;
import com.example.usim.data.VisitorListResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VisitorListFragment extends Fragment {
    public static final int code = 100;
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private ListView mListView;
    VisitorAdapter mMyAdapter;
    public VisitorListFragment() { }

    public static VisitorListFragment newInstance(){
        Bundle args = new Bundle();

        VisitorListFragment fragment = new VisitorListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visitor_list, container, false);
        mListView = (ListView)view.findViewById(R.id.listVisitor);
        dataSetting();
        return view;
    }
    private void dataSetting(){
        mMyAdapter = new VisitorAdapter();
        startVisitorList();
    }



    private void startVisitorList() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";


        service.visitorList(token).enqueue(new Callback<VisitorListResponse>() {
            @Override
            public void onResponse(Call<VisitorListResponse> call, Response<VisitorListResponse> response) {
                VisitorListResponse result = response.body();
                if(result == null) Toast.makeText(getContext(), "방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    List<VisitorListResponse.visitorlist> visitorInfo = result.getData();

                    if (!message.isEmpty())
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        for (int i=0; i< visitorInfo.size(); i++) {
                            int iconImg ;
                            if(visitorInfo.get(i).v_gender == 0) { //여자
                                if(visitorInfo.get(i).v_age < 13) iconImg =R.drawable.childwoman;
                                else if(visitorInfo.get(i).v_age < 20) iconImg =R.drawable.student;
                                else if(visitorInfo.get(i).v_age < 40) iconImg =R.drawable.woman;
                                else if(visitorInfo.get(i).v_age < 60) iconImg =R.drawable.mother;
                                else iconImg =R.drawable.grandmother;
                            }
                            else{ // 남자
                                if(visitorInfo.get(i).v_age < 13) iconImg =R.drawable.childman;
                                else if(visitorInfo.get(i).v_age < 20) iconImg =R.drawable.student;
                                else if(visitorInfo.get(i).v_age < 40) iconImg =R.drawable.man;
                                else if(visitorInfo.get(i).v_age < 60) iconImg =R.drawable.father;
                                else iconImg =R.drawable.grandfather;
                            }
                            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), iconImg),
                                    visitorInfo.get(i).v_name,
                                    visitorInfo.get(i).v_gender,
                                    visitorInfo.get(i).v_age,
                                    visitorInfo.get(i).v_times);
                        }
                        mListView.setAdapter(mMyAdapter);
                    } else
                        Toast.makeText(getContext(), "방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VisitorListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("방문자 정보 가져오기 실패", t.getMessage());
            }
        });
    }

}
