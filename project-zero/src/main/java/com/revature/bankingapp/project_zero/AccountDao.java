package com.revature.bankingapp.project_zero;


import java.util.ArrayList;
import java.util.List;

//import com.revature.pojo.BankApp;

public interface AccountDao {
	
	public void createAccountDB(Account account) throws ClassNotFoundException;
	
	public void createAccountLocked(LockedUser lockeduser) throws ClassNotFoundException;
	
	public Account retrieveAccountDB(Account account) throws ClassNotFoundException;
	
	public ArrayList<Account> retrieveAccountDB();
	
	public void updateAccountDB(Account account) throws ClassNotFoundException;
	
	public void deleteAccountDB(Account account) throws ClassNotFoundException;
	

}
