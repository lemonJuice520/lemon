package com.kb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.kb.lifeknow.R;
import com.kb.model.CityModel;
import com.kb.view.MyGridView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class CityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CityModel> mAllCityList;
    private List<CityModel> mHotCityList;
    private List<String> mRecentCityList;
    public HashMap alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[] sections;// 存放存在的汉语拼音首字母
    private LocationClient myLocationClient;
    private String currentCity;//当前城市
    private MyLocationListener myLocationListener;
    private boolean isNeedRefresh;//当前定位的城市是否需要刷新
    private TextView tvCurrentLocateCity;
    private ProgressBar pbLocate;
    private TextView tvLocate;
    private final int VIEW_TYPE = 5;//view的类型个数

    public CityListAdapter(Context context, List<CityModel> allCityList,List<CityModel> hotCityList, List<String> recentCityList) {
        this.mContext = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;
        this.mRecentCityList=recentCityList;

        alphaIndexer = new HashMap();
        sections = new String[allCityList.size()];

        //这里的主要目的是将listview中要显示字母的条目保存下来，方便在滑动时获得位置，alphaIndexer在Acitivity有调用
        for (int i = 0; i < mAllCityList.size(); i++) {
            //当前汉语拼音首字母
            String currentStr = getAlpha(mAllCityList.get(i).getPinyi());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? getAlpha(mAllCityList.get(i - 1).getPinyi()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(mAllCityList.get(i).getPinyi());
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
        isNeedRefresh=true;
        initLocation();
    }
    @Override
    public int getViewTypeCount() {

        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return mAllCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        int viewType=getItemViewType(position);
        if(viewType ==0){//当前定位城市
            convertView=View.inflate(mContext, R.layout.frist_list_item,null);
            tvLocate=(TextView) convertView.findViewById(R.id.locateHint);
            tvCurrentLocateCity=(TextView) convertView.findViewById(R.id.lng_city);
            pbLocate = (ProgressBar) convertView.findViewById(R.id.pbLocate);
            if(!isNeedRefresh){
                tvLocate.setText("当前定位城市");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText(currentCity);
                pbLocate.setVisibility(View.GONE);
            }else{
                myLocationClient.start();
            }
            tvCurrentLocateCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pbLocate.setVisibility(View.VISIBLE);
                    tvLocate.setText("正在定位");
                    tvCurrentLocateCity.setVisibility(View.GONE);
                    myLocationClient.start();
                }
            });
        } else if (viewType == 1) {//最近访问城市
            convertView = View.inflate(mContext,R.layout.recent_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.recentHint);
            tvRecentVisitCity.setText("最近访问城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.recent_city);
            gvRecentVisitCity.setAdapter(new HitCityAdapter(mContext,mRecentCityList));

        }else if (viewType == 2) {//热门城市
            convertView = View.inflate(mContext,R.layout.recent_city, null);
            TextView tvRecentVisitCity=(TextView) convertView.findViewById(R.id.recentHint);
            tvRecentVisitCity.setText("热门城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView.findViewById(R.id.recent_city);
            gvRecentVisitCity.setAdapter(new HotCityAdapter(mContext,mHotCityList));
        } else if (viewType == 3) {//全部城市，仅展示“全部城市这四个字”
            convertView = View.inflate(mContext,R.layout.total_item, null);
//            TextView tvAllCityList=(TextView)convertView.findViewById(R.id.locateHint);
//            tvAllCityList.setText("全部城市");
//            ListView allCityList=(ListView)convertView.findViewById(R.id.allcity_list);
//            allCityList.setAdapter(new AllCityAdapter(mContext,mAllCityList));

        }else {//数据库中所有的城市的名字展示
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.list_item,null);
                viewHolder = new ViewHolder();
                viewHolder.tvAlpha = (TextView) convertView.findViewById(R.id.alpha);
                viewHolder.tvCityName = (TextView) convertView.findViewById(R.id.name);
                viewHolder.llMain=(LinearLayout) convertView.findViewById(R.id.ll_main);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position >= 1) {
                viewHolder.tvCityName.setText(mAllCityList.get(position).getName());
                viewHolder.llMain.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,mAllCityList.get(position).getName(),Toast.LENGTH_LONG).show();
                    }
                });
                //当前汉语拼音首字母
                String currentStr = getAlpha(mAllCityList.get(position).getPinyi());
                // 上一个汉语拼音首字母，如果不存在为" "
                String previewStr = (position - 1) >= 0 ? getAlpha(mAllCityList.get(position - 1).getPinyi()) : " ";
                //如果当前的条目的城市名字的拼音的首字母和其前一条条目的城市的名字的拼音的首字母不相同，则将布局中的展示字母的TextView展示出来
                if (!previewStr.equals(currentStr)) {
                    viewHolder.tvAlpha.setVisibility(View.VISIBLE);
                    viewHolder.tvAlpha.setText(currentStr);
                } else {
                    viewHolder.tvAlpha.setVisibility(View.GONE);
                }
            }

        }
        return convertView;
    }

    class ViewHolder {
        TextView tvAlpha;
        TextView tvCityName;
        LinearLayout llMain;
    }

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals(0)) {
            return "定位";
        } else if (str.equals(1)) {
            return "最近";
        } else if (str.equals(2)) {
            return "热门";
        } else if (str.equals(3)) {
            return "全部";
        } else {
            return "#";
        }
    }

    public void initLocation() {
        myLocationClient = new LocationClient(mContext);
        myLocationListener=new MyLocationListener();
        myLocationClient.registerLocationListener(myLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000); // 10分钟扫描1次
        option.setAddrType("all");
        option.setProdName("通过GPS定位我当前的位置");
        option.disableCache(true);
        option.setPriority(LocationClientOption.GpsFirst);
        myLocationClient.setLocOption(option);
        myLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation arg0) {

            isNeedRefresh=false;
            if(arg0.getCity()==null){
                //定位失败
                tvLocate.setText("未定位到城市,请选择");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText("重新选择");
                pbLocate.setVisibility(View.GONE);
                return;
            }else{
                //定位成功
                currentCity=arg0.getCity().substring(0,arg0.getCity().length()-1);
                tvLocate.setText("当前定位城市");
                tvCurrentLocateCity.setVisibility(View.VISIBLE);
                tvCurrentLocateCity.setText(currentCity);
                myLocationClient.stop();
                pbLocate.setVisibility(View.GONE);
            }
        }

    }

    class HitCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<String> hotCitys;

        public HitCityAdapter(Context context, List<String> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position));
            return convertView;
        }
    }

    class HotCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<CityModel> hotCitys;

        public HotCityAdapter(Context context, List<CityModel> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_city, null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position).getName());
            return convertView;
        }
    }

    class AllCityAdapter extends BaseAdapter{
        Context context;
        List<CityModel> list;
        CityModel model;

        public AllCityAdapter(Context context,List<CityModel> list){
            this.context=context;
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(context,R.layout.item_city,null);
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(list.get(position).getName());
            return convertView;
        }
    }


}
