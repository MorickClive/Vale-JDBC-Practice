package com.mc.main.ims.controllers;

import java.util.HashMap;

import com.mc.main.ims.models.Person;
import com.mc.main.ims.models.PersonBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class PersonController implements CRUDController<Person> {

	private int id = 0;
	private Person person;
	private HashMap<Integer, Person> storedPeople;
	private StringBuilder sb;

	public PersonController() {
		super();
		storedPeople = new HashMap<>();
		sb = new StringBuilder();
	}

	@Override
	public void create() {
		person = new PersonBuilder().construct(Console.getScanner());
		storedPeople.put(++id, person);
	}

	@Override
	public void read() {
		if (!storedPeople.isEmpty()) {
			System.out.println("Please enter id:");
			int id = Console.inputInt();
			Person targ = storedPeople.get(id);

			if (targ != null) {
				sb.append(String.format("%s", person));
				ReportFormatter.reportDivided("Result:", sb.toString(), "End.");
				sb.setLength(0);
				System.out.println();
			} else {
				ReportFormatter.reportHeader("!!! Entry Not Found !!!");
			}
		}else {
			ReportFormatter.reportHeader("!!! No Results Found !!!");
		}
	}

	@Override
	public void readAll() {
		if (!storedPeople.isEmpty()) {
			storedPeople.forEach((x, y) -> sb.append(String.format("ID[%d]: %s\n", x, y)));
			ReportFormatter.reportDivided("Results:", sb.toString(), "End of Results.");
			sb.setLength(0);
			System.out.println();
		} else {
			ReportFormatter.reportHeader("!!! No Results Found !!!");
		}
	}

	@Override
	public void update() {
		Person target;
		int id = targetEntry();

		target = storedPeople.get(id);
		target = new PersonBuilder().construct(Console.getScanner());

		System.out.println("\nUpdated List: ");
		storedPeople.put(id, target);
	}

	@Override
	public void delete() {
		storedPeople.remove(targetEntry());
	}

	private int targetEntry() {
		int id;
		Person target;

		if (!storedPeople.isEmpty()) {
			readAll();
			System.out.println("Please enter the id:");
			return Console.inputInt();
		} else {
			return 0;
		}
	}

	@Override
	public String genericToString() {
		return Person.class.getSimpleName();
	}

}
