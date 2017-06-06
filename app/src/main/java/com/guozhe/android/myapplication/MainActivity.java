package com.guozhe.android.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    TabLayout tab;
    FrameLayout ground;
    Fragment google,gyesangi,image,memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ground = (FrameLayout) findViewById(R.id.ground);
        pager =(ViewPager)findViewById(R.id.pager);
        tab =(TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Memo"));
        tab.addTab(tab.newTab().setText("Gyesangi"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("image"));

        google = new GoogleMapFragment();
        gyesangi = new GyesangiFragment();
        image = new ImageFragment();
        memo = new MemoFragment();

        List<Fragment> datas = new ArrayList<>();
        datas.add(google);
        datas.add(gyesangi);
        datas.add(image);
        datas.add(memo);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),datas);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));


    }
    class PagerAdapter extends FragmentStatePagerAdapter{
        List<Fragment> datas;

        public PagerAdapter(FragmentManager fm,List<Fragment> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
