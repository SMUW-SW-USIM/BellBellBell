package com.example.usim.record;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;

import java.util.Locale;

public class TextToSpeachActivity extends AppCompatActivity {
    private Button btnEnter;
    private EditText r_name;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_tts);

        initTTS();
        r_name = (EditText) findViewById(R.id.edt_speech);
        btnEnter = (Button) findViewById(R.id.okBtn);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speech(r_name.getText().toString());
            }
        });
    }
    public void initTTS(){
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
                        // btnEnter.setEnabled(true);
                        //음성 톤
                        textToSpeech.setPitch(0.7f);
                        //읽는 속도
                        textToSpeech.setSpeechRate(1.2f);
                    }
                }
            }
        });
    }
    public void Speech(String text) {
        initTTS();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            //if(textToSpeech.isSpeaking()) Toast.makeText(this, "말하는중이지롱", Toast.LENGTH_LONG).show();
            // API 20
        }else {
            Toast.makeText(this, "speak!", Toast.LENGTH_LONG).show();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
        onStop();
    }

    // 메모리 누출을 방지하게 위해 TTS를 중지
    @Override
    public void onStop() {
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    public void test(){
        Toast.makeText(this,"성공",Toast.LENGTH_LONG).show();
    }
}
