package com.example.usim.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usim.R;

import java.util.ArrayList;

public class RecordAdapter extends BaseAdapter {
    private TextView r_name;
    private Button ttsBtn;


    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<RecordItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public RecordItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_record, parent, false);
        }

        r_name = (TextView) convertView.findViewById(R.id.recordName);

        RecordItem myItem = getItem(position);

        r_name.setText(myItem.getName());


        // play 버튼 누르면 TTS로 음성 전달
        ttsBtn = (Button) convertView.findViewById(R.id.recordPlaying);
        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, r_name.getText().toString(), Toast.LENGTH_LONG).show();
                //((TextToSpeachActivity) context).Speech(r_name.getText().toString());
                //((TextToSpeachActivity) context).test();
            }
        });

        return convertView;
    }


    public void addItem(String name) {

        RecordItem mItem = new RecordItem();

        mItem.setName(name);
        mItems.add(mItem);
    }


}