package com.tandong.sademo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tandong.sa.view.AutoViewHelper;
import com.tandong.sa.view.AutoViewHelper.AutoViewPosition;
import com.tandong.sademo.R;

public class ScrollViewActivity extends Activity {

	private AutoViewHelper mAutoViewHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrollview);

		mAutoViewHelper = new AutoViewHelper(this, AutoViewPosition.TOP);
		View autoView = mAutoViewHelper.createAutoViewOnScrollView(R.id.scrollView, R.layout.smartingview);

		autoView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ScrollViewActivity.this, "Click me!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
