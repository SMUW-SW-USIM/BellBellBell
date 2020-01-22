package com.example.usim.ui.record;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;

public class RecordAddActivity extends AppCompatActivity {
    MediaRecorder mRecorder;
    boolean isRecording = false;
    Button mBtRecord = null;
    MediaPlayer mPlayer = null;
    boolean isPlaying = false;
    Button mBtPlay = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_add);
        mRecorder = new MediaRecorder();

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


    }

    // 레코더 기본 설정
    void initAudioRecorder() {
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        //저장 위치 지정 ~~~

        //mRecorder.setOutputFile(mPath);
        try {
            mRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
