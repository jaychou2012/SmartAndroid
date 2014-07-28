package com.tandong.sademo.zip;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.topbar.TopBar;
import com.tandong.sa.topbar.TopBar.Action;
import com.tandong.sa.zip.ZipUtil;
import com.tandong.sademo.R;

public class ZipActivity extends SmartActivity implements OnClickListener {

	private TopBar tb;
	private Button btn_checkfile, btn_uppackingFile, btn_unpack, btn_packing,
			btn_comparePackage, btn_compareEntry;
	private String sdUrl = Environment.getExternalStorageDirectory() + "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_zip);
		initTop();
		initView();

	}

	private void initView() {
		btn_checkfile = (Button) this.findViewById(R.id.btn_checkfile);
		btn_uppackingFile = (Button) this.findViewById(R.id.btn_uppackingFile);
		btn_unpack = (Button) this.findViewById(R.id.btn_unpack);
		btn_packing = (Button) this.findViewById(R.id.btn_packing);
		btn_comparePackage = (Button) this
				.findViewById(R.id.btn_comparePackage);
		btn_compareEntry = (Button) this.findViewById(R.id.btn_compareEntry);

		btn_checkfile.setOnClickListener(this);
		btn_uppackingFile.setOnClickListener(this);
		btn_unpack.setOnClickListener(this);
		btn_packing.setOnClickListener(this);
		btn_comparePackage.setOnClickListener(this);
		btn_compareEntry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_checkfile:

			// 检查某个文件是否在zip包里
			boolean exists = ZipUtil.containsEntry(new File(sdUrl
					+ "/SmartAndroid/demo.zip"), "foo.txt");

			if (exists) {
				showToast("存在");
			} else {
				showToast("不存在");
			}
			break;
		case R.id.btn_uppackingFile:

			// 解压zip里某个文件到SD卡指定位置
			ZipUtil.unpackEntry(new File(sdUrl + "/SmartAndroid/demo.zip"),
					"foo.txt", new File(sdUrl + "/SmartAndroid/bar.txt"));
			break;
		case R.id.btn_unpack:

			// 解压文件到SD卡指定位置
			ZipUtil.unpack(new File(sdUrl + "/SmartAndroid/demo.zip"),
					new File(sdUrl + "/SmartAndroid/"));
			break;

		case R.id.btn_packing:

			// 将指定目录文件打包为zip
			ZipUtil.pack(new File(sdUrl + "/SmartAndroid/"), new File(sdUrl
					+ "/SmartAndroid/new.zip"));
			break;

		case R.id.btn_comparePackage: {

			// 比较两个压缩包是否相同
			boolean equals = ZipUtil.archiveEquals(new File(sdUrl
					+ "/SmartAndroid/demo.zip"), new File(sdUrl
					+ "/SmartAndroid/demo2.zip"));
			if (equals) {
				showToast("相同的压缩包");
			} else {
				showToast("不相同的压缩包");
			}
		}
			break;
		case R.id.btn_compareEntry: {

			// 比较两个压缩包里某个文件是否相同
			boolean equals = ZipUtil.entryEquals(new File(sdUrl
					+ "/SmartAndroid/demo.zip"), new File(sdUrl
					+ "/SmartAndroid/demo2.zip"), "foo.txt");
			if (equals) {
				showToast("相同的压缩包内文件");
			} else {
				showToast("不相同的压缩包内文件");
			}
		}
			break;
		default:
			break;
		}
	}

	private void initTop() {
		tb = (TopBar) this.findViewById(R.id.topbar);
		tb.setTitle("解压缩框架");
		tb.setHomeAction(new Action() {

			@Override
			public void performAction(View arg0) {
				ZipActivity.this.finish();
			}

			@Override
			public int getDrawable() {

				return R.drawable.back;
			}
		});
	}
}
