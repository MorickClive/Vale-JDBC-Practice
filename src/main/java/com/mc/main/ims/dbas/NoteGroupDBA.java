package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class NoteGroupDBA implements DatabaseAccessObject<NoteGroup> {

	private Connection connection;
	private static final String tableName = "NOTE_GROUP";

	public NoteGroupDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(NoteGroup noteGroup) {
		String query;

		try {
			query = String.format("INSERT INTO %s(label) VALUES ('%s')",
					tableName, noteGroup.getLabel());
			connection.createStatement().execute(query);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public NoteGroup read(Integer id) {
		NoteGroup model;
		List<Note> listOfNotes= new ArrayList<>();
		Statement statement;
		ResultSet rs;

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(String.format("SELECT * FROM %s WHERE id=%d", tableName, id));

			rs.next();
			model = ModelParser.toNoteGroup(rs);
			
			rs = statement.executeQuery(String.format("SELECT * FROM NOTES WHERE GROUPID=%d", id));
			while(rs.next()) {
				listOfNotes.add(ModelParser.toNote(rs));
			}
			model.setNoteList(listOfNotes);
			
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NoteGroup> readAll() {
		String query;
		try {
			query = String.format("SELECT * FROM %s", tableName);
			ResultSet rs = connection.createStatement().executeQuery(query);
			List<NoteGroup> list = new ArrayList<>();

			while (rs.next()) {
				list.add(ModelParser.toNoteGroup(rs));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void update(Integer id, NoteGroup replacer) {
		Statement statement;
		String query;

		try {
			statement = connection.createStatement();

			query = String.format("UPDATE %s SET label = '%s' WHERE id=%d",
					tableName, replacer.getLabel(), id);

			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		Statement statement;
		String query;
		
		try {
			statement = connection.createStatement();
			
			query = String.format("DELETE FROM NOTES WHERE GROUPID=%d", id);
			statement.execute(query);

			query = String.format("DELETE FROM %s WHERE id=%d", 
					tableName, id);

			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
