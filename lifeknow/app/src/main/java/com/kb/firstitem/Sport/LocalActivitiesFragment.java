package com.kb.firstitem.Sport;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.CollectionModel;

import java.util.ArrayList;

public class LocalActivitiesFragment extends Fragment {

    View view;
    ListView mListView;

    ArrayList<CollectionModel> mlist;
    LocalActivityAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_localactivities_fragment,container,false);
        return  view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        setGoodsList();
    }

    /**
     * 初始化控件
     */
    public void initView(){
        mListView= (ListView) view.findViewById(R.id.localacticity);
    }

    /**
     * ListView的点击事件
     */
    public void setGoodsList() {
        //ListView绑定数据
        mlist=new ArrayList<CollectionModel>();
        for (int i=0;i<20;i++){
            CollectionModel model=new CollectionModel();
            model.setImg(R.mipmap.qq);
            model.setTitle("活动名称");
            model.setType("时间：2015-05-20");
            model.setStyle("地点：一组团篮球场");
            model.setMoney("费用：无");
            mlist.add(model);
        }
        adapter=new LocalActivityAdapter(mlist,getActivity());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 继承Adapter，实例化控件
     */
    public class LocalActivityAdapter extends BaseAdapter {

        ArrayList<CollectionModel> list;
        Context context;
        public LocalActivityAdapter(ArrayList<CollectionModel> list,Context context){
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
            ViewHolder holder;
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_localactivites_item,null);
                holder=new ViewHolder();
                holder.img_goods= (ImageView) convertView.findViewById(R.id.img_localac);
                holder.txt_title=(TextView)convertView.findViewById(R.id.txt_localac_name);
                holder.txt_time=(TextView)convertView.findViewById(R.id.txt_localac_type);
                holder.txt_local=(TextView)convertView.findViewById(R.id.txt_localac_style);
                holder.txt_money=(TextView)convertView.findViewById(R.id.txt_localac_clarity);

                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.img_goods.setImageResource(list.get(position).getImg());
            holder.txt_title.setText(list.get(position).getTitle());
            holder.txt_time.setText(list.get(position).getType());
            holder.txt_local.setText(list.get(position).getStyle());
            holder.txt_money.setText(list.get(position).getMoney());
            return convertView;
        }

        public class ViewHolder{
            ImageView img_goods;
            TextView txt_title,txt_time,txt_local,txt_money
                    ;


        }
    }
}
