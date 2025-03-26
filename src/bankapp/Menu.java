package bankapp;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Menu {
	
	private BankAccount theAccount;
	private Map<String, UserAccount> userAccounts;
	private Scanner in;
	
	public Menu() {
		theAccount = new BankAccount();
		userAccounts = new HashMap<>();
		in = new Scanner(System.in);
	}
	
	// display methods don't need to be tested
	public void displayOptions() {
		System.out.println("Please enter an amount to be deposited:");
		
	}
	
	//methods that require user input (Scanner) don't need to be tested
	public double getUserInput() {
		Scanner keyboardInput = new Scanner(System.in);
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}
	
	//Can and should test methods that process user input once it is received 
	public void processUserInput(double amount) {
		theAccount.deposit(amount);
	}
	
	public BankAccount getAccount() {
		return theAccount;
	}
	
	// <<< METHODS FOR UserAccount UI 
	public void accountDisplayOptions() {
		System.out.println("Enter '1' to begin creating an account: ");
	}
	
	// Does not need to be tested
	public int getUserInputInt() {
		//Scanner in = new Scanner(System.in);
		int intFromUser = in.nextInt();
		return intFromUser;
	}
	// Does not need to be tested
	public String getUserInputString() {
		//Scanner in = new Scanner(System.in);
		String stringFromUser = in.next();
		return stringFromUser;
	}
	
	// returns true for a Yes and false for a No from the user
	public boolean getUserYesOrNo() {
		System.out.println("Please enter 'yes' for yes and 'no' for no");
		String stringFromUser = getUserInputString();
		boolean isYesOrNo = false;
		while (!isYesOrNo) {
			if (stringFromUser.equals("Yes") || stringFromUser.equals("yes")) {
				return true;
			}
			else if (stringFromUser.equals("No") || stringFromUser.equals("no")) {
				return false;
			}
			else {
				System.out.println("Input is incorrect. Please enter 'yes' for yes or 'no' for no:");
				stringFromUser = getUserInputString();
			}
		}
		return false;
	}
	
	public void processUserAccountMenuChoice(int intFromUser) {
		if (intFromUser == 1) {
			System.out.println("You chose Option 1: Create an account");
			boolean isUsernameAvailable = false;
			String potentialUsername = "";
			// prompts the user for a user name until they either choose one
			// that is available or type 'quit'
			while (!isUsernameAvailable) {
				potentialUsername = getUsernameFromUser();
				if (potentialUsername == "quit") {
					return;
				}
				isUsernameAvailable = checkIfUsernameAvailable(potentialUsername);
			}
			
			String newPassword = getNewPassword();
			// adds an account to the userAccounts map with user's
			// username and password
			boolean createAccountSuccessful = createAUserAccount(potentialUsername, newPassword);
			if (createAccountSuccessful) {
				System.out.println("Your account has been created!");
			}
			
		}
	}
	
	
	// Asks the user for a user name and asks them to confirm it
	public String getUsernameFromUser() {
		System.out.println("Please enter the username for your account or type 'quit' to quit:");
		String potentialUsername = getUserInputString();
		if (potentialUsername == "quit") {
			return "quit";
		}
		
		
		// 	Asks for the user to verify their username.
		// 	If they type 'no', reprompts them until they enter a choice
		// and verify it or until they type 'quit'
		boolean isUsernameCorrect = false;
		while (!isUsernameCorrect) {
			System.out.println("You entered: '" + potentialUsername + "', is this correct?");
			boolean yesOrNoResponse = getUserYesOrNo();
			if (yesOrNoResponse) {
				isUsernameCorrect = true;
			}
			else {
				System.out.println("Please enter the username for your account or type 'quit' to quit:");
				potentialUsername = getUserInputString();
				if (potentialUsername == "quit") {
					return "quit";
				}
			}
		}
		return potentialUsername;
	}
	
	public boolean checkIfUsernameAvailable(String username) {
		return !userAccounts.containsKey(username);
	}
	
	public String getNewPassword() {
		String newPassword = "";
		
		// Prompts for two passwords until they match
		boolean doPasswordsMatch = false;
		while(!doPasswordsMatch) {
			System.out.println("Please enter the new password for your account");
			String firstPasswordEntry = getUserInputString();
			System.out.println("Please enter the new password for again");
			String secondPasswordEntry = getUserInputString();
			if (!firstPasswordEntry.equals(secondPasswordEntry)) {
				System.out.println("Your passwords do not match. Please try again.");
			}
			else {
				doPasswordsMatch = true;
			}
		}
		return newPassword;
	}
	// returns true if account creation is successful
	// returns false if there is already an account with that username
	public boolean createAUserAccount(String username, String password) {
		if (checkIfUsernameAvailable(username)) {
			UserAccount newAccount = new UserAccount(username, password);
			userAccounts.put(username, newAccount);
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getNumberOfAccounts() {
		return userAccounts.size();
	}
	
	// METHODS FOR UserAccount UI >>>
	
}
