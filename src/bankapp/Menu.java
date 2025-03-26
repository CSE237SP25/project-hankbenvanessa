package bankapp;

import java.util.Scanner;

public class Menu {
	
	private BankAccount theAccount;
	
	public Menu() {
		theAccount = new BankAccount();
	}
	
	// display methods don't need to be tested
	public void displayOptions() {
		System.out.println("Type 'd' to make a deposit or type 'w' to make a withdrawal:");
		
	}
	
	//Method to acquire the transaction type (deposit or withdrawal)
	public String getTransactionType() {
		Scanner keyboardInput = new Scanner(System.in);
		String type = keyboardInput.nextLine().trim().toLowerCase();
		return type;
	}
	
	//Method to acquire the amount of money involved in the transaction
	public double getUserInputAmount() {
		Scanner keyboardInput = new Scanner(System.in);
		System.out.println("Enter the amount you would like to deposit/withdraw:");
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}
	
	public void processUserInput(String transactionType, double amount) {
		if (transactionType.equals("d")) {
			theAccount.deposit(amount);
		} 
		else if (transactionType.equals("w")) {
			theAccount.withdraw(amount);
		} 
		else {
			System.out.println("Invalid transaction type.");
		}
	}
	
	public BankAccount getAccount() {
		return theAccount;
	}
}
