package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.AccountSettings;

public class AccountSettingsTests {
	private AccountSettings settings;
	
	@BeforeEach
	void setUp() {
		settings = new AccountSettings();
	}
	
	@Test
	public void testDefaultThresholdValue() {
		assertEquals(50.0, settings.getThreshold());
	}
	
	@Test
	public void testThresholdAcceptsPositive() {
		double expected = 100.0;
		settings.setThreshold(expected);
		assertEquals(expected, settings.getThreshold());
	}
	
	@Test
	public void testThresholdRejectsNegative() {
		settings.setThreshold(100);
		settings.setThreshold(-100);
		assertEquals(100, settings.getThreshold());
	}
	
	@Test
	public void testSpendingLimitAcceptsPositive() {
		double expected = 100.0;
		settings.setSpendingLimit(expected);
		assertEquals(expected, settings.getSpendingLimit());
	}
	
	@Test
	public void testSpendingLimitRejectsNegative() {
		settings.setSpendingLimit(100);
		settings.setSpendingLimit(-100);
		assertEquals(100, settings.getSpendingLimit());
	}
}