package com.example.usim.ui.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.usim.R;
import com.example.usim.data.VisitorCurrentResponse;
import com.example.usim.data.VisitorListAppendData;
import com.example.usim.data.VisitorListAppendResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeFragment extends Fragment {
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    String v_faceId;

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
        View view = inflater.inflate(R.layout.fragment_real_time_stranger, container, false);
        Button buttonPushList = (Button)view.findViewById(R.id.buttonPushList);
        buttonPushList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startVisitorListAppend(new VisitorListAppendData("박동철",0,38,0,"2"));
                // String v_name, Integer v_gender, Integer v_age, Integer v_times, String v_faceId
            }
        });

        //가 데이터 - 수정해야됨
        Button testbtn = (Button) view.findViewById(R.id.testbutton);
        testbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v_faceId = "1";
                //startVisitorCurrent(new VisitorCurrentData(v_faceId));
                startVisitorCurrent(v_faceId);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void startVisitorCurrent(String v_faceId) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";
        service.visitorCurrent(token,v_faceId).enqueue(new Callback<VisitorCurrentResponse>() {
            @Override
            public void onResponse(Call<VisitorCurrentResponse> call, Response<VisitorCurrentResponse> response) {
                VisitorCurrentResponse result = response.body();
                if(result == null) Toast.makeText(getContext(), "실시간 방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    List<VisitorCurrentResponse.visitorcurrentlist> visitorInfo = result.getData();

                    if (!message.isEmpty())
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        Toast.makeText(getContext(), "이름 : "+visitorInfo.get(0).v_name+
                                ", 성별: "+visitorInfo.get(0).v_gender+
                                ", 나이 "+visitorInfo.get(0).v_age+
                                ", 방문횟수: "+visitorInfo.get(0).v_times, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "실시간 방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VisitorCurrentResponse> call, Throwable t) {
                Toast.makeText(getContext(), "실시간 방문자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("실시간 방문자 정보 가져오기 실패", t.getMessage());
            }
        });
    }

    private void startVisitorListAppend(VisitorListAppendData data) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";
        service.postVisitorList(token, data).enqueue(new Callback<VisitorListAppendResponse>() {
            @Override
            public void onResponse(Call<VisitorListAppendResponse> call, Response<VisitorListAppendResponse> response) {
                VisitorListAppendResponse result = response.body();
                if(result == null) Toast.makeText(getContext(), "방문자 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();

                    if (!message.isEmpty())
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 201) {
                        Toast.makeText(getContext(), "방문자 정보 등록하기 성공", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "방문자 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VisitorListAppendResponse> call, Throwable t) {
                Toast.makeText(getContext(), "방문자 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                Log.e("방문자 정보 등록하기 실패", t.getMessage());
            }
        });
    }

}
