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
	private static Properties credentials;

	private DatabaseConnection() { super(); }

	public static Connection getConnection() {
		if (activeConnection == null) {
			initConnection();
		}
		return activeConnection;
	}

	private static void initConnection() {
		String URL, username, password;

		try {

			if (credentials == null) {
				readProperties("db.properties");
			}

			URL = (String) credentials.get("URL");// + "?serverTimezone=UTC";
			username = (String) credentials.get("Username");
			password = (String) credentials.get("Password");

			activeConnection = DriverManager.getConnection(URL, username, password);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void readProperties(String properties) throws IOException {
		credentials = new Properties();
		InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream(properties);

		credentials.load(is);
	}

	public static void runSchema(String resourceName) {
		InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream(resourceName);

		try {
			Statement statement = getConnection().createStatement();
			new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().forEach(x -> {
				if (x.length() > 0) {
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