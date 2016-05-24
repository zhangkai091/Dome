package com.zm.timer.action;

import java.util.Date;

/**
 * 创建一个thread，然后让它在while循环里一直运行着， 
 * 通过sleep方法来达到定时任务的效果
 * @author zm
 */
public class ThreadTask {
	public static void main(String[] args) {
		//毫秒
		final long timeInterval = 10000;
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// -------任务运行代码
					System.out.println("Hello !!");
					System.out.println(new Date());
					// ------- 在这里结束
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
