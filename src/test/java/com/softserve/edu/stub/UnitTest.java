package com.softserve.edu.stub;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.dao.IUserDao;
import com.softserve.edu.service.UserService;

public class UnitTest {

	@Test
	public void checkLastDigits() {
		IUserDao userDao = new ValidUserDaoStub();
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "123";
		actual = userService.getLastDigits();
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

	@Test
	public void checkOutDot() {
		IUserDao userDao = new OutDotUserDaoStub();
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "aaa181";
		actual = userService.getLastDigits();
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

	@Test
	public void checkLastDot() {
		IUserDao userDao = new LastDotUserDaoStub();
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "";
		actual = userService.getLastDigits();
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

}
