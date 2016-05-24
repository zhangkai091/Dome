package com.zm.json.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 应用JSONArray 实现JSON 的转换
 * 用到的jar包有：
 * commons-beanutils-1.7.0.jar;
 * commons-collections-3.2.jar;
 * commons-lang-2.4.jar;
 * commons-logging-1.1.jar;
 * ezmorph-1.0.4.jar;
 * json-lib-2.2.2-jdk15.jar
 * 
 * @author zm
 */
public class JsonArrayTest {

	Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) {

		String json = "[{'PointCode':'GPS_ZFC_20151209','Value':'29.931277777777776,121.65336944444445,48.08,338.0','TimeStamp':'2016/3/28 18:59:50'},{'PointCode':'GPS_ZFC_20151210','Value':'29.931277777777776,121.65336944444445,48.08,338.0','TimeStamp':'2016/3/28 18:59:50'}]";
		new JsonArrayTest().ParseList(json);
	}

	List<Map> list = new ArrayList<Map>();

	/**
	 * 格式化json串
	 * 
	 * @param jsonStr
	 */
	public void ParseJson(String jsonStr) {
		Map<String, String> map1 = new HashMap<String, String>();
		JSONArray jArray = JSONArray.fromObject(jsonStr);
		for (int i = 0; i < jArray.size(); i++) {
			JSONObject json = (JSONObject) jArray.get(i);
			String point = (String) json.get("PointCode");
			String value = (String) json.get("Value");
			String data = (String) json.get("TimeStamp");
			System.out.println("格式化Json串 " + point + " : " + value + " : "
					+ data);
			// 把Json 封装到Map里
			map1.put("PointCode", point);
			map1.put("Value", value);
			map1.put("TimeStamp", data);
			list.add(map1);
		}
		System.out.println("把Json 封装到Map里: " + list.toString());
	}

	/**
	 * Json串转List
	 * 
	 * @param list
	 * @return
	 */
	public List ParseList(String json) {
		JSONArray jsonarray = JSONArray.fromObject(json);
		System.out.println("Json串转List------------");
		List list = (List) JSONArray.toList(jsonarray);
		System.out.println(list);
		System.out.println("List转JSON串");
		JSONArray jsonarray2 = JSONArray.fromObject(list);
		System.out.println(jsonarray2);
		return list;
	}

}
