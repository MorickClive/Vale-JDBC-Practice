package com.mc.main.ims.models.builders;

import com.mc.main.ims.models.Person;
import com.mc.main.ims.util.Console;

public class PersonBuilder implements POJOBuilder<Person> {
	
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

	public Person construct() {
		
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
