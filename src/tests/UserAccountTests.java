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
	void test() {
		//1. Set up variables
		Menu m = new Menu();
		
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
		// Menu m = new Menu();
		
		// 2. 
		int firstNumOfAccounts = m.getNumberOfAccounts();
		m.accountDisplayOptions();
		int menuResponse = m.getUserInputInt();
		
		m.processUserAccountMenuChoice(menuResponse);
		
		//3. 
		int secondNumOfAccounts = m.getNumberOfAccounts();
		int difference = secondNumOfAccounts - firstNumOfAccounts;
		
		assertEquals(1, difference);
	}
	
	// Helper method to set in input of the system
	

}
