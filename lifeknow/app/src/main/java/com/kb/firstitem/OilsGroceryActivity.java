package com.kb.firstitem;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kb.adapter.FarmersAdapter;
import com.kb.adapter.FarmersShopAdapter;
import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;

import java.util.ArrayList;
import java.util.List;

public class OilsGroceryActivity extends Activity implements View.OnClickListener {

    ImageButton back,more;
    ListView mListView;

    Spinner sp_1,sp_2;
    ArrayList<FarmersModel> mList;

    ArrayList<String> list_1,list_2;
    ArrayAdapter<String> adapter_1,adapter_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oils_grocery);
        initView();
    }

    public void initView(){
        back=(ImageButton)findViewById(R.id.img1_back);
        more=(ImageButton)findViewById(R.id.img1_more);
        mListView=(ListView)findViewById(R.id.list_oils);
        sp_1= (Spinner) findViewById(R.id.sp_1);
        sp_2= (Spinner) findViewById(R.id.sp_2);
        list_1=new ArrayList<String>();
        list_2=new ArrayList<String>();


        adapter_1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_1);
        adapter_2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_2);
        for (int i=0;i<5;i++){
            list_1.add("粮油副食");
        }
        for (int i=0;i<5;i++){
            list_2.add("米面");
        }
        sp_1.setAdapter(adapter_1);
        sp_2.setAdapter(adapter_2);

        mList=new ArrayList<FarmersModel>();
        for (int i=0;i<10;i++){
            FarmersModel information = new FarmersModel();
            information.setId(1000+i);
            information.setStroename("标题"+i);
            information.setGrade("评分"+i);
            information.setStroetype("水果"+i);
            information.setAddress("大学城"+i);
            information.setDistanes("<1.2km");
            information.setImage(R.mipmap.ic_launcher);
            mList.add(information); //将新的info对象加入到信息列表中
        }
        mListView.setAdapter(new FarmersAdapter(this,mList));
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1_back:
                finish();
                break;
        }
    }



}
