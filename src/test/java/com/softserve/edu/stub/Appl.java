package com.softserve.edu.stub;

import com.softserve.edu.dao.IUserDao;
import com.softserve.edu.service.UserService;

public class Appl {

	public static void main(String[] args) {
		IUserDao userDao = new OutDotUserDaoStub();
		UserService userService = new UserService(userDao);
		System.out.println("result = " + userService.getLastDigits());
	}
}
