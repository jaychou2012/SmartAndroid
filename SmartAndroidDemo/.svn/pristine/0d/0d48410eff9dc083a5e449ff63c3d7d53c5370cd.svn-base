package com.tandong.sademo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tandong.sademo.R;

public class MenuAdapter extends BaseAdapter {

	private Context c;
	public static ArrayList<String> ss;

	public MenuAdapter(Context context, ArrayList<String> str) {
		this.c = context;
		this.ss = str;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ss.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ss.get(arg0);
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
			convertView = View.inflate(c, R.layout.item_menu, null);
			holder = new ViewHolder();
			holder.tv_menu = (TextView) convertView.findViewById(R.id.tv_menu);
			holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String s = ss.get(position);
		holder.tv_menu.setText(s);
		if (s.contains("首页")) {

			Drawable drawableHome = c.getResources().getDrawable(R.drawable.shouye);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("SmartTag")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.biaoqian);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());

			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("zUImageLoader")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.tupianmoshi);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("ZoomImageView")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.chuizhisuofang);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("Cropper")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.caijian);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("动画特效")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.xiaoguobianhua);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		} else if (s.contains("Actionbarsherlock")) {
			Drawable drawableHome = c.getResources().getDrawable(R.drawable.dingbu);
			drawableHome.setBounds(0, 0, drawableHome.getMinimumWidth(), drawableHome.getMinimumHeight());
			holder.tv_menu.setCompoundDrawables(drawableHome, null, null, null);
		}

		if (s.contains("SmartTag")) {
			holder.tv_number.setVisibility(View.VISIBLE);
		} else {
			holder.tv_number.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	class ViewHolder {
		TextView tv_menu;
		TextView tv_number;
	}
}
