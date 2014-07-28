package com.tandong.sademo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 
 * @author <strong>AppCount 谭东</strong></br>
 *         AppCount统计功能的方法对象，使用统计功能需要创建，然后调用里面的所需功能方法即可</br>
 *         <strong>含有的可选方法功能：</strong></br> void getApp_Location()
 *         用于统计用户所使用APP的位置的方法，可选</br> String setApp_Suggest(String suggest)
 *         接收用户反馈意见的功能方法，可选</br> String setApp_extra(String extra)
 *         用于接收开发者自定义要接收统计用户信息的功能方法，可选</br> String setApp_Score(String score)
 *         用于获取用户积分的功能方法，可选</br> String show() 用于测试，看是否可以正常返回要得到统计的信息，可选</br>
 *         boolean checkNetInfo() 测试网络和WIFI是否正常连接，可选</br> void appInfo_Commit()
 *         POST提交数据到AppCount，最后一步，必选</br>
 */

public class AppUserInfo {

	private Context c;
	public static final String APPCOUNT_API = "http://appcount.sinaapp.com/appapi.php";
	private String AppId;
	private String Tel_id;
	private String App_lasttime;
	private String App_info;
	private String app_json;
	private JSONObject object;
	private SharedPreferences sp;
	private String sp_app_count;
	private int sp_count;
	private String json_result;
	public String address;

	/**
	 * <strong>AppCount统计的构造方法对象，需要创建使用</strong><br>
	 * 
	 * @param context
	 *            上下文,要在哪个Activity里使用
	 * @param app_id
	 *            您的AppCount里的添加的要统计的APP的ID
	 */
	public AppUserInfo(Context context, String app_id) {
		this.c = context;
		object = new JSONObject();
		sp = c.getSharedPreferences("app_count", c.MODE_PRIVATE);// 私有
		Editor editor = sp.edit();
		sp_app_count = sp.getString("app_count", "0");
		if (sp_app_count == null) {
			editor.putString("app_count", "0");
		}
		sp_count = Integer.parseInt(sp_app_count);
		sp_count = sp_count + 1;
		editor.putString("app_count", sp_count + "");
		editor.commit();
		getTel_Model();
		getTel_System();
		getApp_count();
		this.AppId = app_id;
		this.Tel_id = getTel_id();
		this.App_lasttime = getApp_lasttime();

		try {
			object.put("app_id", AppId);
			object.put("tel_id", Tel_id);
			object.put("app_lasttime", App_lasttime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getTel_id() {// 需要权限：<uses-permission
								// android:name="android.permission.READ_PHONE_STATE"/>
		TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		return imei;
	}

	private String getApp_lasttime() {// 最后登陆时间
		String time = System.currentTimeMillis() + "";
		String time_php = time.substring(0, 10);
		return time_php;
	}

	private String getTel_Model() {// 手机型号
		try {
			object.put("tel_model", Build.MODEL);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Build.MODEL;
	}

	private String getTel_System() {// 手机系统版本
		try {
			object.put("tel_system", Build.VERSION.RELEASE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Build.VERSION.RELEASE;
	}

	private String getApp_count() {// 启动次数
		try {
			object.put("app_count", sp.getString("app_count", "1"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp.getString("app_count", "1");
	}

	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());

			sb.append("\nraddress : ");
			sb.append(location.getAddrStr());
			address = location.getAddrStr();
			try {
				object.put("app_location", address);
				Log.i("info", "监听器里的地址：" + address);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}
			address = location.getAddrStr();
			// Coordinates="经度："+location.getLongitude()+"，"+location.getAltitude();
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("Poi time : ");
			sb.append(poiLocation.getTime());
			sb.append("\nerror code : ");
			sb.append(poiLocation.getLocType());
			sb.append("\nlatitude : ");
			sb.append(poiLocation.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(poiLocation.getLongitude());
			sb.append("\nradius : ");
			sb.append(poiLocation.getRadius());
			sb.append("\nraddress : ");
			sb.append(poiLocation.getAddrStr());

			address = poiLocation.getAddrStr();

			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(poiLocation.getAddrStr());
			}
			if (poiLocation.hasPoi()) {
				sb.append("\nPoi:");
				sb.append(poiLocation.getPoi());
			} else {
				sb.append("noPoi information");
			}
			Log.i("position", sb.toString());
		}
	}

	/**
	 * 获取用户位置信息的方法
	 * 
	 * @return 用户位置
	 */
	public String getApp_Location() {// 位置
		BDLocationListener myListener = new MyLocationListener();
		LocationClient mLocationClient = new LocationClient(c); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		// setOption();
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		// option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		mLocationClient.setLocOption(option);

		mLocationClient.start();
		mLocationClient.requestLocation();

		// try {
		// object.put("app_location",address);
		// Log.i("info", "目前位置："+address);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Log.i("info", "API里的位置：" + address);

		return address;
	}

	/**
	 * 获取用户反馈意见的方法
	 * 
	 * @param suggest
	 *            用户输入的反馈内容
	 * @return 用户反馈
	 */
	public String setApp_Suggest(String suggest) {// 反馈建议
		try {
			object.put("app_suggest", suggest);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suggest;
	}

	/**
	 * 获取开发者自定义要获取的用户属性信息方法
	 * 
	 * @param extra
	 *            统计的任意信息
	 * @return 传入的任意内容
	 */
	public String setApp_extra(String extra) {// APP备注
		try {
			object.put("app_extra", extra);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extra;
	}

	/**
	 * 获取开发者自定义的用户当前积分
	 * 
	 * @param score
	 *            用户当前积分
	 * @return 用户当前积分
	 */
	public String setApp_Score(String score) {// 用户积分
		try {
			object.put("app_score", score);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}

	/**
	 * 用于测试调试
	 * 
	 * @return 统计的部分属性信息，返回的是String
	 */
	public String show() {
		App_info = "应用信息：" + AppId + "，" + Tel_id + "，" + getTel_Model() + "，" + getTel_System() + "，"
				+ getApp_lasttime() + "，" + getApp_count() + "，" + "，" + object.toString();
		return App_info;
	}

	/**
	 * 开启一个异步线程提交用户统计数据
	 * 
	 */
	public void appInfo_Commit() {// 提交信息到服务器

		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 200:
					json_result = (String) msg.obj;
					// Toast.makeText(c, json_result, 3000).show();

					Log.i("position", "JSON串：" + json_result);

					try {
						JSONObject object = new JSONObject(msg.obj.toString());
						String app_id = object.getString("appid");
						// Toast.makeText(c, "返回结果的App_id：" + app_id,
						// 3000).show();

						Log.i("position", app_id);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.i("position", msg.obj.toString());
					break;

				default:
					Toast.makeText(c, "错误！", 3000).show();//
					break;
				}

				super.handleMessage(msg);
			}

		};

		new Thread() {// 新建一个线程，实现里面的方法，重写run()方法
			public void run() {

				// 创建http客户端对象
				HttpClient client = new DefaultHttpClient();

				// 创建post方式的请求对象
				HttpPost request = new HttpPost(APPCOUNT_API);
				// 以下三行创建并封装两个键/值对数据，存放在list集合中
				ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				Log.i("info", object.toString());
				list.add(new BasicNameValuePair("appinfo", object.toString()));

				try {
					// 将集合数据封装在reqEntity对象中
					UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(list, "UTF-8");
					// 将数据封装在request中
					request.setEntity(reqEntity);
					// 向服务器发送pos睛求
					HttpResponse response = client.execute(request);
					// 若服务器返回的状态码为HttpStatus.SC_OK常量值(200)
					HttpEntity entity = response.getEntity();
					String info = EntityUtils.toString(entity);
					// 设置返回值
					Message msg = new Message();
					msg.what = response.getStatusLine().getStatusCode();
					msg.obj = info;
					// msg.obj=request.getWebContext();
					handler.sendMessage(msg);
					// if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					// //从响应对象中获取响应的实体数据
					// HttpEntity entity=response.getEntity();
					// //转换为字符串，并显示在标签中
					// tv_result.setText(EntityUtils.toString(entity));
					// }
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};

		}.start();

		// if (checkNetInfo()) {
		//
		// }else{//提交到数据库
		//
		// }
	}

	/**
	 * 用户获取网络状态
	 * 
	 * @return 网络和WIFI是否有一样连通，返回boolean类型
	 */
	public boolean checkNetInfo() {
		// 判断网络是否连接
		ConnectivityManager con = (ConnectivityManager) c.getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();// wifi状态
		boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting(); // Network状态
		if (wifi || internet) {
			return true;
		} else {
			return false;
		}

	}

	private void setOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		// option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		// mLocationClient.setLocOption(option);
	}

	public void stopLocationListener() {

	}

}
