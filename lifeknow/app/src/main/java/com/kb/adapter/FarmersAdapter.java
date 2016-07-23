package com.kb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.model.FarmersModel;

import java.util.ArrayList;


public class FarmersAdapter extends BaseAdapter {

    private int img_shop[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String type[] = {"水果", "鲜花、盆景", "蔬菜、天然", "肉类、野生","菌类、野生"};
    private String txt_grade[] = {"9.0", "8.0", "8.5", "8"};
    private String serviceTime[] = {"8:10-18:10", "9:00-18:00", "8:30-17:30", "8:50-17:50","9:00-18:00"};
    private String distancs[] = {"﹤1km", "﹤2km", "﹤1.5km", "﹤1.8km","﹤1.4km"};
    private String shopName[] = {"花果山", "花花房", "绿色蔬菜", "鸡鸭鱼肉","野生养身菌"};
    private String address[] = {"沙坪坝", "渝中區", "江北區", "涪陵","沙坪坝"};

    Context mContext;
    ArrayList<FarmersModel> list ;
    FarmersModel model;

    public FarmersAdapter(Context context,ArrayList<FarmersModel> list) {
        this.mContext = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
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
                    R.layout.farmers_list_item, null);//得到布局界面
            //得到控件
            mHolder.img_stor = (ImageView) convertView
                    .findViewById(R.id.img_store);
            mHolder.txt_Storename = (TextView) convertView
                    .findViewById(R.id.txt_Storename);
            mHolder.txt_serviceTime = (TextView) convertView
                    .findViewById(R.id.txt_time);
            mHolder.txt_grade = (TextView) convertView
                    .findViewById(R.id.txt_grade);
            mHolder.txt_type = (TextView) convertView
                    .findViewById(R.id.txt_type);
            mHolder.txt_addr = (TextView) convertView
                    .findViewById(R.id.txt_address);
            mHolder.txt_distancs=(TextView)convertView.findViewById(R.id.txt_distancs);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        model=list.get(position);

        //给控件赋值
        mHolder.img_stor.setImageResource(model.getImage());
        mHolder.txt_Storename.setText(model.getStroename());
        mHolder.txt_grade.setText(model.getGrade());
        mHolder.txt_serviceTime.setText(model.getServertime());
        mHolder.txt_distancs.setText(model.getDistanes());
        mHolder.txt_addr.setText(model.getAddress());
        mHolder.txt_type.setText(model.getStroetype());

        return convertView;
    }

    public static class ViewHolder {
        private ImageView img_stor;
        private TextView txt_Storename, txt_grade,txt_serviceTime, txt_type, txt_addr,txt_distancs;
    }



}
