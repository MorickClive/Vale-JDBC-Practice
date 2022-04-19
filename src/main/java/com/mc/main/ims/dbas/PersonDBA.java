package com.mc.main.ims.dbas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		query = "INSERT INTO PERSON(forename, surname, age) VALUES (?, ?, ?)";
		
		try ( PreparedStatement statement = connection.prepareStatement(query) ) {
			statement.setString(1, person.getForename());
			statement.setString(2, person.getSurname());
			statement.setInt(3, person.getAge());
			
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Person read(Integer id) {
		Person model;
		query = "SELECT * FROM PERSON WHERE id=?";

		try ( PreparedStatement statement = connection.prepareStatement(query) ) {
			
			statement.setInt(1, id);
			
			try ( ResultSet rs = statement.executeQuery() ) {
				rs.next();
				model = ModelParser.toPerson(rs);
				return model;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Person> readAll() {
		query = "SELECT * FROM PERSON";
		
		try ( PreparedStatement statement = connection.prepareStatement(query);
			  ResultSet rs = statement.executeQuery()) {
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
		query = "UPDATE Person SET forename = ?, surname = ?, age = ? WHERE id=?";
		
		try ( PreparedStatement statement = connection.prepareStatement(query) ) {
			statement.setString(1, replacer.getForename());
			statement.setString(2, replacer.getSurname());
			statement.setInt(3, replacer.getAge());
			statement.setInt(4, id);
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Integer id) {
		query = "DELETE FROM PERSON WHERE id=?";
		
		try ( PreparedStatement statement = connection.prepareStatement(query) ) {
			statement.setInt(1, id);
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
