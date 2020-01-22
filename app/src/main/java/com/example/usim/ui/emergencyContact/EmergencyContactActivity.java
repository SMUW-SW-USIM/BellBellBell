package com.example.usim.ui.emergencyContact;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.Toast;
=======

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> e6bf203970924f14afc9d03307aa06a2e6720509

import com.example.usim.R;

public class EmergencyContactActivity extends AppCompatActivity {
    String name,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

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

<<<<<<< HEAD
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            name = cursor.getString(0);
            number = cursor.getString(1);
            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
=======

>>>>>>> e6bf203970924f14afc9d03307aa06a2e6720509
    }
}
