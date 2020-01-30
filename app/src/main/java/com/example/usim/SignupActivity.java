package com.example.usim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText editUserName;
    private EditText editId;
    private EditText editPw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        Button signUp = (Button)findViewById(R.id.signUp);
        editUserName = (EditText)findViewById(R.id.editUserName);
        editId = (EditText)findViewById(R.id.editId);
        editPw = (EditText)findViewById(R.id.editPw);

        signUp.setOnClickListener(new View.OnClickListener(){
            String u_name = editUserName.getText().toString();
            String u_id = editId.getText().toString();
            String u_pw = editPw.getText().toString();

            @Override
            public void onClick(View v) {
                startSignup(new SignupData(u_name, u_id, u_pw));
            }
        });
    }
    private void startSignup(SignupData data) {
        service.userJoin(data).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse result = response.body();
                Toast.makeText(SignupActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.getStatus() == 200) {
                    Intent intent = new Intent(getApplicationContext(),InitialActivity.class);
                    startActivityForResult(intent,code);
                    //finish();
                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }
}
