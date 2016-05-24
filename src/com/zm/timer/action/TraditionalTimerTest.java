package com.zm.timer.action;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * timer 定时器实现
 * 
 * @author zm
 *
 */
public class TraditionalTimerTest {
	
	private static int count=0;

	public static void main(String[] args) {
		System.out.println(new Date());
	/*	// 相同的时间间隔运行
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("1" + new Date());
				System.out.println("测试1");
			}
		}, 1000, 3000); // 单位 毫秒
*/		/*
		 * while(true){ System.out.println(new Date().getSeconds()); try {
		 * Thread.sleep(1000); //让定时间 按设置的时间允许 } catch (InterruptedException e)
		 * { e.printStackTrace(); } }
		 */
		
		// 不同的时间间隔运行
		//如果想设每天   凌晨   执行代码 可以用    quartz 设置时间配置时间配置
		class MyTimerTask extends TimerTask {
			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("count:"+count);
				System.out.println("2" + new Date());
				System.out.println("测试2");
				new Timer().schedule(new MyTimerTask(), 2000+2000*count);
			}
		}
		new Timer().schedule(new MyTimerTask(), 2000); // 单位 毫秒
	}

}
