package com.kb.firstitem.News;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kb.lifeknow.R;


public class NewsInfoActivity extends Activity {


    ImageButton btn_back;
    TextView txt_title;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_info);

        btn_back= (ImageButton) findViewById(R.id.img_back);
        txt_title= (TextView) findViewById(R.id.txt_title);
        txt_title.setText("新闻标题");

        Intent intent=getIntent();
        id=intent.getIntExtra("mid",-1);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
