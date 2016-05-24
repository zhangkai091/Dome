package com.zm.json.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zm.json.model.JsonBean;

/**
 * 运用google 的gson jar包来解析json字符串
 * 
 * @author zm
 */
public class GsonObject {

	/**
	 * 将一个json串传转换成bean的List集合 jsonString数据格式
	 * "[{\"sex\":\"na\",\"age\":\"30\",\"name\":\"wjl\"},{\"sex\":\"na11\",\"age\":\"310\",\"name\":\"wjl11\"}]"
	 * ;
	 * 
	 * @param jsonString
	 */
	public static void ParseGson(String jsonString) {
		// new一个Gson对象
		Gson gson = new Gson();
		// 将一个json串传转换成bean的List集合
		List<JsonBean> jsonBeanList = gson.fromJson(jsonString,
				new TypeToken<List<JsonBean>>() {
				}.getType());
		for (JsonBean jsonBean : jsonBeanList) {
			System.out.println("将一个json串传转换成bean的List集合");
			System.out.println("gson" + jsonBean.getName());
			System.out.println("gson" + jsonBean.getAge());
			System.out.println("gson" + jsonBean.getSex());

			// 将一个bean 转换成json串
			String jsonstr = gson.toJson(jsonBean);
			System.out.println("将一个bean 转换成json串");
			System.out.println(jsonstr.toString());
		}

		// 将bean的List集合转换成json字符串
		String jsonstr = gson.toJson(jsonBeanList);
		System.out.println("将bean的List集合转换成json字符串");
		System.out.println(jsonstr.toString());

		// 将一个json字符串转换成为Bean的Array数组对象
		JsonBean[] jsonBeanArray = gson.fromJson(jsonString,
				new TypeToken<JsonBean[]>() {
				}.getType());
		System.out.println("JsonBean数组对象");
		for (int i = 0; i < jsonBeanArray.length; i++) {
			System.out.println("数组对象" + i);
			System.out.println(jsonBeanArray[i].getName());
			System.out.println(jsonBeanArray[i].getAge());
			System.out.println(jsonBeanArray[i].getSex());
		}

		// 将一个Bean的Array数组转换成为json字符串
		String jsonstr1 = gson.toJson(jsonBeanArray);
		System.out.println("将一个Bean的Array数组转换成为json字符串");
		System.out.println(jsonstr1.toString());

		// 将一个json数组封装到Map中
		Map<String, JsonBean> map = new HashMap<String, JsonBean>();
		for (int i = 0; i < jsonBeanArray.length; i++) {
			map.put("" + (i + 10), jsonBeanArray[i]);
		}
		// 将一个Bean的Map转换成为json字符串
		// 数据格式 //String
		// Str="{'1':{sex:'na',age:'30',name:'wjl'},'2':{'sex':'na11','age':'31',name:'wjl11'}}";
		String Str = gson.toJson(map);
		System.out.println("将一个Bean的Map转换成为json字符串");
		System.out.println(Str.toString());
		// 将一个json字符串转换成为Map的数组对象>
		Map<String, JsonBean> map2 = gson.fromJson(Str,
				new TypeToken<Map<String, JsonBean>>() {
				}.getType());
		System.out.println("将一个json字符串转换成为Map的数组对象");
		System.out.println(map2.toString());

		// new 一个实体对象
		// JsonBean jsonBean = new JsonBean();
		// String gsonStr ="{\"sex\":\"na\",\"age\":\"30\",\"name\":\"wjl\"}";
		// jsonBean = gson.fromJson(jsonString, JsonBean.class);
		// System.out.println("name="+jsonBean.getName());
	}

	public static void main(String[] args) {
		// "sex\":\"na11\",\"age\":\"310\",\"name\":\"wjl11\"}备用备用
		// gson将一个json字符串转换成为Bean的List集合
		String gsonStr = "[{'sex':'na','age':'30','name':'wjl'},{'sex':'na11','age':'31','name':'wjl11'}]";
		ParseGson(gsonStr);
	}

}
