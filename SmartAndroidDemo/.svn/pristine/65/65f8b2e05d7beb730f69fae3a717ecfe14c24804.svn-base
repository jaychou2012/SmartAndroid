package com.tandong.sademo.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.topbar.TopBar;
import com.tandong.sa.topbar.TopBar.Action;
import com.tandong.sa.view.AutoReFreshListView;
import com.tandong.sa.view.AutoReFreshListView.OnLoadMoreListener;
import com.tandong.sa.view.AutoReFreshListView.OnRefreshListener;
import com.tandong.sademo.R;
import com.tandong.sademo.adapter.TestAdapter;

public class AutoRefreshListViewActivity extends SmartActivity {
	private AutoReFreshListView mListView;
	private ArrayList<String> lists;
	private static final int LOAD_DATA_FINISH = 10;// 下拉加载更多
	private static final int REFRESH_DATA_FINISH = 11;// 上拉刷新
	private int mCount = 10;
	private TestAdapter mAdapter;

	private TopBar tb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autorefreshlistview);
		initView();
		initTop();
		addData();
		initListView();
	}

	private void initTop() {
		tb = (TopBar) this.findViewById(R.id.topbar);
		tb.setTitle("AutoRefreshListView");

		tb.setHomeAction(new Action() {

			@Override
			public void performAction(View view) {
				AutoRefreshListViewActivity.this.finish();
			}

			@Override
			public int getDrawable() {

				return R.drawable.back;
			}
		});

	}

	private void initListView() {
		mAdapter = new TestAdapter(this, lists);

		mListView.setAdapter(mAdapter);

		mListView.setOnRefreshListener(new OnRefreshListener() {// 上拉刷新

					@Override
					public void onRefresh() {

						Log.e("info", "onRefresh");
						loadData(0);
					}
				});

		mListView.setOnLoadListener(new OnLoadMoreListener() {// 下拉加载更多

					@Override
					public void onLoadMore() {

						Log.e("info", "onLoad");
						loadData(1);
					}
				});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Log.e("info", "click position:" + position);

			}
		});

	}

	private void addData() {
		lists = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			String s = "名字" + i;
			lists.add(s);
		}
	}

	private void initView() {
		mListView = (AutoReFreshListView) this.findViewById(R.id.mListView);

	}

	@SuppressWarnings("unchecked")
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_DATA_FINISH:// 上拉刷新
				if (mAdapter != null) {
					mAdapter.lists = (ArrayList<String>) msg.obj;
					mAdapter.notifyDataSetChanged();
				}
				mListView.onRefreshComplete();
				break;
			case LOAD_DATA_FINISH:// 下拉加载更多
				if (mAdapter != null) {
					mAdapter.lists.addAll((ArrayList<String>) msg.obj);
					mAdapter.notifyDataSetChanged();
				}
				mListView.onLoadMoreComplete();
				break;
			default:
				break;
			}
		};
	};

	public void loadData(final int type) {
		new Thread() {
			@Override
			public void run() {
				List<String> _List = null;
				switch (type) {
				case 0:// 上拉刷新
					mCount = 10;

					_List = new ArrayList<String>();
					for (int i = 0; i <= mCount; i++) {
						String s = "名字" + i;
						_List.add(s);
					}
					break;

				case 1:// 下拉加载更多
					_List = new ArrayList<String>();
					int _Index = mCount + 10;

					for (int i = mCount + 1; i <= _Index; i++) {
						String s = "名字" + i;

						_List.add(s);
					}
					mCount = _Index;
					break;
				}

				try {
					Thread.sleep(2000);// 延迟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (type == 0) { // 下拉刷新
					// Collections.reverse(mList); //逆序
					Message _Msg = mHandler.obtainMessage(REFRESH_DATA_FINISH, _List);
					mHandler.sendMessage(_Msg);
				} else if (type == 1) {// 上拉加载更多
					Message _Msg = mHandler.obtainMessage(LOAD_DATA_FINISH, _List);
					mHandler.sendMessage(_Msg);
				}
			}
		}.start();
	}
}
