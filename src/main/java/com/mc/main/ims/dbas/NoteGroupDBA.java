package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.api.ErrorCode;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class NoteGroupDBA implements DatabaseAccessObject<NoteGroup> {

	private Connection connection;
	private Statement statement;
	private String query;

	private ResultSet rs;
	private List<NoteGroup> list;

	private static final String tableName = "NOTE_GROUP";

	public NoteGroupDBA() {
		super();
		connection = DatabaseConnection.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(NoteGroup noteGroup) {
		try {
			query = String.format("INSERT INTO %s(label) VALUES ('%s')", tableName, noteGroup.getLabel());
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public NoteGroup read(Integer id) {
		NoteGroup model;
		List<Note> list = new ArrayList<>();

		try {
			rs = statement.executeQuery(String.format("SELECT * FROM %s WHERE id=%d", tableName, id));
			rs.next();
			model = ModelParser.toNoteGroup(rs);

			rs = statement.executeQuery(String.format("SELECT * FROM NOTES WHERE GROUPID=%d", id));
			while (rs.next()) {
				list.add(ModelParser.toNote(rs));
			}
			model.setNoteList(list);

			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NoteGroup> readAll() {
		try {
			query = String.format("SELECT * FROM %s", tableName);
			ResultSet rs = statement.executeQuery(query);
			list = new ArrayList<>();

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
		try {
			query = String.format("UPDATE %s SET label = '%s' WHERE id=%d",
					tableName, replacer.getLabel(), id);

			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		String queryChild;
		
		try {
			queryChild = String.format("DELETE FROM NOTES WHERE GROUPID=%d", id);
			statement.execute(queryChild);

			query = String.format("DELETE FROM %s WHERE id=%d",
					tableName, id);
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
