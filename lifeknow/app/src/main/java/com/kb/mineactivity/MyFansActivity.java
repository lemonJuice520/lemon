package com.kb.mineactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.adapter.FansAdapter;
import com.kb.lifeknow.R;
import com.kb.model.FansModel;

import java.util.ArrayList;

public class MyFansActivity extends Activity implements View.OnClickListener {
    ImageButton img_back,img_more;
    TextView txt_title;

    ListView mListview;
    ArrayList<FansModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fans);
        initView();
        setFansListView();
    }
    public void initView(){
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        txt_title=(TextView)findViewById(R.id.txt_title);

        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        txt_title.setText("我的粉丝");


        mListview= (ListView) findViewById(R.id.list_fans);
    }

    public void setFansListView(){
        list=new ArrayList<FansModel>();
        for (int i=0;i<20;i++){
            FansModel model=new FansModel();
            model.setImage(R.mipmap.qq);
            model.setName("琳达");
            model.setPh_num("电话：15523961617");
            model.setAddress("地址：重庆市沙坪坝区");
            list.add(model);
        }
        mListview.setAdapter(new FansAdapter(this,list));

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
