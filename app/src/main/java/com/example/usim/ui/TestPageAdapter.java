package com.example.usim.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TestPageAdapter extends FragmentPagerAdapter {
    private  static  int PAGE_NUMBER = 3;
    public TestPageAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem (int position){
        switch(position){
            case 0:
                return Fragment_Mainpage_1.newInstance();
            case 1:
                return Fragment_Mainpage_2.newInstance();
            case 2:
                return Fragment_Mainpage_3.newInstance();
        }
        return null;
    }
    @Override
    public int getCount(){
        return PAGE_NUMBER;
    }
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "첫번째 탭";
            case 1:
                return "두번째 탭";
            case 2:
                return "첫번째 탭";
            default:
                return null;
        }
    }
}
