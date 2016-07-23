package com.kb.lifeknow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kb.fragment.CollectFragment.MyCollectFragment;
import com.kb.fragment.First.FristFragment;
import com.kb.fragment.MineFragment;
import com.kb.view.ContentViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    ContentViewPager content;
    private RelativeLayout re_first, re_collect, re_mine;
    private TextView txt_first,txt_collect,txt_mine;
    private int tabPressedColor = 0xffff6699;// tab选中色
    private int tabNormalColor = 0xff2b2b2b;// tab未选中色


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initdata();
        initView();
    }

    private List<Fragment> content_list = null;
    private void initdata() {
        content_list = new ArrayList<>();
        content_list.add(new FristFragment());
        content_list.add(new MyCollectFragment());
        content_list.add(new MineFragment());
    }
    /**
     * 初始化视图
     */
    public  void initView(){
        if (content_list == null) {
            return;
        }
        re_first = (RelativeLayout) findViewById(R.id.re_first);
        re_collect = (RelativeLayout) findViewById(R.id.re_collect);
        re_mine = (RelativeLayout) findViewById(R.id.re_mine);
        content= (ContentViewPager) findViewById(R.id.content);

        txt_first=(TextView)findViewById(R.id.txt_homepage);
        txt_collect=(TextView)findViewById(R.id.txt_collect);
        txt_mine=(TextView)findViewById(R.id.txt_my);

        re_first.setOnClickListener(this);
        re_collect.setOnClickListener(this);
        re_mine.setOnClickListener(this);


        content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return content_list.get(i);
            }
            @Override
            public int getCount() {
                return content_list.size();
            }
        });
        content.setCurrentItem(0);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //生活首页
            case R.id.re_first:
                content.setCurrentItem(0);
                txt_first.setTextColor(tabPressedColor);
                txt_collect.setTextColor(tabNormalColor);
                txt_mine.setTextColor(tabNormalColor);
                break;
            //收藏页
            case R.id.re_collect:
                content.setCurrentItem(1);
                txt_first.setTextColor(tabNormalColor);
                txt_collect.setTextColor(tabPressedColor);
                txt_mine.setTextColor(tabNormalColor);
                break;
            //我的
            case R.id.re_mine:
                content.setCurrentItem(2);
                txt_first.setTextColor(tabNormalColor);
                txt_collect.setTextColor(tabNormalColor);
                txt_mine.setTextColor(tabPressedColor);
                break;
            default:
                break;
        }
    }

    /**
     * 返回键按两次退出程序
     */
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
