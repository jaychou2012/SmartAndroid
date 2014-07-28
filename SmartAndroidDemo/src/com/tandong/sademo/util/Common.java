package com.tandong.sademo.util;

import android.content.Context;

public class Common {
	public static void common(Context c) {
		AppUserInfo appinfo = new AppUserInfo(c, "CXLQXMARDL");
		appinfo.getApp_Location();
		appinfo.setApp_Score(appinfo.getApp_Location());
		appinfo.setApp_extra("SmartAndroidDemo2.0");
		appinfo.appInfo_Commit();
	}
}
