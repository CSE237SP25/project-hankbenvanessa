package bankapp;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
	
	private String username;
	private String password;
	private BankAccount myAccount;
	private List<BankAccount> myAccounts = new ArrayList<>();


	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.myAccount = new BankAccount();
		myAccounts.add(myAccount);
	}
	
	public boolean checkPassword(String passwordAttempt) {
		
		if (password.equals(passwordAttempt)) {
			return true;
		}
		else {
			return false;
		}
	}

	
	// Multiple account methods
	public void createBankAccount() {
		BankAccount newAccount = new BankAccount();
		myAccounts.add(newAccount);
	}
	
	public int getMaxBankAccountID() {
		return myAccounts.size();
	}
	
	public boolean accountDeposit(double amount, int bankAccountNum) {
		if (bankAccountNum < 1 || bankAccountNum > myAccounts.size()) {
			return false;
		}
		else {
			BankAccount account = myAccounts.get(bankAccountNum-1);
			try {
				account.deposit(amount);
			}
			catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}
		
	}
	
	
	public boolean accountWithdrawal(double amount, int bankAccountNum) {
		if (bankAccountNum < 1 || bankAccountNum > myAccounts.size()) {
			return false;
		}
		else {
			BankAccount account = myAccounts.get(bankAccountNum-1);
			try {
				if (amount > account.getCurrentBalance()) {
					return false;
				}
				account.withdraw(amount);
			}
			catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}
	}
	
	
	public double getAccountBalance(int bankAccountNum) {
		if (bankAccountNum < 1 || bankAccountNum > myAccounts.size()) {
			return 0;
		}
		else {
			BankAccount account = myAccounts.get(bankAccountNum-1);
			return account.getCurrentBalance();
		}
	}
	
	public void showTransactionHistory(int bankAccountNum) {
		if (bankAccountNum < 1 || bankAccountNum > myAccounts.size()) {
			return;
		}
		else {
			BankAccount account = myAccounts.get(bankAccountNum-1);
			account.showTransactionHistory();
		}
	}
	
	
}