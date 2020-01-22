package com.example.usim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InitialActivity extends AppCompatActivity {
    EditText editId,editPw;
    Button loginbtn,signupbtn;
    public static final int code = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        loginbtn = (Button)findViewById(R.id.loginbtn);
        signupbtn = (Button)findViewById(R.id.signupbtn);
        editId =(EditText)findViewById(R.id.editId);
        editPw =(EditText)findViewById(R.id.editPw);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 아이디, 비번 서버에 넘겨주기
//                if((editId.getText().toString()).equals("") || (editPw.getText().toString()).equals(""))
//                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 입력해주세요.",Toast.LENGTH_LONG).show();
                // 올바른 아이디, 비번이라는 응답을 받았을때만 실행
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivityForResult(intent,code);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(intent,code);
            }
        });
    }

}
