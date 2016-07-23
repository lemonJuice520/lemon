package com.kb.mineactivity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.CollectionModel;

import java.util.ArrayList;

public class AttentionActivity extends Activity implements View.OnClickListener{
    ImageButton img_back,img_more;
    TextView txt_title;
    ListView mListview;
    ArrayList<CollectionModel> list;
    AttentionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        initView();
        addListView();//添加ListView的数据
        adapter=new AttentionAdapter(list,this);
        mListview.setAdapter(adapter);

        setOnclickList();//ListView的点击事件


    }

    public void initView(){
        img_back=(ImageButton)findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        txt_title=(TextView)findViewById(R.id.txt_title);

        mListview=(ListView)findViewById(R.id.list_attent);

        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        txt_title.setText("我的关注");
    }

    public  void addListView(){
        list=new ArrayList<>();
        for (int i=0;i<20;i++){
            CollectionModel model=new CollectionModel();
            model.setImg(R.mipmap.qq);
            model.setTitle("酷我KTV");
            model.setStyle("如果你无法简介的表达你的想法，那只能说明你还不够了解他。");
            list.add(model);
        }
    }

    public void setOnclickList(){
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public class AttentionAdapter extends BaseAdapter{
        ArrayList<CollectionModel> list;
        Context context;
        public  AttentionAdapter(ArrayList<CollectionModel> list,Context context){
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
                convertView= LayoutInflater.from(context).inflate(R.layout.list_attent_item,null);
                holder.icon=(ImageView)convertView.findViewById(R.id.img_attent_icon);
                holder.name=(TextView)convertView.findViewById(R.id.txt_attent_name);
                holder.info=(TextView)convertView.findViewById(R.id.txt_attent_info);
                holder.cancel=(Button)convertView.findViewById(R.id.btn_attent_cancel);

                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.icon.setImageResource(list.get(position).getImg());
            holder.name.setText(list.get(position).getTitle());
            holder.info.setText(list.get(position).getStyle());
            return convertView;
        }

        public class ViewHolder{
            ImageView icon;
            TextView name,info;
            Button cancel;
        }

    }
}
