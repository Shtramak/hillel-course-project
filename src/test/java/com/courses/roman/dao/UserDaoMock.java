package com.courses.roman.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.courses.roman.config.ConnectionFactory;
import com.courses.roman.model.User;

public class UserDaoMock {

	private static ConnectionFactory connectionFactory;
	private Connection mockConnection;
	private PreparedStatement mockStatement;
	private static UserDao userDao;

	@BeforeAll
	public static void init() {
		connectionFactory = mock(ConnectionFactory.class);
		userDao = new UserDao(connectionFactory);
	}

	@BeforeEach
	public void reInitDepartmentDao() throws SQLException {
		mockConnection = mock(Connection.class);
		mockStatement = mock(PreparedStatement.class);
		when(connectionFactory.getConnection()).thenReturn(mockConnection);
	}

	@Test
	public void testInsertUser() throws Exception {
		User user = new User();
		user.setName("Ivan");
		user.setId(1L);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeUpdate()).thenReturn(1);
		int result = userDao.insertUser(user);
		Assertions.assertTrue(result == 1);
	}



}
