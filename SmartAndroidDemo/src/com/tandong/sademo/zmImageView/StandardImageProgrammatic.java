package com.tandong.sademo.zmImageView;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

import com.tandong.sa.zmImageview.ZoomImageView;
import com.tandong.sademo.R;

public class StandardImageProgrammatic extends ExampleActivity {

	protected ZoomImageView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		view = new ZoomImageView(this);
		view.setImageResource(R.drawable.image);
		view.setLayoutParams(params);

		ViewGroup layout = (ViewGroup) findViewById(R.id.layout);

		layout.addView(view);
	}
}