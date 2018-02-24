package com.courses.roman.dao;

import java.io.FileReader;
import java.util.List;

import org.h2.tools.RunScript;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.courses.roman.config.ConnectionFactory;
import com.courses.roman.model.User;

public class UserDaoTest {

	private UserDao userDao = new UserDao();

	@BeforeClass
	public static void before() throws Exception {

		RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/test.sql"));
	}

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
	public void testInsertUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Test");
		MatcherAssert.assertThat(userDao.insertUser(user), CoreMatchers.is(1));
	}

}
