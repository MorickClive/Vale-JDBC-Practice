package com.mc.main.ims.models;

import java.util.ArrayList;
import java.util.List;

public class NoteGroup {
	
	private Integer id;
	private String label;
	private List<Note> noteList; 
	
	public NoteGroup() {
		super();
		this.label = "???";
		this.noteList = new ArrayList<>();
	}

	public NoteGroup(Integer id, String label) {
		super();
		this.id = id;
		this.label = label;
		this.noteList = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<?> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("ID[" + id + "] - " + label.toUpperCase() + ":");
		this.noteList.stream().forEach(x -> result.append("\n\t" + x.toString()));
		result.append(noteList.isEmpty() ? " [Empty!] ]"  : "\n]");
		
		return result.toString();
	}
	
}
