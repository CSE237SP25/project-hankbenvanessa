package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.AccountSettings;

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
	
	
	@Test
	public void testSendAlertWhenBalanceBelowDefaultThreshold() {
		BankAccount account = new BankAccount();
		double currentBalance = 40;
		String expected = "ALERT: Your balance is below the threshold of $50.0";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        account.sendAlert(currentBalance);
        String actual = outContent.toString().trim();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNoSendAlertWhenBalanceAboveThreshold() {
		BankAccount account = new BankAccount();
		double currentBalance = 60;
		String expected = "";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        account.sendAlert(currentBalance);
        String actual = outContent.toString().trim();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSpendingLimitStopsWithdrawal() {
		BankAccount account = new BankAccount();
		account.settings.setSpendingLimit(20);
		account.deposit(100);
		account.withdraw(20);
		account.withdraw(5);
		assertEquals(account.getCurrentBalance(), 80.0, 0.005);
	}
	
	@Test
	public void testFrozenAccountPreventsDeposit() {
		BankAccount account = new BankAccount();
		account.freezeTransactions();
    account.deposit(50);
    assertEquals(0.0, account.getCurrentBalance(), 0.005);
  }
  
	public void testFrozenAccountPreventsWithdraw() {
		BankAccount account = new BankAccount();
		account.deposit(100);
		account.unfreezeTransactions();
    account.withdraw(50);
    assertEquals(100.0, account.getCurrentBalance(), 0.005);
  }

	@Test
	public void testBlockedCardPreventsDeposit() {
		BankAccount account = new BankAccount();
		account.reportCardLostOrStolen();
    account.deposit(50);
    assertEquals(0.0, account.getCurrentBalance(), 0.005);
  }
	
	@Test
	public void testBlockedCardPreventsWithdraw() {
		BankAccount account = new BankAccount();
		account.deposit(100);
		account.reportCardLostOrStolen();
		account.withdraw(50);
		assertEquals(100.0, account.getCurrentBalance(), 0.005);
	}
}
