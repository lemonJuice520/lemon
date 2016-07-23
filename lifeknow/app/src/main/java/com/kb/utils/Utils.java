package com.kb.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kb.model.CityModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类
 * 
 * @author zihao
 * 
 */
public class Utils {


	public static String getData(String path) throws Exception {
		StringBuffer sb = new StringBuffer();
		URL url;
		url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		InputStream stream = connection.getInputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = stream.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, len, "UTF-8"));
		}
		return sb.toString();
	}

	public static String submitPostData(String url,Map<String,String> params,String encode){
		byte[] data = getRequestData(params, encode).toString().getBytes();//获得请求体
		try {
			URL httpurl=new URL(url);
			HttpURLConnection conn= (HttpURLConnection) httpurl.openConnection();
			conn.setReadTimeout(5000);//设置连接超时时间
			conn.setRequestMethod("POST");//设置以Post方式提交数据
			conn.setUseCaches(false);//使用Post方式不能使用缓存
			//获得输出流，向服务器写入数据
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(data);

			int response = conn.getResponseCode();            //获得服务器的响应码
			if(response == HttpURLConnection.HTTP_OK) {
				InputStream inptStream = conn.getInputStream();
				return dealResponseResult(inptStream);                     //处理服务器的响应结果
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	public static Bitmap getPhoto(String urlpath) {
		Bitmap bitmap = null;
		try {
			URL url = new URL( urlpath);
			URLConnection connection = url.openConnection();
			bitmap = BitmapFactory.decodeStream(connection.getInputStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
	public static void getImg(String imgpath, ImageView img_view) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
				.build();

		ImageLoader.getInstance().displayImage(
				"http://tnfs.tngou.net/img" + imgpath, img_view, options);
	}

	/**
	 * 获取现在时间
	 * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = dateFormat.format(new Date(System.currentTimeMillis()));
		return time;
	}
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static void showToast(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 判断一个字符串的位数
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isMatchLenth(String str,int length){
		if(str.isEmpty()){
			return false;
		}else{
			return str.length()==length?true:false;
		}
	}
	/**
	 * 验证手机格式
	 * @param mobileNums
	 * @return
	 */
	public static boolean isMobileNo(String mobileNums){
		String telRegex="[1][358]\\d{9}";
		if (TextUtils.isEmpty(mobileNums)){
			return false;
		}else
			return mobileNums.matches(telRegex);
	}

		/** Function  :   封装请求体信息
         * Param     :   params请求体内容，encode编码格式
         */
	public static StringBuffer getRequestData(Map<String, String> params, String encode) {
		StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
		try {
			for(Map.Entry<String, String> entry : params.entrySet()) {
				stringBuffer.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

	   /** Function  :   处理服务器的响应结果（将输入流转化成字符串）
       * Param     :   inputStream服务器的响应输入流
       */
	     public static String dealResponseResult(InputStream inputStream) {
		         String resultData = null;      //存储处理结果
		         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		         byte[] data = new byte[1024];
		         int len = 0;
		         try {
			             while((len = inputStream.read(data)) != -1) {
				                 byteArrayOutputStream.write(data, 0, len);
				             }
			         } catch (IOException e) {
			             e.printStackTrace();
			         }
		         resultData = new String(byteArrayOutputStream.toByteArray());
		         return resultData;
		     }

	/**
	 * a-z排序
	 */
	public static Comparator comparator = new Comparator<CityModel>() {
		@Override
		public int compare(CityModel lhs, CityModel rhs) {
			String a = lhs.getPinyi().substring(0, 1);
			String b = rhs.getPinyi().substring(0, 1);
			int flag = a.compareTo(b);
			if (flag == 0) {
				return a.compareTo(b);
			} else {
				return flag;
			}
		}
	};

	public static String md5(String paramString) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		} catch (Exception e) {
			return paramString;
		}
	}
	/**
	 * 将指定byte数组转换成16进制字符串
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 判断是否有网络
	 * @param context
	 * @return
	 */
	public static boolean isNetWork(Context context){
		//得到网络的管理者
		ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if(info!=null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * @param context
	 * @return
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo) || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 重新计算ListView的高度(ScorllView 中如果再放入scrollView 是无法计算的，我们可以计算后再赋值)
	 * 避免Scrollview和Listview的布局；
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView==null){
			return;
		}
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}


}