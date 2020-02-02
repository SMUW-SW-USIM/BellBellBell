package com.example.usim.visitor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usim.R;

import java.util.ArrayList;

public class VisitorAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<VisitorItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public VisitorItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_visitor, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.visitorImg) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.visitorName) ;
        TextView tv_gender = (TextView) convertView.findViewById(R.id.visitorGender) ;
        TextView tv_age = (TextView) convertView.findViewById(R.id.visitorAge) ;
        TextView tv_times = (TextView) convertView.findViewById(R.id.visitorTimes) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        VisitorItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        if(myItem.getGender() == 0) tv_gender.setText("여성");
        else tv_gender.setText("남성");
        tv_age.setText("("+myItem.getAge()+"세)");
        tv_times.setText("방문횟수: "+ myItem.getTimes()+"회");

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable img, String name, Integer gender,Integer age, Integer times) {

        VisitorItem mItem = new VisitorItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setGender(gender);
        mItem.setAge(age);
        mItem.setTimes(times);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}
