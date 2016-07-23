package com.kb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.R;


public class FarmersShopAdapter extends BaseAdapter {

    private int img_farmers_shop[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private String txt_productname[] = {"红富士", "新疆梨", "猕猴桃", "进口红西柚3枚装"};
    private String txt_abstract[] = {"原生态红富士产品", "果梗先端肥厚", "美容养颜的功效", "口味甘甜，多汁爽口"};
    private String txt_sun[] = {"18", "16", "32", "24"};
    private String txt_favourable2[] = {"99", "25", "64", "43"};
    private String txt_difference2[] = {"3", "3", "5", "1"};
    private String txt_messageboard2[] = {"12", "11", "10", "3"};


    Context mContext;

    public FarmersShopAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_farmers_shop.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return img_farmers_shop.length;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.shop_grid_item, null);

            mHolder.img_farmers_shop = (ImageView) convertView
                    .findViewById(R.id.img_farmers_shop);
            mHolder.txt_productname = (TextView) convertView
                    .findViewById(R.id.txt_productname);
            mHolder.txt_abstract = (TextView) convertView
                    .findViewById(R.id.txt_abstract);
            mHolder.txt_sun = (TextView) convertView
                    .findViewById(R.id.txt_sun);
            mHolder.txt_favourable2 = (TextView) convertView
                    .findViewById(R.id.txt_favourable2);
            mHolder.txt_difference2 = (TextView) convertView
                    .findViewById(R.id.txt_difference2);
            mHolder.txt_messageboard2 = (TextView) convertView
                    .findViewById(R.id.txt_messageboard2);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.img_farmers_shop.setImageResource(img_farmers_shop[position]);
        mHolder.txt_productname.setText(txt_productname[position]);
        mHolder.txt_abstract.setText(txt_abstract[position]);
        mHolder.txt_sun.setText(txt_sun[position]);
        mHolder.txt_favourable2.setText(txt_favourable2[position]);
        mHolder.txt_difference2.setText(txt_difference2[position]);
        mHolder.txt_messageboard2.setText(txt_messageboard2[position]);

        return convertView;
    }

    public static class ViewHolder {
        private ImageView img_farmers_shop;
        private TextView txt_productname, txt_abstract, txt_sun, txt_favourable2, txt_difference2, txt_messageboard2;
    }

}
