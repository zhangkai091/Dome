package com.zm.thread.action;

/**
 * 多线程三种实现方式
 * @author zm
 */
public class TraditionalThread {
	
	public static void main(String[] args) {
		
		//第一种线程方法 加{}代表new的是对应的子类
		Thread t = new Thread(){
			@Override //重写父类的方法
			public void run(){
				while(true){
					try {
						Thread.sleep(500); //设置线程执行时间  （毫秒）
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程名称:"+Thread.currentThread().getName());
					System.out.println("2:"+this.getName());
				}
			}
		};
		t.start(); //默认执行run方法
		
		//第二种线程方法  更加体现面向对象方式
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500); //设置线程执行时间  （毫秒）
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程名称:"+Thread.currentThread().getName());
				}
			}
		});
		t2.start();
		
		//第三种线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500); //设置线程执行时间  （毫秒）
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Runnable:"+Thread.currentThread().getName());
				}
			}
		}){
			public void run() {
				while(true){
					try {
						Thread.sleep(500); //设置线程执行时间  （毫秒）
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程名称:"+Thread.currentThread().getName());
				}
			}
			
		}.start();
	}

}
