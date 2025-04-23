package bankapp;

import java.util.Stack;

public class BankAccount {

	private double balance;
	private Stack<String> transactionHistory;
	private int transactionCounter;
	public AccountSettings settings;
	protected double dailyWithdrawnAmount;
	private boolean transactionsFrozen = false;
	private boolean isCardReportedLostOrStolen = false;
	
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
	public void freezeTransactions() {
		transactionsFrozen = true;
		System.out.println("All transactions have been frozen");
	}
	
	public void unfreezeTransactions() {
		transactionsFrozen = false;
		System.out.println("Transactions have been unfrozen");
	}
	
	public boolean areTransactionsFrozen() {
		return transactionsFrozen;
	}
	
	public void reportCardLostOrStolen() {
		isCardReportedLostOrStolen = true;
		System.out.println("Your card has been reported lost/stolen. All transactions are now blocked");
	}
	
	public boolean isCardBlocked() {
		return isCardReportedLostOrStolen;
	}
	
	public void deposit(double amount) {
		if (areTransactionsFrozen()) {
			System.out.println("Transaction denied: your account transactions are currently frozen");
      return;
    }
		if (isCardBlocked()) {
			System.out.println("Transaction blocked: Card has been reported lost/stolen");
			return;
		}
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
		transactionHistory.push(transactionCounter + ". Deposited: $" + amount + ". Account Balance is: $" + this.balance);
		transactionCounter++;
	}
	
	public void withdraw(double amount) {
		if (areTransactionsFrozen()) {
			System.out.println("Transaction denied: your account transactions are currently frozen");
			return;
		}
    if (isCardBlocked()) {
      System.out.println("Transaction blocked: Card has been reported lost/stolen");
			return;
    }
		if (amount < 0) {
			throw new IllegalArgumentException();
		}
		if (amount > this.balance) {
			System.out.println("You do not have enough money in your account to withdraw " + amount + " dollars.");
		}
		if (amount + dailyWithdrawnAmount > settings.getSpendingLimit()) {
			System.out.println("This withdrawal would put you over your daily spending limit of " + settings.getSpendingLimit() + "$");
		}
		else {
			this.balance -= amount;
			dailyWithdrawnAmount += amount;
			transactionHistory.push(transactionCounter + ". Withdrew: $" + amount + ". Account Balance is: $" + this.balance);
			transactionCounter++;
			System.out.println("Withdrawal Successful!");
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
