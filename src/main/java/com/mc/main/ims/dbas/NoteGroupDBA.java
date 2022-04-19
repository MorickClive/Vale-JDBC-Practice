package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.models.NoteGroup;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class NoteGroupDBA implements DatabaseAccessObject<NoteGroup> {

	private Connection connection;
	private String query, queryChild;
	private List<NoteGroup> list;

	private static final String tableName = "NOTE_GROUP";

	public NoteGroupDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(NoteGroup noteGroup) {
		query = String.format("INSERT INTO %s(label) VALUES (?)", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, noteGroup.getLabel());

			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public NoteGroup read(Integer id) {
		NoteGroup model;

		query = String.format("SELECT * FROM %s WHERE id=?", tableName);
		queryChild = "SELECT * FROM NOTES WHERE GROUPID=?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id.intValue());

			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				model = ModelParser.toNoteGroup(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try (PreparedStatement statement = connection.prepareStatement(queryChild)) {
			statement.setInt(1, id.intValue());

			try (ResultSet rs = statement.executeQuery()) {
				List<Note> list = new ArrayList<>();

				while (rs.next()) {
					list.add(ModelParser.toNote(rs));
				}
				model.setNoteList(list);

				return model;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NoteGroup> readAll() {
		query = String.format("SELECT * FROM %s", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery();) {
			list = new ArrayList<>();

			while (rs.next()) {
				list.add(ModelParser.toNoteGroup(rs));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public void update(Integer id, NoteGroup replacer) {
		query = String.format("UPDATE %s SET label=? WHERE id=?", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, replacer.getLabel());
			statement.setInt(2, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		queryChild = "DELETE FROM NOTES WHERE GROUPID=?";
		query = String.format("DELETE FROM %s WHERE id=?", tableName);

		try (PreparedStatement statementChildTable = connection.prepareStatement(queryChild)) {
			statementChildTable.setInt(1, id);
			statementChildTable.execute();

			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, tableName);
				statement.setInt(1, id);

				return statement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
