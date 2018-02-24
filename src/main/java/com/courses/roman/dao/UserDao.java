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

	public int insertUser(User user) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		try (Connection connection = connectionFactory.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER(id, name) VALUES(?,?)");
			preparedStatement.setLong(1, user.getId());
			preparedStatement.setString(2, user.getName());
			return preparedStatement.executeUpdate();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}
