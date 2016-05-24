package com.zm.timer.action;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *当启动和去取消任务时可以控制
 *第一次执行任务时可以指定你想要的delay时间
 *在实现时，Timer类可以调度任务，TimerTask则是通过在run()方法里实现具体任务。
 *Timer实例可以调度多任务，它是线程安全的。
 *当Timer的构造器被调用时，它创建了一个线程，这个线程可以用来调度任务
 * @author zm
 */
public class TimerTaskRun {
	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				//在这里运行的任务
				System.out.println("Hello !!!");
				System.out.println(new Date());
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 1 * 1000;
		//安排在一个时间间隔内运行的任务
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	} 
}
