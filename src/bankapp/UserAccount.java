package bankapp;

public class UserAccount {
	
	private String username;
	private String password;
	private BankAccount myAccount;


	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.myAccount = new BankAccount();
	}
	
	public boolean checkPassword(String passwordAttempt) {
		
		if (password.equals(passwordAttempt)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean accountDeposit(double amount) {
		try {
			myAccount.deposit(amount);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	public boolean accountWithdrawal(double amount) {
		try {
			myAccount.withdraw(amount);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	public double getAccountBalance() {
		return this.myAccount.getCurrentBalance();
	}
	
	public void showTransactionHistory() {
		this.myAccount.showTransactionHistory();
	}
	
	
	
}