package com.zm.json.model;

public class JsonBean {
	
	private String name;
	private String sex;
	private String age;
	
	public JsonBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public JsonBean(String name, String sex, String age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
