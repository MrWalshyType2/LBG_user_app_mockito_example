package com.qa.user_testing_app.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.user_testing_app.utils.ConnectionUtilities;

public class UserRepository {
	
	public List<User> findAll() {
		try {
			Connection conn = ConnectionUtilities.getConnection(); 
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM `user`");
			List<User> users = unwrapSet(rs);
			return users;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User save(User user) {
		final String SQL = "INSERT INTO `user` (`forename`, `surname`, `age`) VALUES (?, ?, ?);";
		try {
			Connection conn = ConnectionUtilities.getConnection(); 
			PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			// bind data to the prepared statement to prevent SQL injection
			ps.setString(1, user.getForename());
			ps.setString(2, user.getSurname());
			ps.setInt(3, user.getAge());
			
			// executeUpdate() is used for INSERT, UPDATE and DELETE
			int modifiedCount = ps.executeUpdate();
			
			if (modifiedCount > 0) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					user.setId(keys.getLong(1));
					return user;
				}
				return user;
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong saving the user to the database.");
			e.printStackTrace();
		}
		return null;
	}

	private List<User> unwrapSet(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<>();

		while (rs.next()) {
			User user = unwrap(rs); // get a user out of the result set
			users.add(user); // add them to a list for returning after
		}
		return users;
	}

	private User unwrap(ResultSet rs) throws SQLException {
		User user = new User();

		Long id = rs.getLong("id");
		String forename = rs.getString("forename");
		String surname = rs.getString("surname");
		int age = rs.getInt("age");

		user.setId(id);
		user.setForename(forename);
		user.setSurname(surname);
		user.setAge(age);
		return user;
	}
}
