package com.softserve.edu.service;

import com.softserve.edu.dao.IUserDao;

public class UserService {

	private IUserDao userDao;

	public UserService(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public String getLastDigits() {
		String origin = getUserDao().getIPAddress();
		return origin.substring(origin.lastIndexOf(".") + 1);
	}

	public String getLastDigits(String text) {
		String origin = getUserDao().getIPAddress(text);
		return origin.substring(origin.lastIndexOf(".") + 1);
	}

}