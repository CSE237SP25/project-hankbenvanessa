package tests;
import static org.junit.Assert.assertEquals;
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
		assertEquals(50.0, settings.getThreshold(), "Default value should equal 50");
	}
	
	@Test
	public void testThresholdAcceptsPositive() {
		double expected = 100.0;
		settings.setThreshold(expected);
		assertEquals(expected, settings.getThreshold(), "Threshold accepts valid entries");
	}
	
	@Test
	public void testThresholdRejectsNegative() {
		settings.setThreshold(100);
		settings.setThreshold(-100);
		assertEquals(100, settings.getThreshold(), "Threshold must be positive");
	}


}
