package com.mc.main.ims.models;

public class Note {
	
	private Integer id;
	private String header;
	private String contents;
	
	public Note() {
		super();
	}

	public Note(String header, String contents) {
		super();
		this.header = header;
		this.contents = contents;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Note [id:" + id + " - " + header + "; contents=" + contents + "]";
	}

}
