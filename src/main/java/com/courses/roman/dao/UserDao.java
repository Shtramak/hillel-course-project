package com.courses.roman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.courses.roman.config.ConnectionFactory;
import com.courses.roman.model.User;

public class UserDao {

	private ConnectionFactory connectionFactory;

	public UserDao(final ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * Returns list of users from Data Base.
	 *
	 * @return users list
	 */
	public List<User> loadUsers(final int input) {
		if (input == 1) {
			return Collections.singletonList(new User());
		}
		return new ArrayList<>();
	}

	/**
	 * User to be saved in database.
	 *
	 * @param user to save
	 * @return qty inserted records
	 */
	public int insertUser(User user) {
		try (Connection connection = connectionFactory.getConnection()) {
			final PreparedStatement statement = connection.prepareStatement("INSERT INTO USER(id, name) VALUES(?,?)");
			statement.setLong(1, user.getId());
			statement.setString(2, user.getName());
			return statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}
