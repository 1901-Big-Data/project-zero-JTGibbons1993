package com.revature.bankingapp.project_zero;


import java.io.Serializable;

import LoggingUtil.LoggingUtil;

public class LockedUser extends Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1645045181987147315L;
	private Account account; //customer can have an account
	private String name;
	private String password; // last name CHANGE IT
	private int ssn;   //fields
	private String type;
	private static int accountNumber;
	
	
	
	public LockedUser() {
		super();
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getSsn() {
		return ssn;
	}


	public void setSsn(int ssn) {
		this.ssn = ssn;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LockedUser(String name, String password, int ssn) {
		super();
		this.account = new Account();
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.type = "locked";
		LoggingUtil.logInfo(" User Locked ");
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
		LoggingUtil.logInfo(" Set account ");
	}
	
}