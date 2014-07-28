package com.tandong.sademo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tandong.sademo.R;
import com.tandong.sademo.entity.New;

public class NewsAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<New> news;

	public NewsAdapter(ArrayList<New> news, Context context) {
		this.context = context;
		this.news = news;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return news.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return news.get(arg0);
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
			convertView = View.inflate(context, R.layout.item_news, null);
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		New n = news.get(position);
		holder.tv_title.setText(n.getName() + "     " + n.getTime());

		return convertView;
	}

	class ViewHolder {
		TextView tv_title;
	}
}
