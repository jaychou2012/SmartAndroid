package com.tandong.sademo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tandong.sa.activity.SmartActivity;

public class MainActivity extends SmartActivity implements OnClickListener {
	private TextView tv_textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		// 跳转到目标MainFragmentActivity，关闭当前Activity
		gotoActivity(MainFragmentActivity.class, true);

		// 跳转到目标MainFragmentActivity,不关闭当前Activity，传递过去Bundle信息类
		gotoActivity(MainFragmentActivity.class, false, savedInstanceState);

		// 开启调试输出日志模式标志位(默认为true)
		alwaysShowPrint = true;
		// 输出日志方法
		print("SmartAndroid调试可控输出日志");

		// 设置Activity关闭或跳转时的动画效果
		// (在finish Activity的时候调用，类似overridePendingTransition)
		finish(R.anim.bottomview_anim_enter, R.anim.bottomview_anim_exit);

		// 设置禁止弹出软键盘操作
		disableSoftkeyBoardAutoShow();

		// 保持界面唤醒状态显示
		keepScreenOn();

		// 唤醒点亮屏幕
		acquireWakeLock();

		// 恢复屏幕到黑暗
		releaseWakeLock();

		// // 获取本机wifi网络下的ip地址
		// String ip = getWifiIp();

		// 隐藏软键盘
		hideSoftKeyboard(tv_textview);

		// 弹出Toast提示,其他的几个构造方法类似用法
		showToast("SmartAndroid");

		// 设置SharedPreferences名,不设置时默认为userinfo_pref
		userinfo = "smartandroid_sp";
		// 保存一个key方法
		savePreferenceBoolean("isLogin", false);
		// 读取一个key值方法
		getPreferenceBoolean("isLogin");
		savePreferenceString("name", "smartAndroid");
		// 删除一个key
		deleteKey("name");

		// // Activity设为无标题栏风格
		// setNoTitle();
		//
		// // Activity设为全屏风格
		// setFullScreen();

		// 设置横屏显示
		screenLandscapeDir();

		// 检测sdcard是否可用
		boolean isSdAvailable = sdCardIsAvailable();

		// 根据手机分辨率从dp转成px
		int px = dip2px(this, 15);

		// 根据手机的分辨率从 px(像素) 的单位 转成为 dp
		int dip = px2dip(this, 20);

		// 获取APP的版本名：versionName
		String version = getVersion();

		// 获取APP的版本号：versionCode
		int versionCode = getVersionCode();

	}

	private void initView() {
		tv_textview = (TextView) this.findViewById(R.id.tv_textview);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}
