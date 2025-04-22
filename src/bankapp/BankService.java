package bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class BankService {
	
	
	protected String currentUser;
	protected int currentBankAccountID;
	protected BankAccount theAccount;
	protected Map<String, UserAccount> userAccounts;
	protected Scanner in;
	protected AccountSettings settings;
	
	public BankService() {
		currentUser = "";
		currentBankAccountID = 1;
		theAccount = new BankAccount();
		userAccounts = new HashMap<>();
		in = new Scanner(System.in);
		settings = new AccountSettings();
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
	
	//Does not need to be tested
	public double getUserInputDouble() {
		double doubleFromUser = in.nextDouble();
		return doubleFromUser;
	}
	
	public double getCurrentBalance() {
		if (userAccounts.containsKey(currentUser)) {
			return userAccounts.get(currentUser).getAccountBalance(currentBankAccountID);
		}
		else {
			return theAccount.getCurrentBalance();
		}
	}
	
	public int getNumberOfAccounts() {
		return userAccounts.size();
	}
	
	public String getCurrentUser() {
		return this.currentUser;
	}
	
	public String getRandomBankingTip() {
	    String[] tips = {
	        "Set a monthly budget and stick to it.",
	        "Track your spending to avoid surprises.",
	        "Avoid ATM fees by using your bank’s network.",
	        "Save at least 10% of every paycheck.",
	        "Use strong passwords for your online accounts.",
	        "Review your statements regularly for fraud.",
	        "Build an emergency fund with 3-6 months of expenses.",
	        "Pay your credit card balance in full each month.",
	        "Set up automatic bill payments to avoid late fees.",
	        "Check your credit report once a year for free."
	    };

	    int randomIndex = (int)(Math.random() * tips.length);
	    return tips[randomIndex];
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
	
	public String getValidUsername() {
		String username = "";
		boolean isUsernameValid = false;
		while (!isUsernameValid) {
			System.out.println("Please enter the username for an existing account or type 'quit' to quit:");
			username = getUserInputString();
			if (username.equals("quit")) {
				return "quit";
			}
			if (userAccounts.containsKey(username)) {
				isUsernameValid = true;
			}
			else {
				System.out.println("Invalid Username. Please try again.");
			}
		}
		
		return username;
	}
	
	public void makeDeposit(double depositAmount) {
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to make a deposit");
			return;
		}
		UserAccount account = userAccounts.get(currentUser);
		boolean wasDepositSuccessful = account.accountDeposit(depositAmount, currentBankAccountID);
		if (wasDepositSuccessful) {
			System.out.println("Deposit Successful!");
		}
		else {
			System.out.println("There was a problem with your deposit, please try again later.");
		}
	}
	
	public void makeWithdrawal(double withdrawAmount) {
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to make a withdrawal");
			return;
		}
		UserAccount account = userAccounts.get(currentUser);
		boolean wasWithdrawSuccessful = account.accountWithdrawal(withdrawAmount, currentBankAccountID);
		if (wasWithdrawSuccessful) {
			System.out.println("Withdrawal Successful!");
		}
		else {
			System.out.println("There was a problem with your withdrawal, please try again later.");
		}
	}
	
	public void changeBankAccount(int newBankAccountID) {
		UserAccount account = userAccounts.get(currentUser);
		int maxBankAccountID = account.getMaxBankAccountID();
		if (newBankAccountID < 1 || newBankAccountID > maxBankAccountID) {
			System.out.println("Sorry, you did not enter in an apropriate integer");
			
		}
		else {
			this.currentBankAccountID = newBankAccountID;
			System.out.println("Current bank account: Bank Account " + newBankAccountID);
		}
	}
	
	// Asks the user for a user name and asks them to confirm it
		public String getNewUsernameFromUser() {
			
			// 	Asks for the user to verify their username.
			// 	If they type 'no', reprompts them until they enter a choice
			// and verify it or until they type 'quit'
			String potentialUsername = "";
			boolean isUsernameCorrect = false;
			while (!isUsernameCorrect) {
				System.out.println("Please enter the username for your account or type 'quit' to quit:");
				potentialUsername = getUserInputString();
				if (potentialUsername == "quit") {
					return "quit";
				}
				System.out.println("You entered: '" + potentialUsername + "', is this correct?");
				boolean yesOrNoResponse = getUserYesOrNo();
				if (yesOrNoResponse) {
					if (checkIfUsernameAvailable(potentialUsername)) {
						isUsernameCorrect = true;
					}
					else {
						System.out.println("Sorry, " + potentialUsername + "is already taken");		
					}
				}
			}
			System.out.println("You confirmed username '" + potentialUsername + "'");
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
					newPassword = secondPasswordEntry;
				}
			}
			System.out.println("Password Selected!");
			return newPassword;
		}
		
		// returns true if account creation is successful
		// returns false if there is already an account with that username
		public boolean createUserAccount(String username, String password) {
			if (checkIfUsernameAvailable(username)) {
				UserAccount newAccount = new UserAccount(username, password);
				userAccounts.put(username, newAccount);
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean userLogIn(String username, String password) {
			if (username == "") {
				System.out.println("No username was entered in.");
			}
			//check if username is in the userAccounts map
			if (userAccounts.containsKey(username)) {
				UserAccount account = userAccounts.get(username);
				if (account.checkPassword(password)) {
					this.currentUser = username;
					System.out.println("You are now logged in as '" + this.currentUser + "'");
					return true;
				}
				else {
					System.out.println("Password is Incorrect.");
					return false;
				}
			}
			// username is not in the userAccounts map 
			else {
				System.out.println("An account with this username does not exist.");
				return false;
			}
		}
		
		public double getTransferAmount() {
			boolean isAmountValid = false;
			double transferAmount = 0;
			while(!isAmountValid) {
				System.out.println("Please enter the amount you would like to transfer: ");
				transferAmount = getUserInputDouble();
				if (transferAmount >= 0 && transferAmount <= getCurrentBalance()) {
					isAmountValid = true;
				}
				else {
					System.out.println("You entered an invalid amount, please try again");
				}
			}
			return transferAmount;
		}
	
	
}
