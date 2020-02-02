package com.example.usim.ui.emergencyContact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;

public class EmergencyContactPopupActivity extends AppCompatActivity {
    String name, number;
    EditText editTextName, editTextNum;
    Button okBtn, cancleBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact_popup);
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        number = intent.getExtras().getString("number");
        editTextName = (EditText) findViewById(R.id.editName);
        editTextNum = (EditText) findViewById(R.id.editPhoneNum);
        editTextName.setText(name);
        editTextNum.setText(number);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancleBtn = (Button) findViewById(R.id.cancleBtn);
    }

    public void onClickOK(View v){
        Intent intent = new Intent();
        String n1 = editTextName.getText().toString();
        String n2 = editTextNum.getText().toString();
        intent.putExtra("name",n1);
        intent.putExtra("number",n2);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void onClickCancle(View v){
        finish();
    }
}
