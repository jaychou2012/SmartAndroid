package com.tandong.sademo.aq;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.aq.AQuery;
import com.tandong.sademo.R;

public class AqueryActivity extends SmartActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_aquery);

		// 下面演示Aquery绑定控件和加入监听事件
		AQuery aq = new AQuery(this);

		aq.id(R.id.iv).image(R.drawable.ic_launcher);
		aq.id(R.id.tv).text("Aquery绑定控件");
		aq.id(R.id.btn).clicked(this);
		
		//AQeury也支持Fragment
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			Toast.makeText(AqueryActivity.this, "AQuery点击了按钮", 1000).show();
			break;
		default:
			break;
		}

	}

}
