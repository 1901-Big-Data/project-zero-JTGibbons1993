package com.revature.bankingapp.project_zero;


import java.io.Serializable;

import LoggingUtil.LoggingUtil;

public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6801120831533554290L;
	private Account account; //customer can have an account
	private String name;
	private String password;
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
		LoggingUtil.logInfo(" Set typed ");
	}



	private int ssn; //distinct
	private String type;
	private int accountNumber;
	
	public Customer() {//instantiate
		this.account = new Account();
		this.name = "";
		this.password = "";
		this.ssn = 0;
		this.type = "regular"; 
		LoggingUtil.logInfo(" Regular ");
		
	} 
	
	

	public Customer(String name, String password, int ssn) {
		this.account = new Account();//is empty rn
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.type="regular";
		LoggingUtil.logInfo(" Regular ");
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
		LoggingUtil.logInfo(" Password Set ");
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
		LoggingUtil.logInfo(" Account Selected ");
	}
	
	public static boolean deposit(int accountNumber, double amount) { //deposit feature that points at account number along with how much you'd like to deposit
		boolean status=false;
		if(amount> 0) { // if amount is greater than zero than only you can perform deposit
		for(int i = 0;i < Bank.getCustomers1().size() ;i++) { //for loop to search if the account number is valid.
				if(Bank.getCustomers1().get(i).getAccount().getAccountNumber()==accountNumber) {//searching the counter for only existing accounts
					
					Bank.getCustomers1().get(i).getAccount().setBalance(Bank.getCustomers1().get(i).getAccount().getBalance() +amount);//adds deposit to current amount in account
					status = true;
					LoggingUtil.logInfo(" Deposit Good ");
				}
						
		}
		}
		return status;//status = 0 -->invalid amount or invalid account number
					   //status 1 -->deposited
	}
	public static boolean withdraw(int accountNumber, double amount) {
		boolean status2=false;
		if(amount> 0 ) { // if amount is greater than zero than only you can perform withdraw, cannot withdraw more than 10,000$ at a time
		for(int i = 0;i < Bank.getCustomers1().size() ;i++) { //for loop to search if the account number is valid.
				if(Bank.getCustomers1().get(i).getAccount().getAccountNumber()==accountNumber) {//searching the counter for only existing accounts
					
					Bank.getCustomers1().get(i).getAccount().setBalance(Bank.getCustomers1().get(i).getAccount().getBalance() -amount);//should subtract withdraw amt
					status2 = true;
					LoggingUtil.logInfo(" Withdraw good ");
				
				}
						
		}
		}
		return status2;//status = 0 -->invalid amount or invalid account number
					   //status 1 -->withdrawn
		
		
	}



	public int getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
		LoggingUtil.logInfo(" account number set");
	}

}