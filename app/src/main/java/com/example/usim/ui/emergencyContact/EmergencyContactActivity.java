package com.example.usim.ui.emergencyContact;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usim.R;
import com.example.usim.data.ContactListAppendData;
import com.example.usim.data.ContactListAppendResponse;
import com.example.usim.data.ContactListResponse;
import com.example.usim.network.RetrofitClient;
import com.example.usim.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyContactActivity extends AppCompatActivity {
    String name,number;
    private ListView listview;
    TextView textviewview;
    AddressAdapter mAddressAdapter;
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        listview = (ListView)findViewById(R.id.listAddress);
        dataSetting();

        //mAddressAdapter = new AddressAdapter();
        //listview.setAdapter(mAddressAdapter);
        // mAddressAdapter.addItem("유채은","01012345678");

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

    private void dataSetting(){
        mAddressAdapter = new AddressAdapter();

        //mMyAdapter.addItem(name,num);
        startContactList();
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
                startContactListAppend(new ContactListAppendData(name, number));
                //mAddressAdapter.addItem(name,number);
                //listview.setAdapter(mAddressAdapter);
            }
        }
    }

    private void startContactList() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";

        service.contactList(token).enqueue(new Callback<ContactListResponse>() {
            @Override
            public void onResponse(Call<ContactListResponse> call, Response<ContactListResponse> response) {
                ContactListResponse result = response.body();
                if(result == null) Toast.makeText(getApplicationContext(), "긴급 연락처 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    List<ContactListResponse.contactlist> contactinfo = result.getData();

                    if (!message.isEmpty())
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 200) {
                        for (int i=0; i< contactinfo.size(); i++) {
                            mAddressAdapter.addItem(
                                    contactinfo.get(i).c_name,
                                    contactinfo.get(i).c_number);
                        }
                        listview.setAdapter(mAddressAdapter);
                    } else
                        Toast.makeText(getApplicationContext(), "긴급 연락처 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "긴급 연락처 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("긴급 연락처 정보 가져오기 실패", t.getMessage());
            }
        });
    }

    private void startContactListAppend(ContactListAppendData data) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1X2lkeCI6IjEifQ.87fKkaZ2cChPhtljZuXy9NcIBfZ_utzibQGj0ffbIQU";

        service.postContactList(token, data).enqueue(new Callback<ContactListAppendResponse>() {
            @Override
            public void onResponse(Call<ContactListAppendResponse> call, Response<ContactListAppendResponse> response) {
                ContactListAppendResponse result = response.body();
                if(result == null) Toast.makeText(getApplicationContext(), "긴급 연락처 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                else {
                    Integer status = result.getStatus();
                    String message = result.getMessage();
                    if (!message.isEmpty())
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    if (status == 201) {
                        dataSetting();
                    } else
                        Toast.makeText(getApplicationContext(), "긴급 연락처 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactListAppendResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "긴급 연락처 정보 등록하기 실패", Toast.LENGTH_SHORT).show();
                Log.e("긴급 연락처 정보 등록하기 실패", t.getMessage());
            }
        });
    }
    
}
