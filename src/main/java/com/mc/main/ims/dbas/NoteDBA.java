package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mc.main.ims.models.Note;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class NoteDBA implements DatabaseAccessObject<Note> {

	private Connection connection;
	private String query;
	private List<Note> list;

	private int foreignKey;
	private static final String tableName = "NOTES";

	public NoteDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(Note note) {
		query = String.format("INSERT INTO %s(groupid, header, contents) VALUES (?, ?, ?)", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, foreignKey);
			statement.setString(2, note.getHeader());
			statement.setString(3, note.getContents());
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Note read(Integer id) {
		query = String.format("SELECT * FROM %s WHERE id=?", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				return ModelParser.toNote(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Note> readAll() {
		query = String.format("SELECT * FROM %s WHERE groupid=?", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, foreignKey);
			list = new ArrayList<>();
			
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					list.add(ModelParser.toNote(rs));
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public void update(Integer id, Note replacer) {
		query = String.format("UPDATE %s SET header=?, contents=? WHERE id=?", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, replacer.getHeader());
			statement.setString(2, replacer.getContents());
			statement.setInt(3, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		query = String.format("DELETE FROM %s WHERE id=?", tableName);

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setForeignKey(int id) {
		this.foreignKey = id;
	}

}
