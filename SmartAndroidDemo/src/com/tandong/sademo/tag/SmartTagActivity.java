package com.tandong.sademo.tag;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tag.SmartTag;
import com.tandong.sa.tag.nodes.Document;
import com.tandong.sa.tag.nodes.Element;
import com.tandong.sa.tag.select.Elements;
import com.tandong.sa.topbar.ActionBar;
import com.tandong.sa.topbar.ActionBar.Action;
import com.tandong.sademo.R;
import com.tandong.sademo.adapter.NewsAdapter;
import com.tandong.sademo.entity.New;
import com.tandong.sademo.util.CustomProgressDialog;
import com.tandong.sademo.util.SortTime;

public class SmartTagActivity extends SmartActivity {
	private ActionBar ab;
	private ListView lv_list;
	private ArrayList<New> news;
	private NewsAdapter adapter;
	private Thread th;
	public CustomProgressDialog progressDialog = null;
	public String webUrl = "http://nba2k.qq.com/webplat/info/news_version3/476/1874/1875/m1747/list_1.shtml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smarttag);
		initTop();
		initView();
		getData();
	}

	private void initView() {
		lv_list = (ListView) this.findViewById(R.id.lv_list);
	}

	private void initTop() {
		ab = (ActionBar) findViewById(R.id.actionbar);
		ab.setTitle("SmartTag采集解析网站");
		ab.setHomeAction(new Action() {

			@Override
			public void performAction(View arg0) {
				SmartTagActivity.this.finish();
			}

			@Override
			public int getDrawable() {

				return R.drawable.back;
			}
		});

	}

	private void getData() {
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				collectWeb();
			}
		});
		th.start();

	}

	private void collectWeb() {
		String myString = null;

		news = new ArrayList<New>();
		Message msg6 = new Message();
		msg6.what = 10;
		msg6.obj = news;
		handler.sendMessage(msg6);

		try {
			Document doc = SmartTag.connect(webUrl).get();
			Elements topnews = doc.getElementsByClass("new_list");
			Elements elinks = topnews.select("li");

			for (Element elink : elinks) {
				myString += elink.getElementsByTag("span").text()
						+ ":"
						+ elink.text()
						+ "："
						+ elink.getAllElements().get(0).tagName("a")
								.getAllElements().get(3).attr("abs:href");
				myString += "\n";
				if (elink.text().substring(10).startsWith("[视频")
						|| (elink.text().substring(10).charAt(4) + "")
								.endsWith("[")) {

				} else {
					New ne = new New();
					ne.setName(elink.text().substring(10));
					ne.setUrl(elink.getAllElements().get(0).tagName("a")
							.getAllElements().get(3).attr("abs:href"));
					ne.setTime(elink.getElementsByTag("span").text());
					news.add(ne);
				}
			}
		} catch (Exception e) {
			myString = e.getMessage();
			e.printStackTrace();
		}

		if (myString == null) {
			Message msg2 = new Message();
			msg2.what = 0;
			msg2.obj = news;
			handler.sendMessage(msg2);
			return;
		}

		Message msg = new Message();
		msg.what = 1;
		msg.obj = news;
		handler.sendMessage(msg);
	}

	Handler handler = new Handler(new Callback() {

		@Override
		public boolean handleMessage(Message arg0) {
			switch (arg0.what) {
			case 0:
				if (progressDialog != null) {
					progressDialog.dismiss();
					progressDialog = null;
				}
				Toast.makeText(SmartTagActivity.this, "请求超时，请重试", 1000).show();
				break;
			case 1:
				if (progressDialog != null) {
					progressDialog.dismiss();
					progressDialog = null;
				}
				Collections.sort(news, new SortTime());
				adapter = new NewsAdapter(news, SmartTagActivity.this);
				lv_list.setAdapter(adapter);

				break;
			case 10:
				if (progressDialog == null) {
					progressDialog = CustomProgressDialog
							.createDialog(SmartTagActivity.this);
					progressDialog.setMessage("正在加载中...");
				}

				progressDialog.show();
				break;
			default:
				break;
			}

			return false;
		}

	});

}
