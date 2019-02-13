package com.revature.bankingapp.project_zero;


import java.io.Serializable;
import java.util.Random;

import LoggingUtil.LoggingUtil;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090943915859253842L;
//long represents integers group and memory and memory size is 8 bytes
private int accountNumber;
private double balance;
static private int accountNumberGenerator = 1000;  //since static it'll produce new value everytime account is created
transient Random r = new Random();

//instance members are created every time object is made, gets initialized back to 1000, and reincrements gives 1001
//static members are generated only once, although you create n objects, meaning they wont get initialized back to 1000, so on every increment they produce a new value

//method with the same name is the constructor 
//object is invoked when constructor is present, no call needed
//it would execute by call if turned into a method
public Account() {
	this.accountNumber = accountNumberGenerator + ( r.nextInt(1000) + 1);// will add account 1000+
	this.balance = 0;
	LoggingUtil.logInfo(" Good Account ");
	
}
public int generatorNum(){
	//accountNumber = accountNumberGenerator + ( r.nextInt(1000) + 1);
	return accountNumberGenerator + ( r.nextInt(1000) + 1);
	
}


public Account(double balance) { // they enter a balance at the beginning and that is their
	this.balance = balance;
	this.accountNumber = accountNumberGenerator + (r.nextInt(1000)+1);
	LoggingUtil.logInfo(" Generated ");
}


public int getAccountNumber() { //NULL FOR SOME REASON(fixed, not anymore)
	return accountNumber;
}

public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
	LoggingUtil.logInfo(" Balance is set ");
}



}