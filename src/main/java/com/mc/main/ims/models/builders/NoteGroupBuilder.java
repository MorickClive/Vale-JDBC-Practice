package com.mc.main.ims.models.builders;

import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.util.Console;

public class NoteGroupBuilder implements POJOBuilder<NoteGroup> {
	
	private NoteGroup object;
	
	public NoteGroupBuilder() {
		super();
		object = new NoteGroup();
	}
	
	public NoteGroupBuilder label(String name) {
		object.setLabel(name);
		return this;
	}

	public NoteGroup construct() {
		System.out.println("Please enter label name:");
		this.label(Console.input());

		return object;
	}

	public NoteGroup build() {
		return object;
	};
}
