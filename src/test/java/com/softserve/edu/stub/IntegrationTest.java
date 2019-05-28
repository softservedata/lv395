package com.softserve.edu.stub;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.service.UserService;

public class IntegrationTest {

	@Test
	public void checkLastDigits() {
		UserDao userDao = new UserDao();
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "148";
		actual = userService.getLastDigits();
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}
	
}
