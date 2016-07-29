package com.zm.Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/**
 * Java反射机制
 * @author zm
 */

interface China {
    public static final String name="Rollen";
    public static  int age=20;
    public void sayChina();
    public void sayHello(String name, int age);
}

class Demo implements China{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Demo [name=" + name + "]";
	}

	public Demo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demo(String name) {
		super();
		this.name = name;
	}

	@Override
	public void sayChina() {
		System.out.println("这里是sayChina");
	}

	@Override
	public void sayHello(String name, int age) {
		System.out.println("这里是sayHello");
	}
	
}


public class DemoClass {

	public static void main(String[] args) throws Exception {
		 Demo demo=new Demo();
	     System.out.println("获取当前对象的包路径："+demo.getClass().getName());
		
		 Class<?> demo1= Class.forName("com.zm.Class.Demo");
		 System.out.println(demo1.getName());
		 //通过反射 反射为类对象
		 Demo d =(Demo) demo1.newInstance();
		 d.setName("111111111");
		 System.out.println("通过反射给类对象赋值："+d);
		 
		 Class<?> temp = demo1.getSuperclass();
		 System.out.println("继承的父类："+temp.getName());
		 
		 Constructor<?> cons[]=demo1.getConstructors();
         for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：  "+cons[i]);
         }
         System.out.println("===============本类属性========================");
         // 取得本类的全部属性
         Field[] field = demo1.getDeclaredFields();
         for (int i = 0; i < field.length; i++) {
             // 权限修饰符
             int mo = field[i].getModifiers();
             String priv = Modifier.toString(mo);
             // 属性类型
             Class<?> type = field[i].getType();
             System.out.println(priv + " " + type.getName() + " "
                     + field[i].getName() + ";");
         }
         
         
         System.out.println("===============实现的接口或者父类的属性========================");
         // 取得实现的接口或者父类的属性
         Field[] filed1 = demo1.getFields();
         for (int j = 0; j < filed1.length; j++) {
             // 权限修饰符
             int mo = filed1[j].getModifiers();
             String priv = Modifier.toString(mo);
             // 属性类型
             Class<?> type = filed1[j].getType();
             System.out.println(priv + " " + type.getName() + " "
                     + filed1[j].getName() + ";");
         }
         System.out.println("===============调用Dome类中的方法========================");
        //调用Dome类中的sayChina方法
         Method method=demo1.getMethod("sayChina");
         method.invoke(demo1.newInstance());
         //调用Dome的sayHello方法
         method=demo1.getMethod("sayHello", String.class,int.class);
         method.invoke(demo1.newInstance(),"Rollen",20);
	}

}
