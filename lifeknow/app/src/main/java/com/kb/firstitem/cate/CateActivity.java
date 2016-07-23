
package com.kb.firstitem.cate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kb.adapter.FarmersAdapter;
import com.kb.firstitem.Framer.ShopActivity;
import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;

import java.util.ArrayList;

public class CateActivity extends Activity implements View.OnClickListener {

    ImageButton imgc_back,img_more;
    Spinner spinner1,spinner2;
    ArrayAdapter<String> nearbay,song;
    ArrayList<String> list_nearby = new ArrayList<String>();
    ArrayList<String> list_song = new ArrayList<String>();

    ListView mListView;
    ArrayList<FarmersModel> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        initView();
        getKg();
    }

    public void initView(){
        img_more= (ImageButton) findViewById(R.id.img1_more);
        imgc_back= (ImageButton) findViewById(R.id.img1_back);
        spinner1= (Spinner) findViewById(R.id.sp_song);
        spinner2= (Spinner) findViewById(R.id.sp_nearby);
        mListView= (ListView) findViewById(R.id.list_cate);
        nearbay=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_nearby);
        song=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_song);
        for (int i=0;i<5;i++){
            list_nearby.add("智能排序");
        }
        for (int i=0;i<4;i++){
            song.add("不可送食品");
        }
        spinner1.setAdapter(song);
        spinner2.setAdapter(nearbay);
        img_more.setOnClickListener(this);
        imgc_back.setOnClickListener(this);

    }

    //得到listview控件和他的点击事件
    public void getKg() {
        shopList =new ArrayList<FarmersModel>();
        for (int i=0;i<10;i++){
            FarmersModel information = new FarmersModel();
            information.setId(1000+i);
            information.setStroename("标题"+i);
            information.setGrade("评分"+i);
            information.setStroetype("水果"+i);
            information.setAddress("大学城"+i);
            information.setDistanes("<1.2km");
            information.setImage(R.mipmap.ic_launcher);
            shopList.add(information); //将新的info对象加入到信息列表中
        }

        mListView.setAdapter(new FarmersAdapter(this,shopList));
        //listview的点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CateActivity.this, "你点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(CateActivity.this, ShopActivity.class);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1_back:
                finish();
                break;
            case R.id.img1_more:

                break;
        }

    }
}
