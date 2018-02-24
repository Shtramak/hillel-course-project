package com.courses.tellus.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.courses.tellus.model.User;

public class UserDaoTest {

	private UserDao userDao = new UserDao();

	@Test
	public void testLoadUserResultNot1ReturnEmptyList() {
		final List<User> result = userDao.loadUsers(10);
		Assert.assertTrue( result.size() == 0);
	}

	@Test
	public void testIfResultIs1ReturnNotEmptyList() {
		final List<User> result = userDao.loadUsers(1);
		Assert.assertTrue( result.size() == 1);
	}

	@Test
	public void testLoadUser() {

	}

}
