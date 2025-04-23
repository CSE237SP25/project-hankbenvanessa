package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
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
	public void testUserWireTransferBasic() {
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
	public void testUserWireTransferInvalidInput() {
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
	
	@Test 
	public void testUserTransfer() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		
		m.makeDeposit(50);
		m.bankAccountTransfer(25, 1, 2);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(25.0, currentBalanceBankAccount1, 0.001);
		assertEquals(25.0, currentBalanceBankAccount2, 0.001);
		
	}
	
	@Test 
	public void testUserTransferInvalidAmount() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		
		m.makeDeposit(50);
		m.bankAccountTransfer(100, 1, 2);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceBankAccount1, 0.001);
		assertEquals(0.0, currentBalanceBankAccount2, 0.001);
		
	}
	
	@Test 
	public void testUserTransferInvalidAmountNegative() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		
		m.makeDeposit(50);
		m.bankAccountTransfer(-10, 1, 2);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceBankAccount1, 0.001);
		assertEquals(0.0, currentBalanceBankAccount2, 0.001);
		
	}
	
	public void testUserTransferInvalidAccount() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		
		m.makeDeposit(50);
		m.bankAccountTransfer(25, 1, 3);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceBankAccount1, 0.001);
		assertEquals(0.0, currentBalanceBankAccount2, 0.001);
		
	}
	
	public void testUserTransferInvalidAccountNegative() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		
		m.makeDeposit(50);
		m.bankAccountTransfer(25, 1, -1);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceBankAccount1, 0.001);
		assertEquals(0.0, currentBalanceBankAccount2, 0.001);
		
	}
	
	public void testUserTransferFromBankAccountDifferentThanCurrentBankAccount() {
		Menu m = new Menu();
		
		m.createUserAccount("user1", "pass");
		m.userLogIn("user1", "pass");
		m.processUserCreateNewBankAccount();
		m.makeDeposit(50);
		m.changeBankAccount(2);
		
		
		m.bankAccountTransfer(25.0, 1, 2);
		
		double currentBalanceBankAccount1 = m.getCurrentBalance();
		m.changeBankAccount(2);
		double currentBalanceBankAccount2 = m.getCurrentBalance();
		
		assertEquals(50.0, currentBalanceBankAccount1, 0.001);
		assertEquals(0.0, currentBalanceBankAccount2, 0.001);
		
	}
}
