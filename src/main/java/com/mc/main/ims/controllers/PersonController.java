package com.mc.main.ims.controllers;

import java.util.HashMap;

import com.mc.main.ims.dbas.PersonDBA;
import com.mc.main.ims.models.Person;
import com.mc.main.ims.models.PersonBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class PersonController implements CRUDController<Person> {

	private int id = 0;
	private Person person;
	private HashMap<Integer, Person> storedPeople;
	private StringBuilder sb;

	private PersonDBA dba;

	public PersonController() {
		super();
		storedPeople = new HashMap<>();
		sb = new StringBuilder();
		dba = new PersonDBA();
	}

	@Override
	public void create() {
		person = new PersonBuilder().construct(Console.getScanner());
		dba.create(person);
	}

	@Override
	public void read() {
		System.out.println("Please enter id:");
		int id = Console.inputInt();
		Person targ = dba.read(id);

		if (targ != null) {
			sb.append(String.format("%s", targ));
			ReportFormatter.reportDivided("Result:", sb.toString(), "End.");
			sb.setLength(0);
			System.out.println();
		} else {
			ReportFormatter.reportHeader("!!! Entry Not Found !!!");
		}
	}

	@Override
	public void readAll() {

		dba.readAll().stream().forEach((x) -> sb.append(String.format("ID[%d]: %s\n", x.getID(), x)));

		ReportFormatter.reportDivided("Results:", sb.toString(), "End of Results.");
		sb.setLength(0);
		System.out.println();
	}

	@Override
	public void update() {
		Person source, target;
		int id = targetEntry();

		source = dba.read(id);

		if (source.getID() != null) {
			target = new PersonBuilder().construct(Console.getScanner());

			dba.update(id, target);
			readAll();
		} else {
			ReportFormatter.reportHeader("Unable To Update - Entry Not Found!");
		}
	}

	@Override
	public void delete() {
		int id = targetEntry();

		dba.delete(id);
	}

	private int targetEntry() {
		int id;
		Person target;
		
		readAll();
		System.out.println("Please enter the id:");
		return Console.inputInt();
	}

	@Override
	public String genericToString() {
		return Person.class.getSimpleName();
	}

}
