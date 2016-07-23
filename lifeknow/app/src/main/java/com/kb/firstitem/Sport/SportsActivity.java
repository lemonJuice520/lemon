package com.kb.firstitem.Sport;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.kb.lifeknow.R;

import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends FragmentActivity implements View.OnClickListener {
    ImageButton img_back,img_more;
    Button btn_over;
    TextView txt_title;

    TextView txt_local_sport,txt_new_sport,re_ticket_sport;
    ViewPager view_sport;
    private List<Fragment> mFragmentList ;
    SportAdapter adpter;
    LocalActivitiesFragment locaol;
    NewsEventsFragment news;
    TicktesFragment tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        initView();
    }

    public void initView(){
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        btn_over=(Button)findViewById(R.id.btn_over);
        txt_title=(TextView)findViewById(R.id.txt_title);

        img_back.setOnClickListener(this);
        txt_title.setText("体育活动");

        txt_local_sport= (TextView) findViewById(R.id.txt_local_sport);
        txt_new_sport= (TextView) findViewById(R.id.txt_new_sport);
        re_ticket_sport= (TextView) findViewById(R.id.re_ticket_sport);
        view_sport=(ViewPager)findViewById(R.id.view_sport);
        txt_local_sport.setOnClickListener(this);
        txt_new_sport.setOnClickListener(this);
        re_ticket_sport.setOnClickListener(this);

        mFragmentList = new ArrayList<Fragment>();
        locaol=new LocalActivitiesFragment();
        news=new NewsEventsFragment();
        tickets=new TicktesFragment();
        mFragmentList.add(locaol);
        mFragmentList.add(news);
        mFragmentList.add(tickets);
        adpter=new SportAdapter(this.getSupportFragmentManager(),mFragmentList);
        view_sport.setAdapter(adpter);
        view_sport.setCurrentItem(0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_more:

                break;
            //本地活动
            case R.id.txt_local_sport:
                view_sport.setCurrentItem(0);
                break;
            //新闻赛事
            case R.id.txt_new_sport:
                view_sport.setCurrentItem(1);
                break;
            //体育门票
            case R.id.re_ticket_sport:
                view_sport.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    public class SportAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        public SportAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
