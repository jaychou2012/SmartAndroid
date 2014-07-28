package com.tandong.sademo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.view.PLScollListView;
import com.tandong.sademo.R;

public class PLListViewActivity extends SmartActivity {

	private PLScollListView mListView;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pl);

		mListView = (PLScollListView) findViewById(R.id.layout_listview);
		View header = LayoutInflater.from(this).inflate(R.layout.listview_header, null);
		mImageView = (ImageView) header.findViewById(R.id.layout_header_image);

		mListView.setParallaxImageView(mImageView);
		mListView.addHeaderView(header);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
				new String[] { "First Item", "Second Item", "Third Item", "Fifth Item", "Sixth Item", "Seventh Item",
						"Eighth Item", "Ninth Item", "Tenth Item", "....." });
		mListView.setAdapter(adapter);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			mListView.setViewsBounds(PLScollListView.ZOOM_X2);
		}
	}
}
