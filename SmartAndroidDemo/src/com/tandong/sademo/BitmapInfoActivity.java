package com.tandong.sademo;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.bitmap.BitmapInfo;

public class BitmapInfoActivity extends SmartActivity {
	private BitmapInfo bitmapInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		bitmapInfo = new BitmapInfo(this);

		Bitmap bm = Bitmap.createBitmap(100, 100, Config.RGB_565);

		// Bitmap 转 byte[]
		byte[] b = bitmapInfo.bitmapToBytes(bm);

		// 将Bitmap对象转换为Drawable对象
		Drawable drawable = bitmapInfo.bitmapToDrawable(bm);

		// byte[]数组转为 Bitmap
		Bitmap bitmap = bitmapInfo.bytesToBimap(b);

		// 返回当前Bitmap图片对象宽度和高度,返回格式为：当前图片宽度和高度分别为@700@500
		String bitmapSize = bitmapInfo.getBitmapSize(bm);

		// 等比例缩放图片方法，第二个参数(0.1-1.0)为缩小;(1.1- )为放大
		Bitmap bitmapZoomScale = bitmapInfo.getBitmapZoom(bm, 0.5);

		// 指定图片路径，对图片进行等比例缩小，指定要缩小到的图片宽度，图片高度等比例自动缩小
		Bitmap bitmapZoomWidth = bitmapInfo
				.getBitmapZoom(Environment.getExternalStorageDirectory()
						+ "/smartandroid.png", 100);

		// 对Bitmap图片对象进行指定宽高缩放处理
		Bitmap bitmapZoom = bitmapInfo.getBitmapZoom(bm, 100, 100);

		// 获取SD卡上指定的图片原图
		Bitmap bmNoZoom = bitmapInfo.getLocalBitmap(Environment
				.getExternalStorageDirectory() + "/smartandroid.png");

		// 将Bitmap保存到指定路径里，默认命名为当前时间，格式为PNG。注意路径后要加/
		// 例如最终保存结果为：/sdcard/cacheImage/2013-08-06_14-57-52.png
		bitmapInfo.saveBitmap(bm, Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/");

		// 保存Bitmap到指定位置，指定文件名
		bitmapInfo.saveBitmap(bm, Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/", "smartandroid.png");

		// 保存Bitmap，可指定保存类型格式。要保存为的图片格式，只可输入：png或jpg或jpeg （不区分大小写）
		bitmapInfo.saveBitmap(bm, Environment.getExternalStorageDirectory()
				+ "/SmartAndroid/", "smartandroid", "png");

		// 保存Bitmap，可指定压缩率，默认为当前时间命名。 要保存的图片质量（0-100）值越低压缩率越高，不压缩则设为100
		bitmapInfo.saveBitmapQuality(bm, 50,
				Environment.getExternalStorageDirectory() + "/SmartAndroid/");
		
		// 注意需要写SD卡权限
	}

}
