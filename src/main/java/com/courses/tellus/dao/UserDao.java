package com.courses.tellus.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.courses.tellus.model.User;

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
}
