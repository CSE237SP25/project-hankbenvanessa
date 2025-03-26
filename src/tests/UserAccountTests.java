package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import bankapp.Menu;

class UserAccountTests {
	
	// FROM GEEKSFORGEEKS
	private InputStream originalSystemIn = System.in;
	
	@AfterEach
	public void restoreSystemIn() {
		// Restore System.in after each test
		System.setIn(originalSystemIn);
	}
	// END OF FROM GEEKSFORGEEKS
	
	
	@Test
	void getYesOrNoTest1() {
		//1. Set up variables
		String yes = "yes";
		String simulatedInput = yes;
		
		ByteArrayInputStream byteSimulatedInput = 
			new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(byteSimulatedInput);
		
		// It is crucial to create the menu after we do System.setIn()
				Menu m = new Menu();
		// 2. 
		boolean userYesOrNoResponse = m.getUserYesOrNo();
		
		//3. 
		assertEquals(true, userYesOrNoResponse);
	}
	
	@Test
	void getYesOrNoTest2() {
		//1. Set up variables
		String no = "no";
		String simulatedInput = no;
		
		ByteArrayInputStream byteSimulatedInput = 
			new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(byteSimulatedInput);
		
		// It is crucial to create the menu after we do System.setIn()
				Menu m = new Menu();
		// 2. 
		boolean userYesOrNoResponse = m.getUserYesOrNo();
		
		//3. 
		assertEquals(false, userYesOrNoResponse);
	}
	
	
	@Test
	void testGetUsernameFromUser1() {
		//1. Set up variables
		String usernameForAccount = "user1";
		//			- Is this Correct?
		String isThisCorrectResponse = "yes";
		
		String simulatedInput = usernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse;
						
		ByteArrayInputStream byteSimulatedInput = 
		   new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();

		// 2. 
		String finalUsername = m.getUsernameFromUser();
		
		//3.
		assertEquals(true, finalUsername.equals(usernameForAccount));
		
	}
	
	@Test
	//want to test that the user can enter in a username, decide not to verify it,
	// then enter a new username, verify it, and that afterwards the account username
	// is properly saved
	void testGetUsernameFromUser2() {
		//1. Set up variables
		String firstUsernameForAccount = "user1";
		//			- Is this Correct?
		String isThisCorrectResponse = "no";
		String secondUsernameForAccount = "user2";
		//			- Is this Correct?
		String isThisCorrectResponse2 = "yes";
		
		String simulatedInput = firstUsernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse + System.lineSeparator()
								+ secondUsernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse2 + System.lineSeparator();

		ByteArrayInputStream byteSimulatedInput = 
		   new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();

		// 2. 
		String finalUsername = m.getUsernameFromUser();
		
		//3.
		assertEquals(true, finalUsername.equals(secondUsernameForAccount));
		
	}
	
	@Test
	void testGetNewPassword1() {
		//1. Set up variables
		String firstPasswordEntry = "pass1";
		String secondPasswordEntry = "pass1";
		
		String simulatedInput = firstPasswordEntry + System.lineSeparator()
								+ secondPasswordEntry;
						
		ByteArrayInputStream byteSimulatedInput = 
		   new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();

		// 2. 
		String finalPassword = m.getNewPassword();
		boolean isFinalPasswordCorrect = finalPassword.equals(firstPasswordEntry);
		//3.
		assertTrue(isFinalPasswordCorrect);
		
	}
	
	@Test
	void testGetNewPassword2() {
		//1. Set up variables
		String firstPasswordEntry = "pass1";
		String secondPasswordEntry = "pass2";
		
		String simulatedInput = firstPasswordEntry + System.lineSeparator()
								+ secondPasswordEntry + System.lineSeparator()
								+ secondPasswordEntry + System.lineSeparator()
								+ secondPasswordEntry;
						
		ByteArrayInputStream byteSimulatedInput = 
		   new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();

		// 2. 
		String finalPassword = m.getNewPassword();
		
		//3.
		assertEquals(true, finalPassword.equals(secondPasswordEntry));
		
	}
	
	@Test
	void createUserAccountTest1() {
		//1. Create Variables
		Menu m = new Menu();
		int numOfAccountsBefore = m.getNumberOfAccounts();
		
		//2. Do the test
		m.createUserAccount("user1", "pass1");
		int numOfAccountsAfter = m.getNumberOfAccounts();
		
		//3. Assert Equals
		int difference = numOfAccountsAfter - numOfAccountsBefore;
		assertEquals(1, difference);
	}
	
	@Test
	// Test that creating an account with a username that already has an 
	// account doesn't work
	void createUserAccountTest2() {
		//1. Create Variables
		Menu m = new Menu();
		m.createUserAccount("user1", "pass1");
		int numOfAccountsBefore = m.getNumberOfAccounts();
		
		//2. Do the test
		m.createUserAccount("user1", "pass2");
		int numOfAccountsAfter = m.getNumberOfAccounts();
		
		//3. Assert Equals
		int difference = numOfAccountsAfter - numOfAccountsBefore;
		assertEquals(0, difference);
	}
	
	@Test
	void checkIfUsernameAvailableTest1() {
		//1. Create Variables
		Menu m = new Menu();
		
		//2. Do the test
		boolean isAccountNameAvailable = m.checkIfUsernameAvailable("user1");
		
		//3. Assert Equals
		
		assertEquals(true, isAccountNameAvailable);
	}
	
	@Test
	void checkIfUsernameAvailableTest2() {
		//1. Create Variables
		Menu m = new Menu();
		m.createUserAccount("user1", "pass1");
		
		//2. Do the test
		boolean isAccountNameAvailable = m.checkIfUsernameAvailable("user1");
		
		//3. Assert Equals
		
		assertEquals(false, isAccountNameAvailable);
	}
	
	
	@Test
	void fullProcessUserAccountMenuChoiceTest1() {
		//1. Set up variables
		
		// User Account Inputs
		//   - Account Display Options
		String accountDisplayAnswer = "1";
		//   - Process User Account Menu Choice
		//      - Enter Username For Account
		String usernameForAccount = "user1";
		//			- Is this Correct?
		String isThisCorrectResponse = "yes";
		//	 	- Get New Password
		String newPassword = "pass1";
		// 			- Verify Password
		String passwordVerification = "pass1";
		
		
		String simulatedInput = accountDisplayAnswer + System.lineSeparator()
								+ usernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse + System.lineSeparator()
								+ newPassword + System.lineSeparator()
								+ passwordVerification + System.lineSeparator();
		
		ByteArrayInputStream byteSimulatedInput = 
				new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();
		
		// 2. 
		int firstNumOfAccounts = m.getNumberOfAccounts();
		m.accountDisplayOptions();
		int menuResponse = m.getUserInputInt();
		
		m.processUserAccountMenuChoice(menuResponse);
		
		//3. 
		int secondNumOfAccounts = m.getNumberOfAccounts();
		int difference = secondNumOfAccounts - firstNumOfAccounts;
		System.out.println("--End fullProcessUserAccountMenuChoiceTest1--");
		assertEquals(1, difference);
	}
	

	@Test
	// testing if a user can successfully deny their first username entry
	void fullProcessUserAccountMenuChoiceTest2() {
		//1. Set up variables
		
		// User Account Inputs
		//   - Account Display Options
		String accountDisplayAnswer = "1";
		//   - Process User Account Menu Choice
		//      - Enter Username For Account
		String usernameForAccount = "user1";
		//			- Is this Correct?
		String isThisCorrectResponse = "no";
		//  - Enter Username For Account
		String usernameForAccount2 = "user2";
		//			- Is this Correct?
		String isThisCorrectResponse2 = "yes";
		//	 	- Get New Password
		String newPassword = "pass1";
		// 			- Verify Password
		String passwordVerification = "pass1";
		
		
		String simulatedInput = accountDisplayAnswer + System.lineSeparator()
								+ usernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse + System.lineSeparator()
								+ usernameForAccount2 + System.lineSeparator()
								+ isThisCorrectResponse2 + System.lineSeparator()
								+ newPassword + System.lineSeparator()
								+ passwordVerification + System.lineSeparator();
		
		ByteArrayInputStream byteSimulatedInput = 
				new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();
		
		// 2. 
		int firstNumOfAccounts = m.getNumberOfAccounts();
		m.accountDisplayOptions();
		int menuResponse = m.getUserInputInt();
		
		m.processUserAccountMenuChoice(menuResponse);
		
		//3. 
		int secondNumOfAccounts = m.getNumberOfAccounts();
		int difference = secondNumOfAccounts - firstNumOfAccounts;
		System.out.println("--End fullProcessUserAccountMenuChoiceTest2--");
		assertEquals(1, difference);
	}
	
	@Test
	// make sure that a user can make a mistake on the password
	void fullProcessUserAccountMenuChoiceTest3() {
		//1. Set up variables
		
		// User Account Inputs
		//   - Account Display Options
		String accountDisplayAnswer = "1";
		//   - Process User Account Menu Choice
		//      - Enter Username For Account
		String usernameForAccount = "user1";
		//			- Is this Correct?
		String isThisCorrectResponse = "yes";
		//	 	- Get New Password
		String newPassword = "pass1";
		// 			- Verify Password
		String passwordVerification = "pass2";
		//	 	- Get New Password
		String newPassword2 = "pass3";
		// 			- Verify Password
		String passwordVerification2 = "pass3";
		
		
		String simulatedInput = accountDisplayAnswer + System.lineSeparator()
								+ usernameForAccount + System.lineSeparator()
								+ isThisCorrectResponse + System.lineSeparator()
								+ newPassword + System.lineSeparator()
								+ passwordVerification + System.lineSeparator()
								+ newPassword2 + System.lineSeparator()
								+ passwordVerification2 + System.lineSeparator();
		
		ByteArrayInputStream byteSimulatedInput = 
				new ByteArrayInputStream(simulatedInput.getBytes());
		
		System.setIn(byteSimulatedInput);
		// It is crucial to create the menu after we do System.setIn()
		Menu m = new Menu();
		
		// 2. 
		int firstNumOfAccounts = m.getNumberOfAccounts();
		m.accountDisplayOptions();
		int menuResponse = m.getUserInputInt();
		
		m.processUserAccountMenuChoice(menuResponse);
		
		//3. 
		int secondNumOfAccounts = m.getNumberOfAccounts();
		int difference = secondNumOfAccounts - firstNumOfAccounts;
		System.out.println("--End fullProcessUserAccountMenuChoiceTest3--");
		
		assertEquals(1, difference);
	}



}
