package com.softserve.edu;

import com.softserve.edu.dao.UserDao;
import com.softserve.edu.service.UserService;

public class Appl {

	public static void main(String[] args) {
		UserService userService = new UserService(new UserDao());
		System.out.println("result = " + userService.getLastDigits());
	}
}
