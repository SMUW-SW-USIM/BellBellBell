package com.example.usim.ui.emergencyContact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;

public class EmergencyContactPopupActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact_popup);

        Button okBtn = (Button) findViewById(R.id.okBtn);
        Button cancleBtn = (Button) findViewById(R.id.cancleBtn);
    }

    public void onClickOK(View v){
        finish();
    }
    public void onClickCancle(View v){
        finish();
    }
}
