package com.example.usim.ui.emergencyContact;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;

public class EmergencyContactActivity extends AppCompatActivity {
    String name,number;
    private ListView listview;
    TextView textviewview;
    AddressAdapter mAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        listview = (ListView)findViewById(R.id.listAddress);
        mAddressAdapter = new AddressAdapter();

        listview.setAdapter(mAddressAdapter);
        mAddressAdapter.addItem("유채은","01012345678");

        textviewview = (TextView)findViewById(R.id.textviewview);

        Button addContactBtn = (Button) findViewById(R.id.addContactBtn);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void dataSetting(String name, String num){
        AddressAdapter mMyAdapter = new AddressAdapter();

        mMyAdapter.addItem(name,num);

        listview.setAdapter(mMyAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Cursor cursor = getContentResolver().query(data.getData(),
                        new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                cursor.moveToFirst();
                name = cursor.getString(0);
                number = cursor.getString(1);
                Intent intent = new Intent(EmergencyContactActivity.this, EmergencyContactPopupActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("number", number);
                startActivityForResult(intent, 1);
                cursor.close();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
        if(requestCode == 1){
            if (resultCode == RESULT_OK) {
                name = data.getExtras().getString("name");
                number = data.getExtras().getString("number");
                textviewview.setText(name);
                mAddressAdapter.addItem(name,number);
                listview.setAdapter(mAddressAdapter);
            }
        }
    }

    
}
