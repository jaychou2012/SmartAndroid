package com.tandong.sademo.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;

import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;
import com.tandong.sademo.entity.MyEntity;

public class GsonActivity extends SmartActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		analyzeJson();

	}

	String gson_str = "{\"status\": 0,\"name\":\"SmartAndroid Gson \"}";

	String gson_list = "[{\"status\": 0,\"name\":\"SmartAndroid Gson \"},{\"status\": 2,\"name\":\"SmartAndroid Gson2 \"}]";

	private void analyzeJson() {

		// 1.把JSON单一对象转为Java的实体类对象
		Gson gson = new Gson();
		MyEntity myEntity = gson.fromJson(gson_str, MyEntity.class);// 两个参数（json串，要转为的实体类对象）
		Log.i("info", myEntity.getStatus() + "，" + myEntity.getName());

		// 2.将JSON数组转为实体列表
		List<MyEntity> me = gson.fromJson(gson_list, new TypeToken<List<MyEntity>>() {
		}.getType());
		for (int i = 0; i < me.size(); i++) {
			MyEntity p = me.get(i);

		}

		// 3.将一个对象实体转为Json格式String

		MyEntity entity = new MyEntity();
		entity.setName("SA");
		entity.setStatus(3);
		String jsonStr = gson.toJson(entity);

		// 4.将对象集合列表转为JsonArray类型的数组
		List<MyEntity> meBeans = new ArrayList<MyEntity>();
		MyEntity meBean = new MyEntity();
		meBean.setName("SA1");
		meBean.setStatus(4);

		meBeans.add(meBean);
		meBeans.add(meBean);// 加入2次，这样这个集合里就有2个实体了,方便测试

		Type type = new TypeToken<List<MyEntity>>() {
		}.getType(); // 指定集合对象属性
		String beanListToJson = gson.toJson(meBeans, type);
		
		//同理，Hashmap等类型也是可以转为JSON格式的，自己可以测试下
	}
}
