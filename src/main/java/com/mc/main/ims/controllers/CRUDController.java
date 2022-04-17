package com.mc.main.ims.controllers;

import com.mc.main.ims.dbas.DatabaseAccessObject;
import com.mc.main.ims.models.builders.POJOBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class CRUDController<T> implements CRUDInterface<T> {

	private int id;
	private T model;
	private DatabaseAccessObject<T> dba;
	private POJOBuilder<T> builder;
	private StringBuilder sb;
	
	public CRUDController(POJOBuilder<T> builder, DatabaseAccessObject<T> dba) {
		super();
		this.sb = new StringBuilder();
		this.builder = builder;
		this.dba = dba;
	}
	
	public CRUDController(POJOBuilder<T> builder, DatabaseAccessObject<T> dba, StringBuilder sb) {
		super();
		this.sb = sb;
		this.builder = builder;
		this.dba = dba;
	}
	
	@Override
	public void create() {
		model = builder.construct();
		dba.create(model);
	}

	@Override
	public void read() {
		id = Console.targetID();
		model = dba.read(id);

		ReportFormatter.reportDivided("Results:", model.toString());
		System.out.println();
	}

	@Override
	public void readAll() {
		dba.readAll().stream().forEach((x) -> sb.append(String.format("%s\n", x)));

		ReportFormatter.reportDivided("Results:", sb.toString(), "End of Results.");
		sb.setLength(0);
		System.out.println();
	}

	@Override
	public void update() {
		id = Console.targetID();
		model = builder.construct();
		
		dba.update(id, model);
	}

	@Override
	public void delete() {
		id = Console.targetID();
		dba.delete(id);
	}

}
