package com.mc.main.ims.controllers;

import com.mc.main.ims.dbas.NoteGroupDBA;
import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.models.builders.NoteGroupBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class NoteGroupController implements ListController<NoteGroup> {
	
	private int id = 0;
	private NoteGroup noteGroup;
	private StringBuilder sb;

	private NoteGroupDBA dba;
	private NoteController subController;
	
	public NoteGroupController() {
		super();
		sb = new StringBuilder();
		dba = new NoteGroupDBA();
		subController = new NoteController(sb);
	}
	
	@Override
	public void create() {
		noteGroup = new NoteGroupBuilder().construct();
		dba.create(noteGroup);
	}

	@Override
	public void read() {
		id = Console.targetID();
		noteGroup = dba.read(id);
		
		ReportFormatter.reportDivided("Results:", noteGroup.toString());
		System.out.println();
	}

	@Override
	public void readAll() {
		dba.readAll().stream()
		.forEach((x) -> sb.append(String.format("ID[%d], Label: %s\n", x.getId(), x.getLabel())));

		ReportFormatter.reportDivided("Results:", sb.toString(), "End of Results.");
		sb.setLength(0);
		System.out.println();
	}

	@Override
	public void update() {
		id = Console.targetID();
		noteGroup = new NoteGroupBuilder().construct();
		dba.update(id, noteGroup);
	}

	@Override
	public void delete() {
		id = Console.targetID();
		dba.delete(id);
	}

	@Override
	public CRUDInterface<Note> modify() {
		System.out.println("Which group do you want to modify?");
		subController.setForeignKey(Console.targetID());
		
		return subController;
	}

	@Override
	public Class<Note> getSubMenuType() {
		return Note.class;
	}
	
}
