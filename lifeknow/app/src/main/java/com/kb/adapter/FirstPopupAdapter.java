package com.kb.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.kb.lifeknow.R;

import java.util.ArrayList;
import java.util.Map;

public class FirstPopupAdapter extends BaseAdapter {

    ArrayList<String> list;Context context;
    public FirstPopupAdapter(ArrayList<String> list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        if(convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.popu_grid,null);
            holder=new viewHolder();
            holder.btn_local= (Button) convertView.findViewById(R.id.popo_btn);
            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }
       holder.btn_local.setText(list.get(position).toString());
        return convertView;
    }

    public class viewHolder{
            Button btn_local;
}
}

