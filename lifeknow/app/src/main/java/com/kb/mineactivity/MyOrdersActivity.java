package com.kb.mineactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kb.lifeknow.R;

public class MyOrdersActivity extends Activity implements View.OnClickListener{
    ImageButton img_back,img_more;
    TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        initView();
    }
    public void initView(){
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        txt_title=(TextView)findViewById(R.id.txt_title);

        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        txt_title.setText("我的订单");
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
