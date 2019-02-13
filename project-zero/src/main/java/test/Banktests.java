package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.bankingapp.project_zero.Account;
import com.revature.bankingapp.project_zero.Admin;
import com.revature.bankingapp.project_zero.Bank;
import com.revature.bankingapp.project_zero.Customer;
import com.revature.bankingapp.project_zero.Employee;
import com.revature.bankingapp.project_zero.LockedUser;
import com.revature.bankingapp.project_zero.RunBank;

public class Banktests {
	
	public static final RunBank runbank = new RunBank();
	
	
	@Test 
	public void MenuTest() {
		assertEquals(1,1);
	    Bank.getCustomers1().size();
	    assertEquals(Bank.getCustomers1().size(), 0);
	    assertEquals(Bank.getLu(), 3);
	}
	@Test
	 public void ArrayTest() {
		 
		 ArrayList<Account> Test= Bank.getAcc();
		 Account acc = Test.get(0);
		 assertEquals(acc.getAccountNumber(), 1000);
	}
	@Test
	public void CheckAdmin() {
		 
		 ArrayList<Admin> Test2= Bank.getA1();
		 Admin a1 = Test2.get(0);
		 assertEquals(a1.getName(),"Admin");
	}
	 
	@Test
	public void AccountTest(){
		
		assertEquals(Bank.getAcc().size(), 0);
		
		
		
		}
	@Test
	public void LUTest() {
		assertEquals(Bank.getA1(), 1);
	}
	
	@Test
	public void employTest() {
		
		ArrayList<Customer> Test3=Bank.getCustomers1();
		Customer customers1=Test3.get(0);
		assertEquals(customers1.getName(),"Customer");
	}
	

}
