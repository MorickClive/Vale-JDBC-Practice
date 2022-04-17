package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.Person;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class NoteDBA implements DatabaseAccessObject<Note> {

	private Connection connection;
	private int foreignKey;
	private static final String tableName = "NOTES";

	public NoteDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(Note note) {
		String query;

		try {
			query = String.format("INSERT INTO %s(groupid, header, contents) VALUES (%d, '%s', '%s')",
					tableName, foreignKey, note.getHeader(), note.getContents());
			connection.createStatement().execute(query);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Note read(Integer id) {
		Note model;
		Statement statement;
		ResultSet rs;

		try {
			statement = connection.createStatement();
			System.out.println(String.format("SELECT * FROM %s WHERE id=%d", tableName, id));
			
			rs = statement.executeQuery(String.format("SELECT * FROM %s WHERE id=%d", tableName, id));

			rs.next();
			model = ModelParser.toNote(rs);

			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Note> readAll() {
		String query;
		
		try {
			query = String.format("SELECT * FROM %s WHERE groupid=%d",
					tableName, foreignKey);
			System.out.println(query);
			ResultSet rs = connection.createStatement().executeQuery(query);
			List<Note> list = new ArrayList<>();

			while (rs.next()) {
				list.add(ModelParser.toNote(rs));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void update(Integer id, Note replacer) {
		Statement statement;
		String query;

		try {
			statement = connection.createStatement();

			query = String.format("UPDATE %s SET header = '%s', contents = '%s' WHERE id=%d",
					tableName, replacer.getHeader(), replacer.getContents(), id);

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

			query = String.format("DELETE FROM %s WHERE id=%d", 
					tableName, id);

			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void setForeignKey(int id) {
		this.foreignKey = id;
	}

}
