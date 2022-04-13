package com.mc.main.ims.models;

public class Person {
	
	private Integer ID;
	private String forename;
	private String surname;
	private Integer age;
	
	public Person() {
		super();
		forename = "MISSING";
		surname = "MISSING";
		age = 0;
	}

	public Person(Integer ID, String forename, String surname, Integer age) {
		super();
		this.ID = ID;
		this.forename = forename;
		this.surname = surname;
		this.age = age;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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

	public PersonBuilder builder() {
		return new PersonBuilder();
	}

	@Override
	public String toString() {
		return "Person [forename=" + forename + ", surname=" + surname + ", age=" + age + "]";
	}
	
}
