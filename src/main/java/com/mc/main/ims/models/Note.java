package com.mc.main.ims.models;

import com.mc.main.ims.util.ReportFormatter;

public class Note {
	
	private Integer id;
	private String header;
	private String contents;
	
	public Note() {
		super();
		this.header = "???";
		this.contents = "???";
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
		String header, contents;
		header = String.format("%2$s - ID[%1$d]", id.intValue(), this.header);
		contents = String.format("\tContents:\n\t%2$s%1$s\n\t%2$s", ("\n"+this.contents).replace("\n", "\n\t   "), ReportFormatter.div);
		
		return String.format("%s\n%s\n", header, contents);
	}
	
}
