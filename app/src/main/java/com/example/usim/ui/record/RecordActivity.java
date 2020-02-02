package com.example.usim.ui.record;

import androidx.appcompat.app.AppCompatActivity;
import com.example.usim.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        Button addbtn = (Button)findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordAddActivity.class);
                startActivity(intent);
            }
        });
    }
}
