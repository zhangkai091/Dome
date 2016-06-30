package com.zm.map.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
		}
		System.out.println("===============================================");
		
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value =entry.getValue();
			System.out.println("entrySet Map集合循环2：" + key + ":" + value);
		}
		System.out.println("===============================================");
		
		
		//keySet 遍历Map集合
		Iterator<String> iter = map.keySet().iterator();  
		while (iter.hasNext()) {  
		   String key = iter.next();  
		   String value = map.get(key);  
		   System.out.println("keySet Map集合循环1："+key+":"+value);
		}
		System.out.println("===============================================");
		
		for (String key : map.keySet()) {
			 String value = map.get(key);  
			 System.out.println("keySet Map集合循环2："+key+":"+value);
		}
		System.out.println("===============================================");
		
		//Map排序
		List<Map.Entry<String, String>> itSort = new ArrayList<Map.Entry<String,String>>(map.entrySet());
		//排序
		Collections.sort(itSort, new Comparator<Map.Entry<String, String>>() {   
		    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {   
		    	//以key排序
		        return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		}); 
		for (int i = 0; i < itSort.size(); i++) {
			Map.Entry<String, String> m = (Entry<String, String>) itSort.get(i);
			System.out.println(m.getKey()+":"+m.getValue());
		}
	}

}
