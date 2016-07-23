package com.kb.fragment.CollectFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class GoodsFragment extends Fragment {

    ListView mListView;
    View goodsview;

    ArrayList<CollectionModel> list;
    ViewGoodsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        goodsview = inflater.inflate(R.layout.activity_goods_fragment, container,false);
        return goodsview;
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
        mListView= (ListView) goodsview.findViewById(R.id.list_goods);
    }

    /**
     * ListView的点击事件
     */
    public void setGoodsList() {
        //ListView绑定数据
        list=new ArrayList<>();
        for (int i=0;i<20;i++){
            CollectionModel model=new CollectionModel();
            model.setImg(R.mipmap.qq);
            model.setTitle("商品名称");
            model.setType("款式：戒指/指环");
            model.setStyle("形状：圆形");
            model.setClarity("钻石净度：");
            model.setMoney("￥123456");
            list.add(model);
        }
        adapter=new ViewGoodsAdapter(list,getActivity());
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
    public class ViewGoodsAdapter extends BaseAdapter{

        ArrayList<CollectionModel> list;
        Context context;
        public ViewGoodsAdapter(ArrayList<CollectionModel> list,Context context){
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
                convertView = LayoutInflater.from(context).inflate(R.layout.list_goods_item,null);
                holder=new ViewHolder();
                holder.img_goods= (ImageView) convertView.findViewById(R.id.img_goods);
                holder.txt_title=(TextView)convertView.findViewById(R.id.txt_goods_name);
                holder.txt_type=(TextView)convertView.findViewById(R.id.txt_goods_type);
                holder.txt_style=(TextView)convertView.findViewById(R.id.txt_goods_style);
                holder.txt_clearty=(TextView)convertView.findViewById(R.id.txt_goods_clarity);
                holder.txt_money=(TextView)convertView.findViewById(R.id.txt_goods_money);

                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.img_goods.setImageResource(list.get(position).getImg());
            holder.txt_title.setText(list.get(position).getTitle());
            holder.txt_type.setText(list.get(position).getType());
            holder.txt_style.setText(list.get(position).getStyle());
            holder.txt_clearty.setText(list.get(position).getClarity());
            holder.txt_money.setText(list.get(position).getMoney());
            return convertView;
        }

        public class ViewHolder{
            ImageView img_goods;
            TextView txt_title,txt_type,txt_style,txt_clearty,txt_money;


        }
    }
}
