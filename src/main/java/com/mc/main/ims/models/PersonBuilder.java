package com.mc.main.ims.models;

import java.util.Scanner;

import com.mc.main.ims.util.Console;

public class PersonBuilder {
	
	private Person object;
	
	public PersonBuilder() {
		super();
		object = new Person();
	}
	
	public PersonBuilder forename(String name) {
		object.setForename(name);
		return this;
	}
	
	public PersonBuilder surname(String name) {
		object.setSurname(name);
		return this;
	}
	
	public PersonBuilder age(Integer age) {
		object.setAge(age);
		return this;
	}

	public Person construct(Scanner scan) {
		
		System.out.println("Please enter first name:");
		this.forename(Console.input());

		System.out.println("Please enter last name:");
		this.surname(Console.input());

		System.out.println("Please enter age of person:");
		this.age(Console.inputInt());

		return object;
	}

	public Person build() {
		return object;
	};
}
