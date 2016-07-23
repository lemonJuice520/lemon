package com.kb.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kb.adapter.CityListAdapter;
import com.kb.adapter.ResultLocalListAdapter;
import com.kb.lifeknow.R;
import com.kb.model.CityModel;
import com.kb.utils.PingYinUtil;
import com.kb.view.MyLetterListView;

public class SelectLocalActivity extends Activity implements AbsListView.OnScrollListener {
	private CityListAdapter adapter;
	private ResultLocalListAdapter resultListAdapter;
	private ListView personList,resultList;//显示所有城市/显示查找出的所有城市
	private MyLetterListView letterListView; // A-Z listview
	private TextView overlay;// overlay对话框首字母
	private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置

	private String[] sections;// 存放存在的汉语拼音首字母
	private Handler handler;
	private OverlayThread overlayThread; // 显示首字母对话框
	private ArrayList<CityModel> allCity_lists; // 所有城市列表
	private ArrayList<CityModel> city_lists;// 城市列表
	private ArrayList<CityModel> city_hot;//热门城市
	private ArrayList<CityModel> city_result;
	private ArrayList<String> city_history;//最近访问城市
	private EditText search;
	TextView tv_noresult;

	private String currentCity; // 用于保存定位到的城市
	private int locateProcess = 1; // 记录当前定位的状态 正在定位-定位成功-定位失败
	private boolean isNeedFresh;

//	private DatabaseHelper helper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_select);
		initView();
		cityInit();
		hotCityInit();
//		helper = new DatabaseHelper(this);
		setEditSerch();

		letterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
		overlayThread = new OverlayThread();
		isNeedFresh = true;
		personList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if (position >= 4) {

					Toast.makeText(getApplicationContext(),
							allCity_lists.get(position).getName(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		locateProcess = 1;
		personList.setAdapter(adapter);
		personList.setOnScrollListener(this);
		resultList.setAdapter(resultListAdapter);
		resultList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Toast.makeText(getApplicationContext(),
						city_result.get(position).getName(), Toast.LENGTH_SHORT)
						.show();
			}
		});
		initOverlay();
		cityInit();
//		hisCityInit();
		setAdapter(allCity_lists, city_hot, city_history);

	}
//	public void InsertCity(String name) {
//		SQLiteDatabase db = helper.getReadableDatabase();
//		Cursor cursor = db.rawQuery("select * from recentcity where name = '"
//				+ name + "'", null);
//		if (cursor.getCount() > 0) { //
//			db.delete("recentcity", "name = ?", new String[] { name });
//		}
//		db.execSQL("insert into recentcity(name, date) values('" + name + "', "
//				+ System.currentTimeMillis() + ")");
//		db.close();
//	}


	/**
	 * 刷新界面
	 */
	public void initView(){
		personList = (ListView) findViewById(R.id.list_view);
		tv_noresult = (TextView) findViewById(R.id.tv_noresult);
		letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
		resultList = (ListView) findViewById(R.id.search_result);
		search = (EditText) findViewById(R.id.sh);

		allCity_lists = new ArrayList<CityModel>();
		city_hot = new ArrayList<CityModel>();
		city_history = new ArrayList<String>();
		city_result = new ArrayList<CityModel>();
		resultListAdapter = new ResultLocalListAdapter(this, city_result);
	}
	/**
	 * EditText实现筛选城市的功能
	 */
	public void setEditSerch(){
		//设置监听
		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.toString() == null || "".equals(s.toString())) {
					letterListView.setVisibility(View.VISIBLE);
					personList.setVisibility(View.VISIBLE);
					resultList.setVisibility(View.GONE);
					tv_noresult.setVisibility(View.GONE);
				} else {
					city_result.clear();
					letterListView.setVisibility(View.GONE);
					personList.setVisibility(View.GONE);
//					getResultCityList(s.toString());
					if (city_result.size() <= 0) {
						tv_noresult.setVisibility(View.VISIBLE);
						resultList.setVisibility(View.GONE);
					} else {
						tv_noresult.setVisibility(View.GONE);
						resultList.setVisibility(View.VISIBLE);
						resultListAdapter.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	private void cityInit() {
		CityModel city = new CityModel("定位", "0"); // 当前定位城市
		allCity_lists.add(city);
		city = new CityModel("最近", "1"); // 最近访问的城市
		allCity_lists.add(city);
		city = new CityModel("热门", "2"); // 热门城市
		allCity_lists.add(city);
		city = new CityModel("全部", "3"); // 全部城市
		allCity_lists.add(city);
//		city_lists = getCityList();
//		allCity_lists.addAll(city_lists);
	}

	/**
	 * 热门城市
	 */
	public void hotCityInit() {
		CityModel city = new CityModel("上海", "2");
		city_hot.add(city);
		city = new CityModel("北京", "2");
		city_hot.add(city);
		city = new CityModel("重庆", "2");
		city_hot.add(city);
		city = new CityModel("成都", "2");
		city_hot.add(city);
		city = new CityModel("广州", "2");
		city_hot.add(city);
		city = new CityModel("深圳", "2");
		city_hot.add(city);
		city = new CityModel("武汉", "2");
		city_hot.add(city);
		city = new CityModel("天津", "2");
		city_hot.add(city);
		city = new CityModel("西安", "2");
		city_hot.add(city);
		city = new CityModel("南京", "2");
		city_hot.add(city);
		city = new CityModel("杭州", "2");
		city_hot.add(city);
	}
/*
	private void hisCityInit() {
//		SQLiteDatabase db = helper.getReadableDatabase();
//		Cursor cursor = db.rawQuery(
//				"select * from recentcity order by date desc limit 0, 3", null);
//		while (cursor.moveToNext()) {
//			city_history.add(cursor.getString(1));
//		}
//		cursor.close();
//		db.close();
	}*/

//	private void getResultCityList(String keyword) {
//		DBHelper dbHelper = new DBHelper(this);
//		try {
//			dbHelper.createDataBase();
//			SQLiteDatabase db = dbHelper.getWritableDatabase();
//			Cursor cursor = db.rawQuery(
//					"select * from city where name like \"%" + keyword
//							+ "%\" or pinyin like \"%" + keyword + "%\"", null);
//			CityModel city;
//			Log.e("info", "length = " + cursor.getCount());
//			while (cursor.moveToNext()) {
//				city = new CityModel(cursor.getString(1), cursor.getString(2));
//				city_result.add(city);
//			}
//			cursor.close();
//			db.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//	}

	private void setAdapter(List<CityModel> list, List<CityModel> hotList,List<String> hisCity) {
		adapter = new CityListAdapter(this, list, hotList, hisCity);
		personList.setAdapter(adapter);
	}


	@SuppressWarnings("unchecked")
//	private ArrayList<CityModel> getCityList() {
//		DBHelper dbHelper = new DBHelper(this);
//		ArrayList<CityModel> list = new ArrayList<CityModel>();
//		try {
//			dbHelper.createDataBase();
//			SQLiteDatabase db = dbHelper.getWritableDatabase();
//			Cursor cursor = db.rawQuery("select * from city", null);
//			CityModel city;
//			while (cursor.moveToNext()) {
//				city = new CityModel(cursor.getString(1), cursor.getString(2));
//
//				System.out.println(cursor.getString(1).toString());
//
//				list.add(city);
//			}
//			cursor.close();
//			db.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}


	private boolean mReady;

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		mReady = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private boolean isScroll = false;

	private class LetterListViewListener implements
			MyLetterListView.OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(final String s) {
			isScroll = false;
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				personList.setSelection(position);
				overlay.setText(s);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				handler.postDelayed(overlayThread, 1000);
			}
		}
	}

	// 设置overlay不可见
	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL
				|| scrollState == SCROLL_STATE_FLING) {
			isScroll = true;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		if (!isScroll) {
			return;
		}

		if (mReady) {
			String text;
			String name = allCity_lists.get(firstVisibleItem).getName();
			String pinyin = allCity_lists.get(firstVisibleItem).getPinyi();
			if (firstVisibleItem < 4) {
				text = name;
			} else {
				text = PingYinUtil.converterToFirstSpell(pinyin)
						.substring(0, 1).toUpperCase();
			}
			overlay.setText(text);
			overlay.setVisibility(View.VISIBLE);
			handler.removeCallbacks(overlayThread);
			// 延迟一秒后执行，让overlay为不可见
			handler.postDelayed(overlayThread, 1000);
		}
		}
		}