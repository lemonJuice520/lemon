package com.kb.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kb.lifeknow.R;
import com.kb.model.ViewPagerModel;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

   /* ArrayList<Fragment> list=new ArrayList<Fragment>();
    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }*/

    private ArrayList<ViewPagerModel> mList;
    private Context mContext;

    public ViewPagerAdapter(Context mContext, ArrayList<ViewPagerModel> mList){
        this.mContext=mContext;
        this.mList=mList;
    }

    /**
     * @return 返回多少page
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 判断当前滑动的view等不等进来的图片--true表示不创建，使用缓存   false表示重新创建
     * @param view 当前滑动的view
     * @param object 将要进入的新创建的view，由instantiateItem方法创建
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    /**
     * 类似于BaseAdapger的getView方法 用了将数据设置给view 由于它最多就3个界面，不需要viewHolder
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//
//        View view=null;
//        if(position%mList.size()<0){
//            view=mList.get(mList.size()+position);
//        }
        View view=View.inflate(mContext, R.layout.viewager_item,null);

        ImageView imageView= (ImageView) view.findViewById(R.id.img_page);
        ViewPagerModel model=mList.get(position%mList.size());
        imageView.setImageResource(model.getIconResId());
        container.addView(view);//将view加入到viewPager中
        return view;
    }

    /**
     * 销毁page
     * position： 当前需要消耗第几个page
     * object:当前需要消耗的page
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
