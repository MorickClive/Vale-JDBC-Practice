package com.mc.main.ims.model;

public class Person {
	
	private String forename;
	private String surname;
	private Integer age;
	
	public Person() {
		super();
		forename = "MISSING";
		surname = "MISSING";
		age = 0;
	}

	public Person(String forename, String surname, Integer age) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.age = age;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
