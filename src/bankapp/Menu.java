package bankapp;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Menu extends BankService {
	
	public Menu() {
		super();
	}

	// <<< METHODS FOR UserAccount UI 
	
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
		System.out.println("Enter '9' to see your account settings: ");
		System.out.println("Enter '10' to make a Wire Transfer: ");
		System.out.println("Enter '11' to give us feedback!");
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
		if (intFromUser == 9) {
			settings.showSettingsMenu();
		}
		if (intFromUser == 10) {
			processUserTransfer();
		}
		if (intFromUser == 11) {
			processAppFeedback();
		}
	}
	
	public void processUserCreateAnAccount() {
		System.out.println("--------------------------------------");
		System.out.println("You chose Option 1: Create an account");
		// prompts the user for a user name until they either choose one that is available or type 'quit'
		String newUsername = getNewUsernameFromUser();
		if (newUsername == "quit") {
			System.out.println("You entered 'quit'");
			return;
		}
		String newPassword = getNewPassword();
		// adds an account to the userAccounts map with user's username and password
		boolean createAccountSuccessful = createUserAccount(newUsername, newPassword);
		if (createAccountSuccessful) {
			System.out.println("Your account has been created!");
			System.out.println("Please pick an answer to this security question: " + getRandomSecurityQuestion() + "?");
			getNewSecurityAnswer();
		}
		else {
			System.out.println("Sorry, there was an error while creating your account. Please try again!");
		}
	}
	
	public void processUserLogIn() {
		System.out.println("You chose option 2: log in to an existing account.");
		String username = getValidUsername();
		if (username.equals("quit")) {
			return;
		}
		// username is in the stored accounts
//		System.out.println("Please enter the password associated with your account:");
//		String password = getUserInputString();
//		boolean wasLoginSuccessful = userLogIn(username, password);
		while (loginAttempts < 3) {
			System.out.println("Please enter the password associated with your account:");
			String password = getUserInputString();
			boolean wasLoginSuccessful = userLogIn(username, password);
			if (!wasLoginSuccessful) {
				loginAttempts++;
			}
			else {
				return;
			}
		}
		loginAttempts = 0;
		startPasswordRecovery();
	}
	
	public void processUserDeposit() {
		System.out.println("You chose option 3: make a deposit");
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before making a deposit");
		}
		else {
			System.out.println("Please enter the amount you would like to deposit: ");
			double depositAmount = getUserInputDouble();
			makeDeposit(depositAmount);
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
			makeWithdrawal(withdrawAmount);
			
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
			theAccount.sendAlert(balance);
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
			changeBankAccount(bankAccountID);
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
	
	public void processUserTransfer() {
		if (!userAccounts.containsKey(currentUser)) {
			System.out.println("Please log in to an account before creating a new bank account");
		}
		else {
			System.out.println("You chose option 10: Wire Transfer");
			String transferAccountUsername = getValidUsername();
			if (transferAccountUsername.equals("quit")) {
				return;
			}
			double transferAmount = getTransferAmount();
			wireTransfer(transferAccountUsername, transferAmount);
		}
	}
	
	
	public boolean wireTransfer(String transferAccountUsername, double transferAmount) {
		if (userAccounts.containsKey(transferAccountUsername)) {
			if (transferAmount < 0 || transferAmount > getCurrentBalance()) {
				System.out.println("Sorry, the transfer could not be completed because\n "
						+ "you entered an invalid transfer amount");
				return false;
			}
			makeWithdrawal(transferAmount);
			UserAccount transferAccount = userAccounts.get(transferAccountUsername);
			// Bank Account Wire Transfers always go to the person's Bank Account 1 
			transferAccount.accountDeposit(transferAmount, 1);
			System.out.println("Wire Transfer of " + transferAmount + " to " + transferAccountUsername + " was successful!");
			return true;
		}
		else {
			System.out.println("Sorry, but the account you entered no longer exists");
			return false;
		}
		
	}
	
	public void processAppFeedback() {
		System.out.println("You chose option 11: Give feedback");
		collectUserFeedback();
	}
	
}
