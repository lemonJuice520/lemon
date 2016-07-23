package com.kb.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.MyinfoModel;

import java.util.ArrayList;

public class MineInfoAdapter extends BaseAdapter {

    Context context;
    ArrayList<MyinfoModel> list;
    public MineInfoAdapter(ArrayList<MyinfoModel> list, Context context){
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
        viewHolder holder;
        if(convertView==null){
            holder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_mine_info,null);
            holder.img_info_gou=(ImageView)convertView.findViewById(R.id.img_info_gou);
            holder.txt_info_num=(TextView)convertView.findViewById(R.id.txt_info_num);
            holder.txt_info_type=(TextView) convertView.findViewById(R.id.txt_info_type);
            holder.txt_info_see=(TextView) convertView.findViewById(R.id.txt_info_see);
            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }
        holder.img_info_gou.setImageResource(list.get(position).getImg());
        holder.txt_info_num.setText(list.get(position).getNumber());
        holder.txt_info_type.setText(list.get(position).getType());
        holder.txt_info_see.setText(list.get(position).getLook());

        return convertView;
    }

    public class viewHolder{

        ImageView img_info_gou;
        TextView txt_info_type,txt_info_num,txt_info_see;
    }
}
