package com.softserve.edu.mockito;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.dao.IUserDao;
import com.softserve.edu.dao.UserDao;
import com.softserve.edu.service.UserService;

public class UnitUserTest {

	@Test
	public void checkLastDigits() {
		IUserDao userDao = Mockito.mock(IUserDao.class);
		//IUserDao userDao =  Mockito.spy(new UserDao());
		//
		Mockito.when(userDao.getIPAddress()).thenReturn(".123");
		//Mockito.doCallRealMethod().when(userDao).getIPAddress("");
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "123";
		//expected = "148";
		actual = userService.getLastDigits();
		//actual = userService.getLastDigits("");
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

	//@Test
	public void checkOutDot() {
		IUserDao userDao = Mockito.mock(IUserDao.class);
		Mockito.when(userDao.getIPAddress()).thenReturn("aaa181");
		UserService userService = new UserService(userDao);
		String actual;
		String expected;
		//
		expected = "aaa181";
		actual = userService.getLastDigits();
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

	//@Test
	public void checkLastDot() {
		IUserDao userDao = Mockito.mock(IUserDao.class);
		Mockito.when(userDao.getIPAddress()).thenReturn("aaa181.");
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
