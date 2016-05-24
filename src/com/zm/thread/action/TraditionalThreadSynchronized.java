package com.zm.thread.action;

/**
 * 传统的线程同步技术 Synchronized
 * 
 * @author zm
 */
public class TraditionalThreadSynchronized {

	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}

	private void init() {
		final Outputer out = new Outputer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.output("lishidsadsssssss");
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//out.output2("lishidsadafffffff");
					out.output3("lishidsadafffffff");
				}
			}
		}).start();
	}

	static  class Outputer { // static 相当于外部类
		// String xxx=""; //必须是同一个对象
		public void output(String name) {
			int len = name.length();
			/*
			 *  如果普通方法 和本方法同步互斥  xxx 或this  就可以实现
			 *  如果想让静态方法和本方法同步互斥 同步  需要用字节码对象Outputer.class
			 */
			synchronized (Outputer.class) { 
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}

		public synchronized void output2(String name) {  //和上一个方法 可以互斥  都是代表对象本身
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static  synchronized void output3(String name) { 
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}

	}
}
