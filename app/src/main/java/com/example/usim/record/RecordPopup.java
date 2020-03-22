package com.example.usim.record;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;
import com.example.usim.data.RecordListAppendData;
import com.example.usim.data.RecordListAppendResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordPopup extends AppCompatActivity {

    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    TextView txtText;
    Button okBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //타이틀바 없애기
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.popup_tts);

            //UI 객체생성
            txtText = (TextView)findViewById(R.id.edt_speech);

            //데이터 가져오기
            Intent intent = getIntent();
            String data = intent.getStringExtra("data");
            txtText.setText(data);
        }

        //확인 버튼 클릭
        public void mOnClose(View v){
            //데이터 전달하기
            Intent intent = new Intent();
            intent.putExtra("result", "Close Popup");
            setResult(RESULT_OK, intent);

            // 서버에 정보 저장
            startRecordListAppend(new RecordListAppendData(txtText.getText().toString()));
            //액티비티(팝업) 닫기
            finish();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //바깥레이어 클릭시 안닫히게
            if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
                return false;
            }
            return true;
        }

        @Override
        public void onBackPressed() {
            //안드로이드 백버튼 막기
            return;
        }

        public void onClickCancle(View v){
        finish();
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
