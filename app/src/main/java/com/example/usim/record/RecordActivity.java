package com.example.usim.record;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;
import com.example.usim.data.RecordListResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordActivity extends AppCompatActivity {
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    private ListView mListView;
    RecordAdapter mMyAdapter;

//    public static RecordActivity newInstance(){
//        Bundle args = new Bundle();
//
//        RecordActivity activity = new RecordActivity();
//        activity.setArguments(args);
//        return activity;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        Button addbtn = (Button)findViewById(R.id.addbtn);
        mListView = (ListView)findViewById(R.id.listRecording);
        dataSetting();

        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordAddActivity.class);
                startActivity(intent);
            }
        });
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
                if(result == null) Toast.makeText(getApplicationContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    List<RecordListResponse.recordlist> recordInfo = result.getData();

                    if (!message.isEmpty())
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        for (int i=0; i< recordInfo.size(); i++) {
                            mMyAdapter.addItem(
                                    recordInfo.get(i).r_name);
                        }
                        mListView.setAdapter(mMyAdapter);
                    } else
                        Toast.makeText(getApplicationContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecordListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "녹음 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("녹음 정보 가져오기 실패", t.getMessage());
            }
        });
    }
}
