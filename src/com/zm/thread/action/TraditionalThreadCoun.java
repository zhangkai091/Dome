package com.zm.thread.action;

/**
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，接着回到主线程循环100次，如此循环50次 
 * 用到同步锁 wait 和notify
 * 实现线程间的通信
 * 
 * @author zm
 *
 */
public class TraditionalThreadCoun {
	public static void main(String[] args) {
		new TraditionalThreadCoun().init();
	}

	public void init() {
		final Business b = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 50; i++) {
					b.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			b.main(i);
		}
	}

	class Business {
		private boolean isSub = true;

		public synchronized void sub(int i) {
			while (!isSub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j <= 10; j++) {
				System.out.println("sub: " + j + " main:" + i);
			}
			isSub = false;
			this.notify();
		}

		public synchronized void main(int i) {
			while (isSub) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j <= 100; j++) {
				System.out.println("sub: " + j + " main:" + i);
			}
			isSub = true;
			this.notify();
		}
	}

}
