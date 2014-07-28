package com.tandong.sademo;

import java.io.InputStream;

import android.os.Bundle;
import android.os.Environment;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;

public class AssistToolActivity extends SmartActivity {
	private AssistTool assistTool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		assistTool = new AssistTool(this);

		// 唤醒点亮屏幕
		assistTool.acquireWakeLock();

		// 如果目标文件夹不存在，则创建，存在就不处理
		assistTool.AutoMakeDir(Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/");

		// 将assets目录的文件复制到SD卡指定位置，指定文件名
		// (String ASSETS_NAME, String savePath,String saveName)
		assistTool.copy("smartandroid.jar",
				Environment.getExternalStorageDirectory() + "/SmartAndroid/",
				"smartAndroid2.0.jar");

		// 复制assets目录下的文件到手机里（位置可以自己指定）
		assistTool.copyAssets("html/smartandroid.html",
				Environment.getExternalStorageDirectory() + "/SmartAndroid/");

		// 设置SharedPreferences名,不设置时默认为userinfo_pref
		assistTool.userinfo = "smartandroid_sp";

		// 保存一个key方法
		assistTool.savePreferenceBoolean("isLogin", false);

		// 读取一个key值方法
		assistTool.getPreferenceBoolean("isLogin");

		assistTool.savePreferenceString("name", "smartAndroid");

		// 删除一个key
		assistTool.deleteKey("name");

		// 检测指定文件是否存在
		boolean fileExists = assistTool.FileExists(Environment
				.getExternalStorageDirectory() + "/smartandroid.jar");

		// 获取assets目录下指定文件的InputStream
		InputStream is = assistTool.getISAssets("smartAndroid.jar");

		// 读取assets目录下文本类型文件内容（html或txt等）
		String text = assistTool.getStringFromAssets("smartandroid.txt");

		// 获取APP的VersionName
		String version = assistTool.getVersion();

		// 获取APP的VersionCode
		int versionCode = assistTool.getVersionCode();

		// 获取wifi的ip地址
		String wifiIp = assistTool.getWifiIp();

		// 跳转到某个Activity，可指定是否关闭当前Activity
		assistTool.gotoActivity(MainActivity.class, true);

		// 跳转到某个Activity，可指定是否关闭当前Activity，并传递Bundle数据信息
		assistTool.gotoActivity(MainActivity.class, false, savedInstanceState);

		// 隐藏软键盘
		// assistTool.hideSoftKeyboard(view);

		// 从资源文件中（asset）读取文本文档(如果出现乱码，可以调换输入的格式参数charsetName)
		String assets_txt = assistTool.readTxtFromAssets(
				"html/smartandroid.txt", "utf-8");

		// 关闭屏幕
		assistTool.releaseWakeLock();

		// 弹出Toast提示,其他的几个构造方法类似用法
		assistTool.showToast("SmartAndroid");

		// 将时间戳毫秒数转换为标准的时间格式：yyyy-MM-dd_HH:mm:ss
		String normalTime = assistTool.toNormalTime(System.currentTimeMillis());

		// 将时间戳毫秒数转换为指定的时间格式：例如，yyyy-MM-dd_HH:mm:ss
		String time = assistTool.toNormalTime(System.currentTimeMillis(),
				"yyyy-MM-dd_HH:mm:ss");

		// 将JAVA里获取到的13位毫秒数转换为PHP使用的10位的时间戳毫秒数
		String phpTime = assistTool.toPHPTime(System.currentTimeMillis() + "");

		// 根据手机分辨率从dp转成px
		int px = assistTool.dip2px(this, 15);

		// 根据手机的分辨率从 px(像素) 的单位 转成为 dp
		int dip = assistTool.px2dip(this, 20);

		// 手机系统存储是否还有指定大小的空间
		boolean enoughSpaceOnPhone = assistTool.enoughSpaceOnPhone(1024);

		// 手机SD卡是否还有指定大小的空间
		boolean enoughSpaceOnSdCard = assistTool.enoughSpaceOnSdCard(1024);

		// 获取手机系统存储可用空间
		long realSizeOnPhone = assistTool.getRealSizeOnPhone();

		// 获取手机SD卡存储可用空间
		long realSizeOnSdcard = assistTool.getRealSizeOnSdcard();

		// 检测SD卡是否可用
		boolean sdCardIsAvailable = assistTool.sdCardIsAvailable();

		// 打印输出日志：System.out.println(log);
		assistTool.print("smartandroid Log");

	}

}
