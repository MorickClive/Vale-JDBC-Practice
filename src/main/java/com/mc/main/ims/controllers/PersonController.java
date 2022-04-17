package com.mc.main.ims.controllers;

import com.mc.main.ims.dbas.PersonDBA;
import com.mc.main.ims.models.Person;
import com.mc.main.ims.models.builders.PersonBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class PersonController implements CRUDController<Person> {

	private int id;
	private Person person;
	private StringBuilder sb;

	private PersonDBA dba;

	public PersonController() {
		super();
		sb = new StringBuilder();
		dba = new PersonDBA();
	}

	@Override
	public void create() {
		person = new PersonBuilder().construct();
		dba.create(person);
	}

	@Override
	public void read() {
		id = Console.targetID();
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
		id = Console.targetID();

		source = dba.read(id);

		if (source.getID() != null) {
			target = new PersonBuilder().construct();

			dba.update(id, target);
			readAll();
		} else {
			ReportFormatter.reportHeader("Unable To Update - Entry Not Found!");
		}
	}

	@Override
	public void delete() {
		dba.delete(Console.targetID());
	}

	@Override
	public String genericToString() {
		return Person.class.getSimpleName();
	}

}
