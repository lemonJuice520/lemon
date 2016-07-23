package com.kb.mineactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kb.lifeknow.R;

public class BasicsActivity extends Activity implements View.OnClickListener {

    ImageButton img_back,img_more;
    Button btn_over;
    TextView txt_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        initView();
    }

    public void initView(){
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_more=(ImageButton)findViewById(R.id.img_more);
        btn_over=(Button)findViewById(R.id.btn_over);
        txt_title=(TextView)findViewById(R.id.txt_title);
        btn_over.setVisibility(View.VISIBLE);
        img_more.setVisibility(View.GONE);

        img_back.setOnClickListener(this);
        txt_title.setText("基本资料设置");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_over:

                break;
        }
    }
}
