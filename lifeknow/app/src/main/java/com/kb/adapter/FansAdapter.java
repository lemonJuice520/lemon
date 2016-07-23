package com.kb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.FansModel;
import com.kb.model.FarmersModel;

import java.util.ArrayList;


public class FansAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<FansModel> list ;
    FansModel model;

    public FansAdapter(Context context, ArrayList<FansModel> list) {
        this.mContext = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.fans_list_item, null);//得到布局界面
            //得到控件
            mHolder.img_fans = (ImageView) convertView
                    .findViewById(R.id.img_fans);
            mHolder.txt_name = (TextView) convertView
                    .findViewById(R.id.txt_name);
            mHolder.txt_phone = (TextView) convertView
                    .findViewById(R.id.txt_phone);
            mHolder.txt_addr = (TextView) convertView
                    .findViewById(R.id.txt_addr);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        model=list.get(position);

        //给控件赋值
        mHolder.img_fans.setImageResource(model.getImage());
        mHolder.txt_name.setText(model.getName());
        mHolder.txt_phone.setText(model.getPh_num());
        mHolder.txt_addr.setText(model.getAddress());

        return convertView;
    }

    public static class ViewHolder {
        private ImageView img_fans;
        private TextView txt_name, txt_phone, txt_addr;
    }



}
