package com.kb.fragment.First;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.kb.firstitem.EntertainmentActivity;
import com.kb.firstitem.HomeDepotActivity;
import com.kb.firstitem.Housekepping.HousekeepingActivity;
import com.kb.firstitem.OilsGroceryActivity;
import com.kb.firstitem.Sport.SportsActivity;
import com.kb.location.SelectLocalActivity;
import com.kb.location.LocationApplication;
import com.kb.adapter.FirstPopupAdapter;
import com.kb.adapter.ViewPagerAdapter;
import com.kb.firstitem.cate.CateActivity;
import com.kb.firstitem.Framer.FarmersActivity;
import com.kb.firstitem.News.NeighborhoodNewsActivity;
import com.kb.lifeknow.R;
import com.kb.model.ViewPagerModel;
import com.kb.service.LocationService;
import com.kb.view.FristViewPager;
import com.kb.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FristFragment extends Fragment implements View.OnClickListener{

    private FristViewPager mViewPager;
    private  LinearLayout mDotLayout;
    private ArrayList<ViewPagerModel> list=new ArrayList<ViewPagerModel>();
    private Thread thread;//子线程轮播对象

    private TextView mIntroTv,txt_frist_local;
    private ImageButton img_more;
    private ViewPagerAdapter mViewPagerAdapter;
    private MyGridView mGridView;

    private RelativeLayout re_first_local,re_search,re_bottom;
    private ImageView up_down;
    private TextView txt_pop_local;
    private View mView;
    private PopupWindow mPopupWindow;
    private String loc;//保存地址
    private GridView pop_grid;


    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] iconName = {"周边新闻", "快餐美食", "生鲜果蔬", "休闲娱乐", "粮油副食", "家居百货","社区服务", "家政服务",
            "旅游住宿", "相亲交友", "体育活动", "我要发布"};

    private LocationService locationService;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frist, null);
        return view;
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txt_frist_local.setMovementMethod(ScrollingMovementMethod.getInstance());

        setLinstener();
        setGridLisener();
        initData();
    }

    /**
     * 显示请求字符串
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (txt_frist_local != null)
                txt_frist_local.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /***
     * Stop location service
     */
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        locationService = ((LocationApplication)getActivity().getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type =getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
    }
    /**
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    public  BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                System.out.println(sb.append(location.getAddress().address.toString()));

                loc=location.getCity().substring(0,location.getCity().length() - 1);
                logMsg(loc);
            }
        }

    };

    /**
     * 初始化控价
     */
    private void initView() {
        mViewPager = (FristViewPager) view.findViewById(R.id.viewPager);
        mIntroTv = (TextView) view. findViewById(R.id.tv_intro);
        txt_frist_local=(TextView) view. findViewById(R.id.txt_frist_local);
        mDotLayout = (LinearLayout) view. findViewById(R.id.dot_layout);
        mGridView= (MyGridView) view.findViewById(R.id.grid);
        re_first_local=(RelativeLayout)view.findViewById(R.id.re_first_local);
        re_search=(RelativeLayout)view.findViewById(R.id.re_search);
        up_down=(ImageView)view.findViewById(R.id.up_down);

        img_more=(ImageButton)view.findViewById(R.id.img_first_more);

        re_first_local.setOnClickListener(this);
        img_more.setOnClickListener(this);
    }

    /**
     * 设置Viewpager的事件监听
     */
    public  void setLinstener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                updateIntroAndDot();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始ViewPager化数据
     */
    private void initData(){

        list.clear();
        list.add(new ViewPagerModel(R.mipmap.qq,"可爱的茶杯兔"));
        list.add(new ViewPagerModel(R.mipmap.hudie,"飞舞的彩蝶"));
        list.add(new ViewPagerModel(R.mipmap.qq,"可爱的茶杯兔"));
        list.add(new ViewPagerModel(R.mipmap.hudie,"飞舞的彩蝶"));
        initDots();
        if(mViewPagerAdapter==null){
            mViewPagerAdapter=new ViewPagerAdapter(getActivity(),list);
        }else{
            mViewPagerAdapter.notifyDataSetChanged();
        }
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.setCurrentItem(Integer.MAX_VALUE/2- Integer.MAX_VALUE / 2 % list.size());
        if(thread==null){
            startthreadLunbo();//开启子线程轮播
            thread.start();
        }
    }
    private void startthreadLunbo() {
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    };

    /**
     * 初始化Dot数据
     */
    public void initDots(){

        for (int i=0;i<list.size();i++){
            View view=new View(getActivity());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);//设置远点Dots的大小
            if(i!=0){//第一个点不需要左边距
                params.leftMargin=10;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selector_dot);
            mDotLayout.addView(view);
        }
    }
    /**
     * 更新文本和Dots
     */
    private void updateIntroAndDot(){
        int currentPage = mViewPager.getCurrentItem()%list.size();
        mIntroTv.setText(list.get(currentPage).getIntro());
        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            mDotLayout.getChildAt(i).setEnabled(i==currentPage);//设置setEnabled为true的话 在选择器里面就会对应的使用白色颜色
        }
    }
    /**
     * 设置GridView的事件监听
     */
    public void setGridLisener(){
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getGridData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.itemImage, R.id.itemname};
        SimpleAdapter sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.activity_grid_view_adapter, from, to);
        mGridView.setAdapter(sim_adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getAdapter().getItem(position);
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), NeighborhoodNewsActivity.class));
                        Toast.makeText(getActivity(),"周边新闻",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CateActivity.class));
                        Toast.makeText(getActivity(),"快餐美食",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), FarmersActivity.class));
                        Toast.makeText(getActivity(),"生鲜果蔬",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(),EntertainmentActivity.class));
                        Toast.makeText(getActivity(),"休闲娱乐",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(),OilsGroceryActivity.class));
                        Toast.makeText(getActivity(),"粮油副食",Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), HomeDepotActivity.class));
                        Toast.makeText(getActivity(),"家具百货",Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(getActivity(),"社区服务",Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(),HousekeepingActivity.class));
                        Toast.makeText(getActivity(),"家政服务",Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        Toast.makeText(getActivity(),"旅游住宿",Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        Toast.makeText(getActivity(),"相亲交友",Toast.LENGTH_LONG).show();
                        break;
                    case 10:
                        startActivity(new Intent(getActivity(), SportsActivity.class));
                        Toast.makeText(getActivity(),"体育活动",Toast.LENGTH_LONG).show();
                        break;
                    case 11:
                        Toast.makeText(getActivity(),"我要发布",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     *  Gridview中的数据获取
     * @return
     */
    public List<Map<String, Object>> getGridData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_first_local:
                showLocalPopupWindow();
                break;
            case R.id.img_first_more:
                setMorePopuWindow();
                break;
        }
    }
    /**
     * 显示地区弹窗
     */
    private void showLocalPopupWindow() {
        up_down.setImageResource(R.mipmap.up);
        // 一个自定义的布局，作为显示的内容
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.frist_local_pop, null);
        mPopupWindow = new PopupWindow(mView, LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //设置PopupWindow的宽高
        mPopupWindow.setWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth());
        mPopupWindow.setHeight(getActivity().getWindowManager().getDefaultDisplay().getHeight()*4/10);
        //让键盘弹出时，不会挡住pop窗口。
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setFocusable(true);//获取焦点
        mPopupWindow.setOutsideTouchable(true);//点击空白处时，隐藏掉pop窗口
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景
        backgroundAlpha(0.3f);//设置背景为半透明色
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);//还原背景色
                up_down.setImageResource(R.mipmap.down);
            }
        });
        getpopuData();
        mPopupWindow.showAsDropDown(re_search,Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,20);
    }
    private void getpopuData(){
        pop_grid= (GridView) mView.findViewById(R.id.popu_grid);
        txt_pop_local=(TextView)mView.findViewById(R.id.txt_pop_local);
        re_bottom=(RelativeLayout)mView.findViewById(R.id.re_bottom);
        re_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SelectLocalActivity.class);
                startActivity(intent);
            }
        });
        final ArrayList<String> grid_list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            grid_list.add("重庆"+i);
        }
        FirstPopupAdapter popu_adapter=new FirstPopupAdapter(grid_list,getActivity());
        pop_grid.setAdapter(popu_adapter);

        txt_pop_local.setText("当前城市："+loc);

        pop_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt_frist_local.setText(grid_list.get(position));
                mPopupWindow.dismiss();
            }
        });
    }
    private void setMorePopuWindow(){
        mView=LayoutInflater.from(getActivity()).inflate(R.layout.frist_more_pop,null);
        mPopupWindow=new PopupWindow(mView , LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setWidth(350);
        mPopupWindow.setHeight(370);
        mPopupWindow.setFocusable(true);//获取焦点
        mPopupWindow.setOutsideTouchable(true);//点击空白处时，隐藏掉pop窗口
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景
        mPopupWindow.showAsDropDown(re_search,500,10);
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

}
