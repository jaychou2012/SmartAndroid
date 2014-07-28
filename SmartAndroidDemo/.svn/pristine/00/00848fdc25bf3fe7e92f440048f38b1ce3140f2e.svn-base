package com.tandong.sademo;

import android.os.Bundle;
import android.os.Environment;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;

public class WelcomActivity extends SmartActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AssistTool.setFullScreen(this);
		setContentView(R.layout.activity_welcome);
		AssistTool at = new AssistTool(this);
		at.AutoMakeDir(Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/");

		at.copy("demo.zip", Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/", "demo.zip");
		at.copy("demo.zip", Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/", "demo2.zip");
		CountJump(2000, HomeActivity.class, true);
	}
}
