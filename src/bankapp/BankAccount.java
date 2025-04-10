package bankapp;

import java.util.Stack;

public class BankAccount {

	private double balance;
	private Stack<String> transactionHistory;
	private int transactionCounter;
	private AccountSettings settings;
	
	public BankAccount() {
		this.balance = 0;
		settings = new AccountSettings();
		this.transactionHistory = new Stack<>();
		this.transactionCounter = 1;
	}
	
	public void sendAlert(double currentBalance) {
		if (currentBalance < settings.getThreshold()) {
			System.out.println("ALERT: Your balance is below the threshold of $" + settings.getThreshold());
		}
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
		transactionHistory.push(transactionCounter + ". Deposited: $" + amount + ". Account Balance is: $" + this.balance);
		transactionCounter++;
	}
	
	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		}
		if (amount > this.balance) {
			System.out.println("You do not have enough money in your account to withdraw " + amount + " dollars.");
		}
		else {
			this.balance -= amount;
			transactionHistory.push(transactionCounter + ". Withdrew: $" + amount + ". Account Balance is: $" + this.balance);
			transactionCounter++;
		}
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public void showTransactionHistory() {
		if (transactionHistory.isEmpty()) {
	        	System.out.println("No transactions yet.");
	    }
		else {
		    System.out.println("Transaction History:");
		    for (int i = 0; i <= transactionHistory.size() - 1; i++) {
		        System.out.println(transactionHistory.get(i));
		    }
		}
	}
}
