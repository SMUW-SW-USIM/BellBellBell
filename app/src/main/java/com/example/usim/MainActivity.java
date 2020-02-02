package com.example.usim;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.usim.ui.home.HomeFragment;
import com.example.usim.ui.home.RealTimeFragment;
import com.example.usim.ui.home.VisitorListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TabLayout mTab;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        TestPagerAdapter mTestPagerAdapter = new TestPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mTestPagerAdapter);
        mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
        mTab.getTabAt(0).setIcon(R.drawable.homeicon2);
        mTab.getTabAt(1).setIcon(R.drawable.bellicon);
        mTab.getTabAt(2).setIcon(R.drawable.visitoricon);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                mTab.getTabAt(0).setIcon(R.drawable.homeicon);
                mTab.getTabAt(1).setIcon(R.drawable.bellicon);
                mTab.getTabAt(2).setIcon(R.drawable.visitoricon);
                switch (position){
                    case 0 : mTab.getTabAt(0).setIcon(R.drawable.homeicon2); break;
                    case 1 : mTab.getTabAt(1).setIcon(R.drawable.bellicon2); break;
                    case 2 : mTab.getTabAt(2).setIcon(R.drawable.visitoricon); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public class TestPagerAdapter extends FragmentPagerAdapter {
        private int PAGE_NUMBER = 3;
        public TestPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return RealTimeFragment.newInstance();
                case 2:
                    return VisitorListFragment.newInstance();
                default:
                    return null;
            }
        }
        @Override
        public int getCount(){
            return PAGE_NUMBER;
        }
    }

}
