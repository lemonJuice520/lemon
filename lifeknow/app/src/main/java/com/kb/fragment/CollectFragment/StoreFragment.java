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

public class StoreFragment extends Fragment {
    View view;
    ListView mListView;

    ArrayList<CollectionModel> list;
    ViewStoreAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
         view= inflater.inflate(R.layout.activity_store_fragment, container,false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();

        setStoreList();
    }

    /**
     * 初始化控件
     */
    public void initView(){
        mListView= (ListView) view.findViewById(R.id.list_store);
    }

    /**
     * ListView的点击事件
     */
    public void setStoreList() {
        list=new ArrayList<>();
        for (int i=0;i<20;i++){
            CollectionModel model=new CollectionModel();
            model.setImg(R.mipmap.qq);
            model.setTitle("hahhahahh");
            list.add(model);
        }
        adapter=new ViewStoreAdapter(list,getActivity());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 继承Adapter，实例化数据
     */
    public class ViewStoreAdapter extends BaseAdapter{
        ArrayList<CollectionModel> list;
        Context context;
        public ViewStoreAdapter (ArrayList<CollectionModel> list, Context context){
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
                holder=new ViewHolder();
                convertView=LayoutInflater.from(context).inflate(R.layout.list_store_item,null);
                holder.img= (ImageView) convertView.findViewById(R.id.img_store);
                holder.name= (TextView) convertView.findViewById(R.id.txt_store_name);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.img.setImageResource(list.get(position).getImg());
            holder.name.setText(list.get(position).getTitle());

            return convertView;
        }

        public class ViewHolder{
            private ImageView img;
            private TextView name;
        }
    }
}
