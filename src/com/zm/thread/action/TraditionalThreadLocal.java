package com.zm.thread.action;

import java.util.Random;

/**
 * 线程范围内的共享变量
 * jdk 提供了ThreadLocal实现类
 * @author zm
 */
public class TraditionalThreadLocal {
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + "数据："
							+ data);
					x.set(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	static class A {
		public int get() {
			int data = x.get();
			System.out.println("B FROM： " + Thread.currentThread().getName()
					+ "数据：" + data);
			return data;
		}
	}

	static class B {
		public int get() {
			int data = x.get();
			System.out.println("A FROM： " + Thread.currentThread().getName()
					+ "数据：" + data);
			return data;
		}
	}

}
