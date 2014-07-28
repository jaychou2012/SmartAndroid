package com.tandong.sademo;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.appInfo.AppInfo;
import com.tandong.sa.entity.MyPermission;
import com.tandong.sademo.R;

public class AppInfoActivity extends SmartActivity {

	private AppInfo appInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_appinfo);

		appInfo = new AppInfo(this);

		// 获取手机上所有第三方应用:格式(微信,QQ)
		String thirdApps = appInfo.getAllThirdApp();

		// 获取APP包名
		String packageName = appInfo.getPackageName();

		// 获取某个包名APP的所需的用户权限集合
		ArrayList<MyPermission> permissions = appInfo
				.getUsesPermission(packageName);

		// 获取APP的Icon
		Drawable icon = appInfo.getAppIcon(this);

		// 获取APP名字
		String appName = appInfo.getAppName(this);

		// 获取App第一次安装时间
		long firstInstallTime = appInfo.getFirstInstallTime(this);

		// 获取最后使用时间
		long lastUpdateTime = appInfo.getLastUpdateTime(this);

		// 获取App版本号
		int verCode = appInfo.getVerCode(this);

		// 获取App版本名
		String verName = appInfo.getVerName(this);
	}

}
