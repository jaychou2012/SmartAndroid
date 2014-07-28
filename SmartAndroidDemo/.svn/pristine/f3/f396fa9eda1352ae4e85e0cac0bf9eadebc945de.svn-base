package com.tandong.sademo;

import android.os.Bundle;

import com.tandong.sa.activity.SmartFragmentActivity;

public class MainFragmentActivity extends SmartFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 跳转到目标MainActivity，关闭当前Activity
		gotoActivity(MainActivity.class, true);

		// 跳转到目标MainActivity,不关闭当前Activity，传递过去Bundle信息类
		gotoActivity(MainActivity.class, false, savedInstanceState);

		// 其他方法类似SmartActivity
	}

}
