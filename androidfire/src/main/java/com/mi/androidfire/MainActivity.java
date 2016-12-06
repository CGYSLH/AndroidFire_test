package com.mi.androidfire;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.more)
    ImageButton more;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    private ArrayList<String> news_list=new ArrayList<>();
    private ArrayList<Fragment> list_fragment=new ArrayList<>();
    private NewsFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inittabs();

        initAdapter();
        pager.setAdapter(adapter);
        tab.setSelectedTabIndicatorColor(Color.WHITE);
        tab.setupWithViewPager(pager);


    }

    private void initAdapter() {
        adapter = new NewsFragmentAdapter(getSupportFragmentManager());
    }

    private void initFragments() {
        for (int i=0;i<news_list.size();i++) {
            list_fragment.add(new NewsFragment().getinstance(news_list.get(i)));
        }
    }

    private void inittabs() {

       String [] news= getResources().getStringArray(R.array.newstab1);
       for (int i=0;i<news.length;i++) {
           news_list.add(news[i]);

       }
        initFragments();


    }
    class NewsFragmentAdapter extends FragmentStatePagerAdapter
    {

        public NewsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return news_list.get(position);
        }
    }
}
