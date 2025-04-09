package bankapp;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Menu {
	
	private String currentUser;
	private int currentBankAccountID;
	private BankAccount theAccount;
	private Map<String, UserAccount> userAccounts;
	private Scanner in;
	
	public Menu() {
		currentUser = "";
		currentBankAccountID = 1;
		theAccount = new BankAccount();
		userAccounts = new HashMap<>();
		in = new Scanner(System.in);
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

	// <<< METHODS FOR UserAccount UI 
	public void accountDisplayOptions() {
		String bankingTip = getRandomBankingTip();
		System.out.println("Tip of the day: " + bankingTip);
		System.out.println("-----------------------------------------------");
		System.out.println("Enter '1' to begin creating an account: ");
		System.out.println("Enter '2' to log in to an existing account: ");
		System.out.println("Enter '3' to make a deposit: ");
		System.out.println("Enter '4' to make a withdrawal: ");
		System.out.println("Enter '5' to see your account balance: ");
    System.out.println("Enter '6' to see your transaction history: ");
		System.out.println("Enter '7' to change Bank Accounts: ");
		System.out.println("Enter '8' to create a new Bank Account: ");
	}
	
	
	public void runBankAccount() {
		
		boolean shouldStop = false;
		while(!shouldStop) {
			accountDisplayOptions();
			int menuResponse = 0;
			boolean isResponseProper = false;
			while(!isResponseProper) {
				try {
					menuResponse = getUserInputInt();
					isResponseProper = true;
				}
				catch (Exception e) {
				}
			}
			processUserAccountMenuChoice(menuResponse);
			
			
		}
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
			processUserCreateAnAccount();
		}
		if (intFromUser == 2) {
			processUserLogIn();
		}
		if (intFromUser == 3) {
			processUserDeposit();
		}
		if (intFromUser == 4) {
			processUserWithdrawal();
		}
		if (intFromUser == 5) {
			processUserGetBalance();
		}
		if (intFromUser == 6) {
			processUserGetTransactionHistory();
		}
    if (intFromUser == 7) {
			processUserChangeBankAccount();
		}
		if (intFromUser == 8) {
			processUserCreateNewBankAccount();
		}
	}
	
	public void processUserGetTransactionHistory() {
		System.out.println("You chose option 6: view transaction history");
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account to view your transaction history");
		}
		else {
			UserAccount account = userAccounts.get(currentUser);
			account.showTransactionHistory(currentBankAccountID);		
		}
	}
	
	public void processUserCreateAnAccount() {
		System.out.println("--------------------------------------");
		System.out.println("You chose Option 1: Create an account");
		boolean isUsernameAvailable = false;
		String potentialUsername = "";
		// prompts the user for a user name until they either choose one that is available or type 'quit'
		while (!isUsernameAvailable) {
			potentialUsername = getNewUsernameFromUser();
			if (potentialUsername == "quit") {
				System.out.println("You entered 'quit'");
				return;
			}
			isUsernameAvailable = checkIfUsernameAvailable(potentialUsername);
			if (!isUsernameAvailable) {
				System.out.println("Unfortunately, the username '" + potentialUsername + "' is already taken.");
			}
		}
		
		String newPassword = getNewPassword();
		// adds an account to the userAccounts map with user's username and password
		boolean createAccountSuccessful = createUserAccount(potentialUsername, newPassword);
		if (createAccountSuccessful) {
			System.out.println("Your account has been created!");
		}
	}
	
	public void processUserLogIn() {
		System.out.println("You chose option 2: log in to an existing account.");
		System.out.println("Please enter the username for your account or type 'quit' to quit:");
		String username = getUserInputString();
		if (username.equals("quit")) {
			return;
		}
		// check if username is in the stored userAccounts map
		if (!userAccounts.containsKey(username)) {
			System.out.println("The username '" + username + "' does not have an associated account, "
					+ "please try again");
			int menuChoice = 2;
			processUserAccountMenuChoice(menuChoice);
		}
		// username is in the stored accounts
		else {
			System.out.println("Please enter the password associated with your account:");
			String password = getUserInputString();
			boolean wasLoginSuccessful = userLogIn(username, password);
			if (!wasLoginSuccessful) {
				int menuChoice = 2;
				processUserAccountMenuChoice(menuChoice);
			}
		}
	}
	
	public void processUserDeposit() {
		System.out.println("You chose option 3: make a deposit");
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before making a deposit");
		}
		else {
			System.out.println("Please enter the amount you would like to deposit: ");
			double depositAmount = getUserInputDouble();
			UserAccount account = userAccounts.get(currentUser);
			boolean wasDepositSuccessful = account.accountDeposit(depositAmount, currentBankAccountID);
			if (wasDepositSuccessful) {
				System.out.println("Deposit Successful!");
			}
			else {
				System.out.println("There was a problem with your deposit, please try again later.");
			}
		}
		
	}
	
	public void processUserWithdrawal() {
		System.out.println("You chose option 4: make a withdrawal.");
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before making a withdrawal");
		}
		else {
			System.out.println("Please enter the amount you would like to withdraw: ");
			double withdrawAmount = getUserInputDouble();
			UserAccount account = userAccounts.get(currentUser);
			boolean wasWithdrawSuccessful = account.accountWithdrawal(withdrawAmount, currentBankAccountID);
			if (wasWithdrawSuccessful) {
				System.out.println("Withdrawal Successful!");
			}
			else {
				System.out.println("There was a problem with your withdrawal, please try again later.");
			}
		}
		
	}
	
	public void processUserGetBalance() {
		System.out.println("You chose option 5: see account balance.");
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before checking your balance!");
		}
		else {
			UserAccount account = userAccounts.get(currentUser);
			double balance = account.getAccountBalance(currentBankAccountID);
			System.out.println("Your balance is currently $" + balance + " dollars.");
		}
	}
	
	public void processUserChangeBankAccount() {
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before changing your Bank Account");
		}
		else {
			System.out.println("You chose option 7: change bank account.");
			UserAccount account = userAccounts.get(currentUser);
			int maxBankAccountID = account.getMaxBankAccountID();
			System.out.println("Your options are:");
			for (int i = 1; i <= maxBankAccountID; i ++) {
				System.out.println("Bank Account " + i);
			}
			System.out.println("Please enter the ID for an account");
			int bankAccountID = getUserInputInt();
			if (bankAccountID < 1 || bankAccountID > maxBankAccountID) {
				System.out.println("Sorry, you did not enter in an apropriate integer");
				
			}
			else {
				this.currentBankAccountID = bankAccountID;
			}
		}
		
	}
	
	public void processUserCreateNewBankAccount() {
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before creating a new bank account");
		}
		else {
			System.out.println("You chose option 8: create new bank account.");
			UserAccount account = userAccounts.get(currentUser);
			account.createBankAccount();
			System.out.println("Creation Successful!");
		}
	}
	
	// Asks the user for a user name and asks them to confirm it
	public String getNewUsernameFromUser() {
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
	
	public int getNumberOfAccounts() {
		return userAccounts.size();
	}
	
	// METHODS FOR UserAccount UI >>>
	
	// <<< METHODS FOR UserLogIn UI
	public String getCurrentUser() {
		return this.currentUser;
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
	
}
