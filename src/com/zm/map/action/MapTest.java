package com.zm.map.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map解析
 * @author zm
 */
public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "张三");
		map.put("2", "李四");
		map.put("3", "王五");
		map.put("4", "赵六");
		
		
		/*
		 * entrySet 遍历Map集合
		 * 下边两种定义Iterator 的方式是一样的
		 */
		Iterator it = map.entrySet().iterator();
		//Iterator<Entry<String, String>> it2 = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry p = (Entry) it.next();
			System.out.println("entrySet Map集合循环1："+p.getKey()+":"+p.getValue());
			System.out.println("===============================================");
		}
		
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value =entry.getValue();
			System.out.println("entrySet Map集合循环2：" + key + ":" + value);
			System.out.println("===============================================");
		}
		
		
		//keySet 遍历Map集合
		Iterator<String> iter = map.keySet().iterator();  
		while (iter.hasNext()) {  
		   String key = iter.next();  
		   String value = map.get(key);  
		   System.out.println("keySet Map集合循环1："+key+":"+value);
		   System.out.println("===============================================");
		}
		
		for (String key : map.keySet()) {
			 String value = map.get(key);  
			 System.out.println("keySet Map集合循环2："+key+":"+value);
			 System.out.println("===============================================");
		}
	}

}
