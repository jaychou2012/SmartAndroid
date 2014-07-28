package com.tandong.sademo.eventbus;

import android.os.Bundle;
import android.view.View;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.eventbus.EventBus;

public class EventBusActivity extends SmartActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 类似于广播，在需要接收内容的地方注册EventBus（有多种注册方式，这里只展示其中几种�?
		// 注册：三个参数分别是，消息订阅�?（接收�?），接收方法名，事件�?
		EventBus.getDefault().register(this);
//		EventBus.getDefault().register(this, "setTextA", SetTextAEvent.class);
//		EventBus.getDefault().register(this, "setTextB", SetTextBEvent.class);
//		EventBus.getDefault().register(this,"messageFromSecondActivity",SecondActivityEvent.class);
//		EventBus.getDefault().registerSticky(this, "messageFromSecondActivity", SecondActivityEvent.class);
//		EventBus.getDefault().register(this, "countDown", CountDownEvent.class);
	}

	public void postEvent(View view) {
		// 分发（在你需要发送内容消息的地方使用调用即可�?
		EventBus.getDefault().post("SmartAndroid");
		// EventBus.getDefault().post(new MyEvent());

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		EventBus.getDefault().unregister(this);
	}

	// 以下展示其中4种接收处理事件内容的方法，方法名称固�?
	public void onEventMainThread(String json) {// 接收事件内容地方（参数类型可以自己根据实际情况定义）

	}

	public void onEvent(Object obj) {// 默认接收事件内容地方（参数类型可以自己根据实际情况定义）

	}

	public void onEventAsync(Object event) {// 接收事件内容地方（参数类型可以自己根据实际情况定义）

	}

	public void onEventBackgroundThread(Object event) {// 接收事件内容地方（参数类型可以自己根据实际情况定义）

	}
}
