package com.kb.firstitem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.adapter.FarmersAdapter;
import com.kb.adapter.FarmersShopAdapter;
import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;

import java.util.ArrayList;

public class EntertainmentActivity extends Activity implements View.OnClickListener {

    ImageButton back,more;
    TextView title;

    ListView list_enter;
    ArrayList<FarmersModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void initView(){
        back=(ImageButton)findViewById(R.id.img1_back);
        more=(ImageButton)findViewById(R.id.img1_more);
        list_enter= (ListView) findViewById(R.id.list_enter);
        list=new ArrayList<FarmersModel>();
        for (int i=0;i<20;i++){
            FarmersModel model=new FarmersModel();
            model.setId(1000+i);
            model.setStroename("标题"+i);
            model.setGrade("评分"+i);
            model.setStroetype("水果"+i);
            model.setAddress("大学城"+i);
            model.setDistanes("<1.2km");
            model.setImage(R.mipmap.ic_launcher);
            list.add(model); //将新的info对象加入到信息列表中
            
        }
        list_enter.setAdapter(new FarmersAdapter(this,list));

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;

        }
    }
}
