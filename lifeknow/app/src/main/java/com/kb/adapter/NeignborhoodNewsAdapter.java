package com.kb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.NewsModel;

import java.util.ArrayList;

public class NeignborhoodNewsAdapter extends BaseAdapter {

    ArrayList<NewsModel> mlist;
    Context mcontext;
    NewsModel model;

    final int TYPE_1=0;
    final int TYPE_2=1;

    public NeignborhoodNewsAdapter(ArrayList<NewsModel> mlist, Context mcontext){
        this.mcontext=mcontext;
        this.mlist=mlist;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int p=position;
        if(p==0){
           return TYPE_1;
        }else{
            return TYPE_2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //控件类
        HolderView holderView=null;
        HolderView2 holder=null;
        int type=getItemViewType(position);
        //布局不为空时
        if(convertView==null){
            switch (type){
                case TYPE_1:
                    holder=new HolderView2();
                    convertView= LayoutInflater.from(mcontext).inflate(R.layout.list_news_duotu, null);
                    holder.txt_name=(TextView) convertView.findViewById(R.id.toutiaodt_title);
                    holder.image=(ImageView)convertView.findViewById(R.id.toutiaodt_imgsrc1);
                    convertView.setTag(holder);

                    break;
                case TYPE_2:
                    holderView=new HolderView();
                    convertView= LayoutInflater.from(mcontext).inflate(R.layout.xlist_item_news, null);
                    //实例化控件
                    holderView.img_photo=(ImageView) convertView.findViewById(R.id.img_photo);
                    holderView.txt_title=(TextView) convertView.findViewById(R.id.txt_title);
                    holderView.txt_key=(TextView) convertView.findViewById(R.id.txt_key);
                    holderView.txt_whoandtime=(TextView) convertView.findViewById(R.id.txt_whoandtime);
                    convertView.setTag(holderView);//打包

                    break;
                default:
                    break;
            }
        }else{
            switch (type){
                case TYPE_1:
                    holder = (HolderView2) convertView.getTag();
                    break;
                case TYPE_2:
                    holderView = (HolderView) convertView.getTag();
                    break;
            }
        }

        model=mlist.get(position);
        //设置资源
        switch (type){
            case TYPE_1:
                holder.txt_name.setText(model.getTitle());
                holder.image.setImageResource(model.getImg());
                break;
            case TYPE_2:
                holderView.img_photo.setImageResource(model.getImg());
                holderView.txt_title.setText(model.getTitle());
                holderView.txt_key.setText(model.getDescription());
                holderView.txt_whoandtime.setText(model.getKeywords()+"  "+model.getTime());
                break;
        }


//        //设值图片
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
//
//        ImageLoader.getInstance().displayImage("http://tnfs.tngou.net/img"+model.getImg(), holderView.img_photo, options);
        return convertView;
    }
    /**
     * 控件类
     */
    public class HolderView{
        ImageView img_photo;
        TextView txt_title,txt_key;
        TextView txt_whoandtime;
    }

    public class HolderView2{
        TextView txt_name;
        ImageView image;
    }
}
