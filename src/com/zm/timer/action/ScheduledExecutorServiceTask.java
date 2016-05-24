package com.zm.timer.action;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 *  ScheduledExecutorService
 *  相比于Timer的单线程，它是通过线程池的方式来执行任务的
 *  可以很灵活的去设定第一次执行任务delay时间
 *  提供了良好的约定，以便设定执行的时间间隔
 * @author zm
 */
public class ScheduledExecutorServiceTask {
	public static void main(String[] args) {
	    Runnable runnable = new Runnable() {
	      public void run() {
	        System.out.println("Hello !!");
	        System.out.println(new Date());
	      }
	    };
	    ScheduledExecutorService service = Executors
	                    .newSingleThreadScheduledExecutor();
	    
	    						  //启动对象    开始时间      间隔时间秒
	    service.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.SECONDS);
	  }

}
