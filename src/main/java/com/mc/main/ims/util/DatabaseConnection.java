package com.mc.main.ims.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

	private static Connection activeConnection;
	private static String URL;
	private static String username;
	private static String password;

	public DatabaseConnection(Connection activeConnection) {
		super();
		this.activeConnection = activeConnection;
	}

	public static Connection getConnection() {
		if (activeConnection == null) {
			initConnection();
		}
		return activeConnection;
	}

	private static void initConnection() {
			try {
				readProperties();
				activeConnection = DriverManager.getConnection(URL, username, password);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
	}

	private static void readProperties() throws IOException {
		Properties props = new Properties();
		InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
		
		props.load(is);

		URL = (String) props.get("URL");// + "?serverTimezone=UTC";
		username = (String) props.get("Username");
		password = (String) props.get("Password");		
	}
	
	public static void runSchema(String resourceName) {
		InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream(resourceName);

		try {
			Statement statement = getConnection().createStatement();
			new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
			.lines()
			.forEach(x -> {
				if(x.length() > 0) {
					try {
						statement.execute(x);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testQuery() {
		String debugQuery = "SHOW DATABASES";
		try {
			ResultSet rs = getConnection().createStatement().executeQuery(debugQuery);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			if (activeConnection != null) {
				activeConnection.close();
				System.out.println("\t[RESOURCE]: DB Connection closed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};

}