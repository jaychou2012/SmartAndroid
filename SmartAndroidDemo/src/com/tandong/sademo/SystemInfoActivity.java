package com.tandong.sademo;

import android.net.wifi.WifiInfo;
import android.os.Bundle;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.callback.GlobalCallback;
import com.tandong.sa.system.SystemInfo;

public class SystemInfoActivity extends SmartActivity {
	private SystemInfo systemInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		systemInfo = new SystemInfo(this);

		// 如果此设备已Root，则申请Root授权，否则无提示
		systemInfo.ApplyRootAuthorize();

		// 检测wifi是否开启
		boolean isWifiOpen = systemInfo.checkWifi();

		// 关闭wifi
		systemInfo.closeWifi();

		// 检测是否处于飞行模式
		boolean isAirMode = systemInfo.getAirplaneMode(this);

		// 获取可用内存（单位：M）
		String availableMemory = systemInfo.getAvailableMemory();

		// 获取电池电量信息(例如：60%)
		systemInfo.getBatteryLevel(new GlobalCallback() {

			@Override
			public void data_result(String arg0) {

			}
		});

		// 获取设备开机后运行时间
		String bootTime = systemInfo.getBootTime();

		// 获取设备CPU核心数
		int cpuCoresNum = systemInfo.getCpuCoresNum();

		// 获取CPU最大频率，单位MHZ
		String cpuMaxFrequenc = systemInfo.getCpuMaxFrequence();

		// 获取CPU最小频率，单位MHZ
		String cpuMinFrequenc = systemInfo.getCpuMinFrequence();

		// 获取CPU型号
		String cpuModel = systemInfo.getCpuModel();

		// 获取CPU当前运行时频率
		String currCpuFreq = systemInfo.getCurrCpuFreq();

		// 获取设备屏幕密度(例如：(0.75/1.0/1.5/2.0))
		float deviceDensity = systemInfo.getDeviceDensity();

		// 获取设备每英寸的dpi
		float deviceDensityDpi = systemInfo.getDeviceDensityDpi();

		// 获取设备屏幕像素高度
		int deviceHeight = systemInfo.getDeviceHeight();

		// 获取设备屏幕像素宽度
		int deviceWidth = systemInfo.getDeviceWidth();

		// 获取设备当前语言
		String deviceLanguage = systemInfo.getDeviceLanguage();

		// 获取设备名称
		String deviceName = systemInfo.getDeviceName();

		// 获取设备方向（0 是 portrait,1 是 landscape）
		int deviceOrientation = systemInfo.getDeviceOrientation();

		// 获取设备IMEI号
		String imei = systemInfo.getImei();

		// 获取设备Mac地址
		String macAddress = systemInfo.getMacAddress();

		// 获取网络类型(wifi或者Mobile)
		String NetWorkType = systemInfo.getNetWorkType();

		// 获取SD卡可用存储空间(单位：M)
		String sDCardAvailableStorage = systemInfo.getSDCardAvailableStorage();

		// 获取SD卡总体大小(单位：M)
		String sDCardTotalStorage = systemInfo.getSDCardTotalStorage();

		// 检测手机上的存在的所有传感器
		String sensor = systemInfo.getSensor();

		// 获取设备系统版本号
		String systemVersionCode = systemInfo.getSystemVersionCode();

		// 获取设备系统版本名
		String systemVersionName = systemInfo.getSystemVersionName();

		// 获取设备总体内存(单位：M)
		String totalMemory = systemInfo.getTotalMemory();

		// wifi打开的前提下，获取wifi的所有相关信息(如地址，id，网速等)
		WifiInfo wifiInfo = systemInfo.getWifiInfo(true);

		// 获取wifi的IP地址
		int wifiIpAddress = systemInfo.getWifiIpAddress();

		// 获取wifi的连接速度
		int wifiLinkSpeed = systemInfo.getWifiLinkSpeed();

		// 获取wifi的MAC地址
		String wifiMacAddress = systemInfo.getWifiMacAddress();

		// 获取wifi的Rssi（信号强度:0 到 -100）
		int wifiRssi = systemInfo.getWifiRssi();

		// 检测是否开启GPS，需要权限"android.permission.ACCESS_FINE_LOCATION"
		boolean isOpenGPS = systemInfo.isOpenGPS();

		// 检测设备是否有Root
		boolean isRoot = systemInfo.isRoot();

		// 打开设备Wifi
		systemInfo.openWifi();

		// 打开跳转到wifi设置界面
		systemInfo.openWifiSetting();

		// 取消或开启飞行模式
		systemInfo.setAirplaneModeOn(this, true);

		// 获取设备的CPU序列号
		String cpuSer = systemInfo.getCpuSer();

		// 获取某个进程pid的CPU使用情况
		int cupUsage = systemInfo.getCpuUsage(2);

	}

}
