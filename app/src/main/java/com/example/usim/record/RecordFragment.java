package com.example.usim.record;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.usim.R;
import com.example.usim.data.RecordListResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecordFragment extends Fragment {
    public RecordFragment() {
        // Required empty public constructor
    }
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private ListView listview;
    RecordAdapter mMyAdapter;

    public static RecordFragment newInstance(){
        Bundle args = new Bundle();

        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recording, container, false);
        listview = (ListView)view.findViewById(R.id.listRecording);
        dataSetting();


        Button addbtn = (Button)view.findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RecordAddActivity.class);
                startActivity(intent);
            }
        });


        // List<String> list = new ArrayList<>();

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);


//        listview.setAdapter(mMyAdapter);
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView,
//                                    View view, int position, long id) {
//                String selected_item = (String)adapterView.getItemAtPosition(position);
//                // Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
//                Log.i("3버버ㅓ너버버번","여기아ㅏ아아");
//            }
//        });

        return view;
    }

    private void dataSetting(){
        mMyAdapter = new RecordAdapter();
        startRecordList();
    }

    private void startRecordList() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";

        service.recordList(token).enqueue(new Callback<RecordListResponse>() {
            @Override
            public void onResponse(Call<RecordListResponse> call, Response<RecordListResponse> response) {
                RecordListResponse result = response.body();
                if(result == null) Toast.makeText(getContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    List<RecordListResponse.recordlist> recordInfo = result.getData();

                    if (!message.isEmpty())
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        Toast.makeText(getContext(), "녹음 정보 가져오기 성공", Toast.LENGTH_SHORT).show();
                        for (int i=0; i< recordInfo.size(); i++) {
                            mMyAdapter.addItem(
                                    recordInfo.get(i).r_name);
                        }
                        listview.setAdapter(mMyAdapter);
                    } else
                        Toast.makeText(getContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecordListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("녹음 정보 가져오기 실패", t.getMessage());
            }
        });
    }
}

