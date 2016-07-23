package com.kb.mineactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kb.adapter.MineInfoAdapter;
import com.kb.lifeknow.R;
import com.kb.model.MyinfoModel;

import java.util.ArrayList;

public class MyInfomationActivity extends Activity implements View.OnClickListener {
    ImageButton img_back,img_more;
    TextView txt_title;

    ListView mListView;
    ArrayList<MyinfoModel> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);
        initView();
        setListView();

    }
    public void initView(){
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        txt_title=(TextView)findViewById(R.id.txt_title);
        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        txt_title.setText("我的消息");

        mListView=(ListView)findViewById(R.id.list_info);
    }

    public void setListView(){
        mlist=new ArrayList<MyinfoModel>();
        for (int i=0;i<20;i++){
            MyinfoModel model=new MyinfoModel();
            model.setImg(R.mipmap.qq);
            model.setNumber("运单号：1245896312587456963");
            model.setType("快递已被签收");
            model.setLook("去看看");
            mlist.add(model);

        }
        mListView.setAdapter(new MineInfoAdapter(mlist,this));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyInfomationActivity.this,"你点击了第"+(position-1)+"个列表",Toast.LENGTH_LONG).show();
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
