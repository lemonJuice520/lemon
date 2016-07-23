package com.kb.firstitem.Sport;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.NewsModel;

import java.util.ArrayList;

public class NewsEventsFragment extends Fragment {

    View view;
    private ListView mListview;

    ActivitesNewsAdapter adapter;
    ArrayList<NewsModel> mlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_newsevent_fragment,null);
        return  view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListview= (ListView) view.findViewById(R.id.list_activitesnews);
        mlist=new ArrayList<NewsModel>();
        for (int i=0;i<10;i++){
            NewsModel model=new NewsModel();
            model.setImg(R.mipmap.qq);
            model.setTitle("我是标题"+i);
            model.setDescription("简单描述。。。。。");
            model.setKeywords("分类");
            model.setTime("2016-04-13");
            mlist.add(model);
        }
        mListview.setAdapter(new ActivitesNewsAdapter(mlist,getActivity()));
    }

    public class ActivitesNewsAdapter extends BaseAdapter {

        ArrayList<NewsModel> mlist;
        Context mcontext;
        NewsModel model;

        public ActivitesNewsAdapter(ArrayList<NewsModel> mlist, Context mcontext) {
            this.mcontext = mcontext;
            this.mlist = mlist;
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
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            //控件类
            HolderView holderView;
            //布局不为空时
            if (convertView == null) {
                convertView = LayoutInflater.from(mcontext).inflate(R.layout.xlist_item_news, null);
                holderView = new HolderView();
                //实例化控件
                holderView.img_photo = (ImageView) convertView.findViewById(R.id.img_photo);
                holderView.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
                holderView.txt_key = (TextView) convertView.findViewById(R.id.txt_key);
                holderView.txt_whoandtime = (TextView) convertView.findViewById(R.id.txt_whoandtime);
                //打包
                convertView.setTag(holderView);
            } else {
                holderView = (HolderView) convertView.getTag();
            }
            model = mlist.get(position);

            holderView.img_photo.setImageResource(model.getImg());
            holderView.txt_title.setText(model.getTitle());
            holderView.txt_key.setText(model.getDescription());
            holderView.txt_whoandtime.setText(model.getKeywords() + "  " + model.getTime());
            return convertView;
        }

        /**
         * 控件类
         */
        public class HolderView {
            ImageView img_photo;
            TextView txt_title, txt_key;
            TextView txt_whoandtime;
        }
    }
}
