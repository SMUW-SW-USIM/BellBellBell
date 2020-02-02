package com.example.usim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.data.SigninData;
import com.example.usim.data.SigninResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialActivity extends AppCompatActivity {
    private ServiceApi service;
    private EditText loginId,loginPw;
    private Button loginbtn,signupbtn;
    public static final int code = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        loginbtn = (Button)findViewById(R.id.loginbtn);
        signupbtn = (Button)findViewById(R.id.signupbtn);
        loginId =(EditText)findViewById(R.id.loginId);
        loginPw =(EditText)findViewById(R.id.loginPw);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String u_id = loginId.getText().toString();
                String u_pw = loginPw.getText().toString();
                if(u_id.isEmpty()) Toast.makeText(InitialActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(u_pw.isEmpty()) Toast.makeText(InitialActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                else startSignin(new SigninData(u_id, u_pw));
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

    private void startSignin(SigninData data) {
        service.userLogin(data).enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                SigninResponse result = response.body();
                if(result == null) Toast.makeText(InitialActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();

                    if (!message.isEmpty())
                        Toast.makeText(InitialActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(intent, code);
                    } else
                        Toast.makeText(InitialActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Toast.makeText(InitialActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                Log.e("로그인 실패", t.getMessage());
            }
        });
    }
}
