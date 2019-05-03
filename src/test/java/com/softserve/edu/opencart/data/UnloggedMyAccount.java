package com.softserve.edu.opencart.data;

public enum UnloggedMyAccount {
	REGISTER("Register"),
	LOGIN("Login");
	//
	private String name;

	private UnloggedMyAccount(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
