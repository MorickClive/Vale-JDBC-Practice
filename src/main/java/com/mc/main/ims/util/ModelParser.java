package com.mc.main.ims.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.models.Person;

public class ModelParser {

	public static Person toPerson(ResultSet rs) {
		Person model = new Person();

		try {
			model.setID(rs.getInt(1));
			model.setForename(rs.getString(2));
			model.setSurname(rs.getString(3));
			model.setAge(rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

	public static Note toNote(ResultSet rs) {
		Note model = new Note();

		try {
			model.setId(rs.getInt(1));
			model.setHeader(rs.getString(3));
			model.setContents(rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

	public static NoteGroup toNoteGroup(ResultSet rs) {
		NoteGroup model = new NoteGroup();

		try {
			model.setId(rs.getInt(1));
			model.setLabel(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

}
