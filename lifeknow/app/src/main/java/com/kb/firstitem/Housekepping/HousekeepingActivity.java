package com.kb.firstitem.Housekepping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.adapter.FarmersAdapter;
import com.kb.lifeknow.MainActivity;
import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;
import com.kb.utils.Utils;

import java.util.ArrayList;

public class HousekeepingActivity extends Activity implements View.OnClickListener {

    ImageButton btn_back;
    TextView txt_compay,txt_person;
    ListView mHouseListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping);
        initView();
    }
    public void initView(){
        btn_back= (ImageButton) findViewById(R.id.img_back);
        txt_compay= (TextView) findViewById(R.id.txt_company);
        txt_person= (TextView) findViewById(R.id.txt_person);
        mHouseListView= (ListView) findViewById(R.id.list_house);
        Utils.setListViewHeightBasedOnChildren(mHouseListView);
        
        btn_back.setOnClickListener(this);
        txt_compay.setOnClickListener(this);
        txt_person.setOnClickListener(this);

        ArrayList<FarmersModel> list=new ArrayList<FarmersModel>();
        for (int i=0;i<20;i++){
            FarmersModel model=new FarmersModel();
            model.setStroename("蚂蚁搬家");
            list.add(model);
        }
        FarmersAdapter adapter=new FarmersAdapter(this,list);
        mHouseListView.setAdapter(adapter);


        mHouseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(HousekeepingActivity.this,HouseKeepingInfoActivity.class);
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
          case R.id.txt_company:
              System.out.println("进来了");
              txt_compay.setTextColor(getResources().getColor(R.color.colorBlue));
              txt_person.setTextColor(getResources().getColor(R.color.colorBlack));
              break;
          case R.id.txt_person:
              txt_compay.setTextColor(getResources().getColor(R.color.colorBlack));
              txt_person.setTextColor(getResources().getColor(R.color.colorBlue));
              break;
      }
    }
}
