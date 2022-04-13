package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mc.main.ims.models.Person;
import com.mc.main.ims.util.DatabaseConnection;

public class PersonDBA implements DatabaseAccessObject<Person> {

	private Connection connection;

	public PersonDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	private Person toModel(ResultSet rs) {
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

	@Override
	public boolean create(Person person) {
		String query;

		try {
			query = String.format("INSERT INTO PERSON(forename, surname, age) VALUES ('%s', '%s', %d)",
					person.getForename(), person.getSurname(), person.getAge());
			connection.createStatement().execute(query);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Person read(Integer id) {
		Person model;
		Statement statement;
		ResultSet rs;

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(String.format("SELECT * FROM PERSON WHERE id=%d", id));

			rs.next();
			model = toModel(rs);

			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Person> readAll() {
		try {
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM PERSON");
			List<Person> list = new ArrayList<>();

			while (rs.next()) {
				list.add(toModel(rs));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void update(Integer id, Person replacer) {
		Statement statement;
		String query;

		try {
			statement = connection.createStatement();

			query = String.format("UPDATE Person SET forename = '%s', surname = '%s', age = %d WHERE id=%d",
					replacer.getForename(), replacer.getSurname(), replacer.getAge(), id);

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

			query = String.format("DELETE FROM PERSON WHERE id=%d", id);

			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
