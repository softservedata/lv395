package com.softserve.edu.opencart.data;

public enum LoggedMyAccount {
	MY_ACCOUNT("My Account"),
	ORDER_HISTORY("Order History"),
	TRANSACTIONS("Transactions"),
	DOWNLOADS("Downloads"),
	LOGOUT("Logout");
	//
	private String name;

	private LoggedMyAccount(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
