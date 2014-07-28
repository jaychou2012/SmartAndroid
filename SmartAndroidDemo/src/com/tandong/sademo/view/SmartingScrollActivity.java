package com.tandong.sademo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sademo.R;

public class SmartingScrollActivity extends SmartActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smartingscroll);

		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SmartingScrollActivity.this, ListViewActivity.class));
			}
		});
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SmartingScrollActivity.this, ScrollViewActivity.class));
			}
		});
	}

}
