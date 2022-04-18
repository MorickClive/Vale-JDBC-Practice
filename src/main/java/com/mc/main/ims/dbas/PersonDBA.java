package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.mc.main.ims.models.Person;
import com.mc.main.ims.util.DatabaseConnection;
import com.mc.main.ims.util.ModelParser;

public class PersonDBA implements DatabaseAccessObject<Person> {

	private Connection connection;
	private List<Person> list;
	private String query;

	public PersonDBA() {
		super();
		connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(Person person) {
		query = String.format("INSERT INTO PERSON(forename, surname, age) VALUES ('%s', '%s', %d)",
				person.getForename(), person.getSurname(), person.getAge());
		
		try ( Statement statement = connection.createStatement() ) {
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
		query = String.format("SELECT * FROM PERSON WHERE id=%d", id);

		try ( Statement statement = connection.createStatement();
			  ResultSet rs = statement.executeQuery(query);) {
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
		query = "SELECT * FROM PERSON";
		
		try ( Statement statement = connection.createStatement();
			  ResultSet rs = statement.executeQuery(query);) {
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
		query = String.format("UPDATE Person SET forename = '%s', surname = '%s', age = %d WHERE id=%d",
				replacer.getForename(), replacer.getSurname(), replacer.getAge(), id);
		
		try ( Statement statement = connection.createStatement();) {
			statement.execute(query);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		query = String.format("DELETE FROM PERSON WHERE id=%d", id);
		
		try ( Statement statement = connection.createStatement();) {
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
