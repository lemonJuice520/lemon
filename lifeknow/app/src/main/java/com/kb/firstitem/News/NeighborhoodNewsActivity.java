package com.kb.firstitem.News;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.adapter.NeignborhoodNewsAdapter;
import com.kb.lifeknow.R;
import com.kb.model.NewsModel;

import java.util.ArrayList;

public class NeighborhoodNewsActivity extends Activity implements View.OnClickListener {

    ImageButton btn_back,btn_more;
    TextView txt_title;
    ListView mXlist;
    NeignborhoodNewsAdapter adapter;
    ArrayList<NewsModel> newList;
    NewsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_news);
        initView();
        setLinstener();
    }

    /**
     * 得到控件
     */
    public void initView(){
        btn_back= (ImageButton) findViewById(R.id.img_back);
        btn_more= (ImageButton) findViewById(R.id.img_more);
        txt_title=(TextView) findViewById(R.id.txt_title);
        mXlist= (ListView) findViewById(R.id.xlist_news);

        btn_back.setOnClickListener(this);
        btn_more.setOnClickListener(this);
        txt_title.setText("新闻");
    }

    /**
     * 设置监听事件
     */
    public void setLinstener(){
        newList=new ArrayList<NewsModel>();
        model=new NewsModel();

        for (int i=0;i<10;i++){
            NewsModel model=new NewsModel();
            model.setImg(R.mipmap.ic_launcher);
            model.setTitle("我是标题"+i);
            model.setDescription("简单描述。。。。。");
            model.setKeywords("分类");
            model.setTime("2016-04-13");
            newList.add(model);
        }
        adapter=new NeignborhoodNewsAdapter(newList,this);
        mXlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mXlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id=newList.get(i).getId();
                Intent intent=new Intent(NeighborhoodNewsActivity.this, NewsInfoActivity.class);
                intent.putExtra("mid",id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_more:

                break;
        }
    }
}
