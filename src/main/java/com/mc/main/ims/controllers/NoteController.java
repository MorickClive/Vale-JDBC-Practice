package com.mc.main.ims.controllers;

import com.mc.main.ims.dbas.NoteDBA;
import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.builders.NoteBuilder;
import com.mc.main.ims.util.Console;
import com.mc.main.ims.util.ReportFormatter;

public class NoteController implements CRUDController<Note> {

	private int id = 0;
	private Note note;
	private StringBuilder sb;

	private NoteDBA dba;
	
	public NoteController(StringBuilder sb) {
		super();
		this.sb = sb;
		this.dba = new NoteDBA();
	}
	
	@Override
	public void create() {
		note = new NoteBuilder().construct();
		dba.create(note);
	}

	@Override
	public void read() {
		id = Console.targetID();
		note = dba.read(id);

		ReportFormatter.reportDivided("Results:", note.toString());
		System.out.println();
	}

	@Override
	public void readAll() {
		dba.readAll().stream().forEach((x) -> sb.append(String.format("ID[%d]: %s\n", x.getId(), x)));

		ReportFormatter.reportDivided("Results:", sb.toString(), "End of Results.");
		sb.setLength(0);
		System.out.println();
	}

	@Override
	public void update() {
		id = Console.targetID();
		note = new NoteBuilder().construct();
		
		dba.update(id, note);
	}

	@Override
	public void delete() {
		id = Console.targetID();
		dba.delete(id);
	}

	@Override
	public String genericToString() {
		return Note.class.getSimpleName();
	}
	
	public void setForeignKey(int id) {
		this.dba.setForeignKey(id);
	}

}
