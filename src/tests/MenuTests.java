package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

public class MenuTests {
	
	@Test
	public void testUserDeposit() {
		Menu m = new Menu();

		m.processUserInput("d", 25.0);
		
		BankAccount account = m.getAccount();
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testUserWithdrawal() {
		Menu m = new Menu();

		m.processUserInput("d", 25.0);
		m.processUserInput("w", 10.0);
		
		BankAccount account = m.getAccount();
		assertEquals(account.getCurrentBalance(), 15.0, 0.005);
	}
}
