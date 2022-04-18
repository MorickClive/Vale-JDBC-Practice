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
	private String query, queryChild;
	private List<NoteGroup> list;

	private static final String tableName = "NOTE_GROUP";

	public NoteGroupDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(NoteGroup noteGroup) {
		query = String.format("INSERT INTO %s(label) VALUES ('%s')", tableName, noteGroup.getLabel());
		
		try (Statement statement = connection.createStatement()) {
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
		
		query = String.format("SELECT * FROM %s WHERE id=%d", tableName, id);
		queryChild = String.format("SELECT * FROM NOTES WHERE GROUPID=%d", id);

		try (	Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);) {
			rs.next();
			model = ModelParser.toNoteGroup(rs);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		try (	Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(queryChild);) {
			List<Note> list = new ArrayList<>();
			
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
		query = String.format("SELECT * FROM %s", tableName);
		list = new ArrayList<>();
		
		try (	Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);) {

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
		query = String.format("UPDATE %s SET label = '%s' WHERE id=%d",
				tableName, replacer.getLabel(), id);
		
		try ( Statement statement = connection.createStatement() ) {
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		queryChild = String.format("DELETE FROM NOTES WHERE GROUPID=%d", id);
		query = String.format("DELETE FROM %s WHERE id=%d", tableName, id);
		
		try ( Statement statement = connection.createStatement() ) {
			statement.execute(queryChild);
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
