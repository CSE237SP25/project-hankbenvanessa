package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.BankService;
import bankapp.Menu;

public class MenuTests {
	
	@Test
	public void testUserDeposit() {
		Menu m = new Menu();

		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.makeDeposit(25.0);
		
		double currentBalance = m.getCurrentBalance();
		assertEquals(25.0, currentBalance, 0.005);
	}
	
	@Test
	public void testUserWithdrawal() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		
		m.makeDeposit(25.0);
		m.makeWithdrawal(10.0);
		
		double currentBalance = m.getCurrentBalance();
		assertEquals(15.0, currentBalance, 0.005);
	}
	
	@Test
	public void testUserTransferBasic() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.createUserAccount("user2", "pass");
		m.userLogIn("user1", "pass");
		
		m.makeDeposit(100);
		m.wireTransfer("user2", 50);
		
		double currentBalanceUser1 = m.getCurrentBalance();
		m.userLogIn("user2", "pass");
		double currentBalanceUser2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceUser1, 0.001);
		assertEquals(50.0, currentBalanceUser2, 0.001);
	}
	
	@Test
	public void testUserTransferInvalidInput() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.createUserAccount("user2", "pass");
		m.userLogIn("user1", "pass");
		
		m.makeDeposit(50);
		m.wireTransfer("user2", 100);
		
		double currentBalanceUser1 = m.getCurrentBalance();
		m.userLogIn("user2", "pass");
		double currentBalanceUser2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceUser1, 0.001);
		assertEquals(0, currentBalanceUser2, 0.001);
	}
	
}
