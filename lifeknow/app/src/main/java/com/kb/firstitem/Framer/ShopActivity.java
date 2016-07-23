package com.kb.firstitem.Framer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kb.adapter.FarmersShopAdapter;
import com.kb.lifeknow.R;
import com.kb.view.MyGridView;

public class ShopActivity extends Activity implements View.OnClickListener {


    private MyGridView farmers_shop_grid;
    private ImageView img_shop_back, img_telephone, img_sms;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_details);

        farmers_shop_grid = (MyGridView) findViewById(R.id.farmers_shop_grid);
        img_shop_back = (ImageView) findViewById(R.id.img1_back);
        img_shop_back.setOnClickListener(this);
        FarmersShopAdapter ff = new FarmersShopAdapter(this);
        farmers_shop_grid.setAdapter(ff);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1_back:
                finish();
                break;
            default:
                break;
        }
    }
}
