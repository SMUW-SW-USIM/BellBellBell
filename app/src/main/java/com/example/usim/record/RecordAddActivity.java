package com.example.usim.record;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.usim.R;
import com.example.usim.data.RecordListAppendData;
import com.example.usim.data.RecordListAppendResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordAddActivity extends AppCompatActivity {
    MediaRecorder mRecorder;
    boolean isRecording = false;
    Button mBtRecord = null;
    MediaPlayer mPlayer = null;
    boolean isPlaying = false;
    Button mBtPlay = null;
    String mPath;
    public static final int RECORD_AUDIO = 0;

    Button recordSaveBtn;
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    String r_name,r_info;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_add);
        mRecorder = new MediaRecorder();

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO);

        } else {
            initAudioRecorder();
        }

        // 녹음 시작, 중지
        mBtRecord = (Button) findViewById(R.id.recordBtn);
        mBtRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording == false) {
                    initAudioRecorder();
                    mRecorder.start();
                    isRecording = true;
                    mBtRecord.setText("녹음 중지");
                } else {
                    mRecorder.stop();
                    isRecording = false;
                    mBtRecord.setText("녹음 시작");
                }
            }
        });
//        recordSaveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //가데이터
//                r_info="녹음테스트"; r_info="녹음테스트정보";
//                startRecordListAppend(new RecordListAppendData(r_name,r_info));
//            }
//        });


        mBtPlay = (Button) findViewById(R.id.recordSaveBtn);
        mBtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == false) {
                    try {
                        mPlayer.setDataSource(mPath);
                        mPlayer.prepare();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPlayer.start();

                    isPlaying = true;
                    mBtPlay.setText("Stop Playing");
                }
                else {
                    mPlayer.stop();

                    isPlaying = false;
                    mBtPlay.setText("Start Playing");
                }
            }
        });
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                mBtPlay.setText("Start Playing");
            }
        });

    }

    // 레코더 기본 설정
    void initAudioRecorder() {

        try {
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.aac";
            Log.d("경로", "file path is " + mPath);
            mRecorder.setOutputFile(mPath);

            mRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void startRecordListAppend(RecordListAppendData data) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";

        service.postRecordList(token, data).enqueue(new Callback<RecordListAppendResponse>() {
            @Override
            public void onResponse(Call<RecordListAppendResponse> call, Response<RecordListAppendResponse> response) {
                RecordListAppendResponse result = response.body();
                if(result == null) Toast.makeText(getApplicationContext(), "녹음 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    if (!message.isEmpty())
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 201) {
                        Toast.makeText(getApplicationContext(), "녹음 정보 등록하기 성공", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "녹음 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecordListAppendResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "녹음 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                Log.e("녹음 정보 등록하기 실패", t.getMessage());
            }
        });
    }

}
