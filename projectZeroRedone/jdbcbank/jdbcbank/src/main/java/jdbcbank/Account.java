package jdbcbank;

public class Account {
	private String accountNumber;
	private double balance;
	
	public Account() {
		accountNumber = "000000000";
		balance = 0;
	}
	public Account(String accoountNumber) {
		this();
		setAccountNumber(accoountNumber);
	}
	public Account(String accountNumber,double balance) {
		this(accountNumber);
		deposit(balance);
	}
	
	public boolean deposit(double amount) {
		double b = getBalance() + amount;
		if(Database.setBalance(getAccountNumber(),b)) {
			balance = b;
			return true;
		}
		return false;
	}
	public void withdraw(double amount) throws Exception {
		if(getBalance() - amount >= 0) {
			double b = getBalance() - amount;
			if(Database.setBalance(getAccountNumber(),b)) {
				balance = b;
				return;	
			}
			else {
				throw new Exception("Database error.");
			}
		}
		else {
			throw new Exception("Not enough balance.");
		}
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	@Override
	public String toString() {
		return "Account # : "+getAccountNumber()+"\r\nBalance: "+getBalance();
	}
}
