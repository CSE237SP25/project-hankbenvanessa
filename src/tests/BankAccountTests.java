package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

public class BankAccountTests {

	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
		
		//2. Call the method being tested
		account.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		BankAccount account = new BankAccount();

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test
	public void testShowTransactionHistory() {
		//Googled this to learn how to capture console output
		PrintStream originalOut = System.out;
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));
	
	        BankAccount account = new BankAccount();
	        account.deposit(25);
	        account.showTransactionHistory();

		//Googled this to learn how to capture console output
	        System.setOut(originalOut);
	        String printedOutput = outputStream.toString().trim();
	
	        String expected = "Transaction History:\n1. Deposited: $25.0. Account Balance is: $25.0";
	        assertEquals(expected, printedOutput);
    }
}
