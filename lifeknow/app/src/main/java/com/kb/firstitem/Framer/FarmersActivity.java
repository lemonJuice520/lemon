package com.kb.firstitem.Framer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.kb.adapter.FarmersAdapter;
import com.kb.adapter.ViewPagerAdapter;
import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;
import com.kb.model.ViewPagerModel;
import com.kb.utils.Utils;

import java.util.ArrayList;


public class FarmersActivity extends Activity implements View.OnClickListener {

    private ImageView img_back;
    ArrayList<FarmersModel> shopList;//数据组
    ListView list_shop;
    private Intent mIntent;


    ScrollView scr;

    ViewPager mViewPager;
    private TextView mIntroTv;
    private LinearLayout mDotLayout;
    ArrayList<ViewPagerModel> list=new ArrayList<ViewPagerModel>();
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * handler处理定时滑动任务
     */
    private Handler mMyHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
            mMyHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers);
        initView();
        getKg();
        setLinstener();
        initData();
    }

    public void initView(){
        mViewPager = (ViewPager)findViewById(R.id.view_farme);
        mIntroTv = (TextView) findViewById(R.id.intro);
        mDotLayout = (LinearLayout) findViewById(R.id.dots);
        img_back = (ImageView) findViewById(R.id.img_farmers_back);
        list_shop = (ListView) findViewById(R.id.list_shop);
        //返回上一个界面
        img_back.setOnClickListener(this);
    }

    /**
     * 设置Viewpager的事件监听
     */
    public  void setLinstener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                updateIntroAndDot();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    /**
     * 初始ViewPager化数据
     */
    private void initData(){
        list.add(new ViewPagerModel(R.mipmap.qq,"可爱的茶杯兔"));
        list.add(new ViewPagerModel(R.mipmap.hudie,"飞舞的彩蝶"));
        list.add(new ViewPagerModel(R.mipmap.qq,"可爱的茶杯兔"));
        list.add(new ViewPagerModel(R.mipmap.hudie,"飞舞的彩蝶"));

        initDots();
        mViewPagerAdapter=new ViewPagerAdapter(this,list);
        mViewPager.setAdapter(mViewPagerAdapter);
        //默认在1亿多
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2 )% list.size()));
        //3秒定时
        mMyHandler.sendEmptyMessageDelayed(0, 3000);
    }

    /**
     * 初始化Dot数据
     */
    public void initDots(){

        for (int i=0;i<list.size();i++){
            View view=new View(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);//设置远点Dots的大小
            if(i!=0){//第一个点不需要左边距
                params.leftMargin=10;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            mDotLayout.addView(view);
        }
    }

    /**
     * 更新文本
     */
    private void updateIntroAndDot(){
        int currentPage = mViewPager.getCurrentItem()%list.size();
        mIntroTv.setText(list.get(currentPage).getIntro());
        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            mDotLayout.getChildAt(i).setEnabled(i==currentPage);//设置setEnabled为true的话 在选择器里面就会对应的使用白色颜色
        }
    }
    //得到listview控件和他的点击事件
    public void getKg() {
        shopList =new ArrayList<FarmersModel>();
        for (int i=0;i<10;i++){
            FarmersModel information = new FarmersModel();
            information.setId(1000+i);
            information.setStroename("标题"+i);
            information.setGrade("评分"+i);
            information.setStroetype("水果"+i);
            information.setAddress("大学城"+i);
            information.setDistanes("<1.2km");
            information.setImage(R.mipmap.ic_launcher);
            shopList.add(information); //将新的info对象加入到信息列表中
        }

        list_shop.setAdapter(new FarmersAdapter(this,shopList));

        Utils.setListViewHeightBasedOnChildren(list_shop);//设置listview的高度

        //listview的点击事件
        list_shop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FarmersActivity.this, "你点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
                mIntent = new Intent(FarmersActivity.this, ShopActivity.class);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_farmers_back:
                finish();
                break;

            default:
                break;
        }
    }


}