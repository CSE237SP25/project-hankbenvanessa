package bankapp;

public class UserAccount {
	String username;
	String password;
	BankAccount myAccount;


	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.myAccount = new BankAccount();
	}
	
	public boolean checkPassword(String passwordAttempt) {
		
		if (password == passwordAttempt) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean accountDeposit(double amount) {
		try {
			myAccount.deposit(amount);
			return true;
		}
		catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	
	
}