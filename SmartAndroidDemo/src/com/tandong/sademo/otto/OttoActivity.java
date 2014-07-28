package com.tandong.sademo.otto;

import android.os.Bundle;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.otto.Bus;
import com.tandong.sa.otto.Subscribe;
import com.tandong.sa.otto.ThreadEnforcer;

public class OttoActivity extends SmartActivity {

	// 主要使用com.squareup.otto.Bus类、@Produce、 @Subscribe 注解。
	private Bus bus1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Otto的事件调用默认是在主线程（应用的UI线程）中调用，下面两种声明方式是一样的效果.
		bus1 = new Bus();
		Bus bus2 = new Bus(ThreadEnforcer.MAIN);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// bus1.register(this);
		BusProvider.getInstance().register(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// bus1.unregister(this);
		BusProvider.getInstance().unregister(this);
	}

	public void postEvent() {
		// 发送事件内容
//		bus1.post(new AnswerAvailableEvent(42));
	}

	// @Subscribe 注解告诉Bus该函数订阅了一个事件，该事件的类型为该函数的参数类型；

	// @Produce注解告诉Bus该函数是一个事件产生者，产生的事件类型为该函数的返回值。
	
	
//	@Subscribe
//	public void answerAvailable(AnswerAvailableEvent event) {
//		// TODO: React to the event somehow!
//	}
//
//	@Produce
//	public AnswerAvailableEvent produceAnswer() {
//		// Assuming 'lastAnswer' exists.
//		return new AnswerAvailableEvent(this.lastAnswer);
//	}
}
