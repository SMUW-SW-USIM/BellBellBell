package com.example.usim;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TextToSpeachActivity extends AppCompatActivity {
    private Button btnEnter;
    private EditText edtSpeech;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_tts);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //사용할 언어를 설정
                    int result = textToSpeech.setLanguage(Locale.KOREA);
                    //언어 데이터가 없거나 혹은 언어가 지원하지 않으면...
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        btnEnter.setEnabled(true);
                        //음성 톤
                        textToSpeech.setPitch(0.7f);
                        //읽는 속도
                        textToSpeech.setSpeechRate(1.2f);
                    }
                }
            }
        });

        edtSpeech = (EditText) findViewById(R.id.edt_speech);
//        btnEnter = (Button) findViewById(R.id.btn_ent);
//        btnEnter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Speech();
//            }
//        });
    }

    private void Speech() {
        String text = edtSpeech.getText().toString();
        // QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
        // QUEUE_ADD: 현재 Queue에 값을 추가하는 옵션이다.
        // API 21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            //if(textToSpeech.isSpeaking()) Toast.makeText(this, "말하는중이지롱", Toast.LENGTH_LONG).show();
            // API 20
        }else {
            Toast.makeText(this, "speak!", Toast.LENGTH_LONG).show();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    // 메모리 누출을 방지하게 위해 TTS를 중지
    @Override
    protected void onStop() {
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}
