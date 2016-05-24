package com.zm.thread.action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建
 * 
 * @author zm
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		// new一个线程池
		ExecutorService ex = Executors.newFixedThreadPool(3);
		for (int i = 0; i <= 10; i++) {
			final int sta = i;
			ex.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i <= 10; i++) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+ "  loop " + i  +" sta " + sta);
					}
				}
			});
		}

	}

}
