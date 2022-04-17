package com.mc.main.ims.models;

import com.mc.main.ims.models.builders.PersonBuilder;

public class Person {
	
	private Integer id;
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
		this.id = ID;
		this.forename = forename;
		this.surname = surname;
		this.age = age;
	}

	public Integer getID() {
		return id;
	}

	public void setID(Integer iD) {
		id = iD;
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
		return "ID["+id+"] [forename=" + forename + ", surname=" + surname + ", age=" + age + "]";
	}
	
}
