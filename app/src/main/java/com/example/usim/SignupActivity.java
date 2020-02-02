package com.example.usim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.data.IdCheckData;
import com.example.usim.data.IdCheckResponse;
import com.example.usim.data.SignupData;
import com.example.usim.data.SignupResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    public static final int code = 100;
    private ServiceApi service;
    private Button IdCheckbtn,signUpbtn;
    private EditText editUserName;
    private EditText editId;
    private EditText editPw;
    private boolean isIdCheck = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        IdCheckbtn = (Button)findViewById(R.id.IdCheckbtn);
        signUpbtn = (Button)findViewById(R.id.realSignUpbtn);
        editUserName = (EditText)findViewById(R.id.editUserName);
        editId = (EditText)findViewById(R.id.editId);
        editPw = (EditText)findViewById(R.id.editPw);

        IdCheckbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String u_id = editId.getText().toString();
                if(u_id.isEmpty()) Toast.makeText(SignupActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else startIdCheck(new IdCheckData(u_id));
            }
        });
        signUpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String u_name = editUserName.getText().toString();
                String u_id = editId.getText().toString();
                String u_pw = editPw.getText().toString();
                if(u_name.isEmpty()) Toast.makeText(SignupActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(u_id.isEmpty()) Toast.makeText(SignupActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(u_pw.isEmpty()) Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if (!isIdCheck) Toast.makeText(SignupActivity.this, "아이디 중복을 확인해주세요", Toast.LENGTH_SHORT).show();
                else startSignup(new SignupData(u_name, u_id, u_pw));
            }
        });
    }
    private void startIdCheck(IdCheckData data) {
        service.userIdCheck(data).enqueue(new Callback<IdCheckResponse>() {
            @Override
            public void onResponse(Call<IdCheckResponse> call, Response<IdCheckResponse> response) {
                IdCheckResponse result = response.body();
                if(result == null) Toast.makeText(SignupActivity.this, "이미 사용중인 아이디입니다", Toast.LENGTH_SHORT).show();
                else if (result.getStatus() == 201) {
                    Toast.makeText(SignupActivity.this, "사용가능한 아이디입니다", Toast.LENGTH_SHORT).show();
                    isIdCheck = true;
                }
            }
            @Override
            public void onFailure(Call<IdCheckResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "아이디 중복확인 실패", Toast.LENGTH_SHORT).show();
                Log.e("아이디 중복확인 실패", t.getMessage());
            }
        });
    }
    private void startSignup(SignupData data) {
        service.userSignup(data).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse result = response.body();
                if(result == null) Toast.makeText(SignupActivity.this, "입력된 정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                else if (result.getStatus() == 201) {
                    Intent intent = new Intent(getApplicationContext(),InitialActivity.class);
                    startActivityForResult(intent,code);
                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 실패", t.getMessage());
            }
        });
    }
}
