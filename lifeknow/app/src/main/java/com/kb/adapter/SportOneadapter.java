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
import com.kb.model.SportModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SportOneadapter extends BaseAdapter {

    Context context;
    ArrayList<SportModel> list;
    public SportOneadapter(Context context, ArrayList<SportModel> list){
        this.context=context;
        this.list=list;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.list_sport_localactivites,null);
            holder.img_head= (ImageView) convertView.findViewById(R.id.img_head);
            holder.txt_name=(TextView)convertView.findViewById(R.id.txt_sport_name);
            holder.txt_time=(TextView)convertView.findViewById(R.id.txt_sport_name);
            holder.txt_addr=(TextView)convertView.findViewById(R.id.txt_sport_addr);
            holder.txt_info=(TextView)convertView.findViewById(R.id.txt_sport_info);

            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }

        holder.img_head.setImageResource(list.get(position).getImg());
        holder.txt_name.setText(list.get(position).getTitle());
        holder.txt_time.setText(list.get(position).getTime());
        holder.txt_addr.setText(list.get(position).getAddr());
        holder.txt_info.setText(list.get(position).getDescription());
        return convertView;
    }

    public class viewHolder{
        ImageView img_head;
        TextView txt_name,txt_time,txt_addr,txt_info;
    }
}
