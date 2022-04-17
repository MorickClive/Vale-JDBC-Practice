package com.mc.main.ims.models.builders;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.util.Console;

public class NoteBuilder {
	
	private Note object;
	
	public NoteBuilder() {
		super();
		object = new Note();
	}
	
	public NoteBuilder header(String name) {
		object.setHeader(name);
		return this;
	}	
	
	public NoteBuilder contents(String name) {
		object.setContents(name);
		return this;
	}

	public Note construct() {
		System.out.println("Please enter header:");
		this.header(Console.input());
		
		System.out.println("Please enter note contents:");
		this.contents(Console.input());

		return object;
	}

	public Note build() {
		return object;
	};
}
