package com.qa.user_testing_app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtilities {

	private static final String URL = "jdbc:h2:mem:test";
	private static Connection conn;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (conn == null) {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(URL);
		}
		return conn;
	}
	
	public static void initialiseDatabase(final String SQL) {
		if (SQL == null) throw new IllegalArgumentException("Must provide SQL to initialise the database");
		
		try {
			if (conn == null) conn = getConnection();
			final Statement statement = conn.createStatement();
			statement.execute(SQL);
			System.out.println("Created table");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something went wrong initialising the database, exiting.");
			System.exit(1);
		}
	}
}
