package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.api.ErrorCode;

import com.mc.main.ims.models.Person;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class PersonDBA implements DatabaseAccessObject<Person> {

	private Connection connection;
	private Statement statement;
	private String query;
	
	private ResultSet rs;
	private List<Person> list;

	public PersonDBA() {
		super();
		connection = DatabaseConnection.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Person person) {
		try {
			query = String.format("INSERT INTO PERSON(forename, surname, age) VALUES ('%s', '%s', %d)",
					person.getForename(), person.getSurname(), person.getAge());
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Person read(Integer id) {
		Person model;

		try {
			rs = statement.executeQuery(String.format("SELECT * FROM PERSON WHERE id=%d", id));

			rs.next();
			model = ModelParser.toPerson(rs);

			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Person> readAll() {
		try {
			rs = statement.executeQuery("SELECT * FROM PERSON");
			list = new ArrayList<>();

			while (rs.next()) {
				list.add(ModelParser.toPerson(rs));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void update(Integer id, Person replacer) {
		try {
			query = String.format("UPDATE Person SET forename = '%s', surname = '%s', age = %d WHERE id=%d",
					replacer.getForename(), replacer.getSurname(), replacer.getAge(), id);

			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		try {
			query = String.format("DELETE FROM PERSON WHERE id=%d", id);
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
