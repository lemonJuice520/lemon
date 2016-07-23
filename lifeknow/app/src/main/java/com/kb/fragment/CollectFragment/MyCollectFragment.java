package com.kb.fragment.CollectFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kb.lifeknow.R;

import java.util.ArrayList;
import java.util.List;

public class MyCollectFragment extends Fragment  implements View.OnClickListener {


    View mView;
    ImageButton img_back,img_more;
    TextView txt_title;
    Button btn_goods,btn_store;
    ViewPager mViewpager;

    /**
     * Fragment
     */
    private GoodsFragment mGoodsFg;
    private StoreFragment mStoreFg;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    viewAdapter mViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.activity_my_collection,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        img_back= (ImageButton)mView.findViewById(R.id.img_back);
        img_more=(ImageButton)mView.findViewById(R.id.img_more);
        txt_title=(TextView)mView.findViewById(R.id.txt_title);

        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        txt_title.setText("我的收藏");
        img_back.setVisibility(View.GONE);

        btn_goods=(Button)mView.findViewById(R.id.btn_goods);
        btn_store=(Button)mView.findViewById(R.id.btn_store);
        mViewpager=(ViewPager)mView.findViewById(R.id.view_collect);

        btn_goods.setOnClickListener(this);
        btn_store.setOnClickListener(this);

        //Viewpager界面切换
        mGoodsFg=new GoodsFragment();
        mStoreFg=new StoreFragment();
        mFragmentList.add(mGoodsFg);
        mFragmentList.add(mStoreFg);
        mViewAdapter=new viewAdapter(getChildFragmentManager(), mFragmentList);
        mViewpager.setAdapter(mViewAdapter);
        mViewpager.setCurrentItem(0);
        btn_goods.setBackgroundResource(R.drawable.btn_shape_left2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.img_back:

        break;
        case R.id.img_more:

        break;
        case R.id.btn_goods:
        mViewpager.setCurrentItem(0);
        btn_goods.setBackgroundResource(R.drawable.btn_shape_left2);
        btn_store.setBackgroundResource(R.drawable.btn_shape_right);
        return;
        case R.id.btn_store:
        mViewpager.setCurrentItem(1);
        btn_store.setBackgroundResource(R.drawable.btn_shape_right2);
        btn_goods.setBackgroundResource(R.drawable.btn_shape_left);
        return;

        }

    }

    public class viewAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public viewAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
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

