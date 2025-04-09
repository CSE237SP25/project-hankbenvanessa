package bankapp;
import java.util.Scanner;

public class AccountSettings {

	private double threshold = 50;

	
	// UI
	public void showSettingsMenu() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("---Account Settings---");
			System.out.println("1. View and Change Overdraft Alert Threshold");
			System.out.println("0. Return to Main Menu");
			System.out.print("Choose an option:");
			
			choice = scanner.nextInt();
			
			if (choice == 1) {
				configureThreshold();
			} else if (choice == 0) {
				System.out.println("Returning to main menu...");
				return;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		} while(choice != 0);
	}
	
	// IF USER CHOOSES THRESHOLD OPTION
	
	// set overdraft threshold
	public void setThreshold(double thresholdAmount) {
		if (thresholdAmount <= 0) {
			System.out.println("Threshold must be a positive number.");
			return;
		} else {
		this.threshold = thresholdAmount;
		}
	}
	
	// views current overdraft threshold
	public double getThreshold() {
		return this.threshold;
	}
	
	// for UI; if user chooses to set threshold
	public void configureThreshold() {
		Scanner scanner = new Scanner(System.in);
		// current threshold
		System.out.println("Current overdraft threshold: $" + this.getThreshold());
		System.out.print("Enter new threshold amount: $");
		// update threshold
		double newThreshold = scanner.nextDouble();
		this.setThreshold(newThreshold);
		// comfirm update
		System.out.println("Updated threshold amount: $" + newThreshold);
	}
	// END OF THRESHOLD OPTION
	
	//... add more settings here: 
	
}