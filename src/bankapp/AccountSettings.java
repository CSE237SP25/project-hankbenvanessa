package bankapp;
import java.util.Scanner;

public class AccountSettings {

	private static double threshold = 50;
	private static double spendingLimit = 999999999;

	
	// UI
	public void showSettingsMenu() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("---Account Settings---");
			System.out.println("1. View and Change Overdraft Alert Threshold");
			System.out.println("2. View and Change Daily Spending Limit");
			System.out.println("0. Return to Main Menu");
			System.out.print("Choose an option:");
			
			choice = scanner.nextInt();
			
			if (choice == 1) {
				configureThreshold();
			} else if (choice == 2) {
				configureSpendingLimit();
			} else if (choice == 0) {
				System.out.println("Returning to main menu...");
				return;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		} while(choice != 0);
	}
	
	public void configureSpendingLimit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Current spending limit: $" + this.getSpendingLimit());
		System.out.print("Enter new spending limit: $");
		double newSpendingLimit = scanner.nextDouble();
		setSpendingLimit(newSpendingLimit);
	}
	
	// IF USER CHOOSES THRESHOLD OPTION
	
	// set overdraft threshold
	public void setThreshold(double thresholdAmount) {
		if (thresholdAmount <= 0) {
			System.out.println("Threshold must be a positive number.");
			return;
		} else {
			threshold = thresholdAmount;
			System.out.println("Updated threshold amount: $" + thresholdAmount);
		}
	}
	
	public void setSpendingLimit(double amount) {
		if (amount <= 0) {
			System.out.println("The spending limit must be a positive number.");
			return;
		} else {
			spendingLimit = amount;
			System.out.println("Updated spending limit amount: $" + spendingLimit);
		}
	}
	
	// views current overdraft threshold
	public double getThreshold() {
		return threshold;
	}
	
	public double getSpendingLimit() {
		return spendingLimit;
	}
	
	// for UI; if user chooses to set threshold
	public void configureThreshold() {
		Scanner scanner = new Scanner(System.in);
		// current threshold
		System.out.println("Current overdraft threshold: $" + this.getThreshold());
		System.out.print("Enter new threshold amount: $");
		// update threshold
		double newThreshold = scanner.nextDouble();
		setThreshold(newThreshold);
		// comfirm update
		
	}
	// END OF THRESHOLD OPTION
	
	//... add more settings here: 
	
	
}
