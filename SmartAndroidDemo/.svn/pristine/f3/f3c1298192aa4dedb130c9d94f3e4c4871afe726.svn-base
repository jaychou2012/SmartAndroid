package com.tandong.sademo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tandong.sademo.R;

public class TestAdapter extends BaseAdapter {
	private Context c;
	public ArrayList<String> lists;

	public TestAdapter(Context context, ArrayList<String> lhqzs) {
		this.c = context;
		this.lists = lhqzs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(c, R.layout.item_autorefresh, null);
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String s = lists.get(position);
		holder.tv_title.setText(s);
		return convertView;
	}

	class ViewHolder {
		TextView tv_title;
	}
}
