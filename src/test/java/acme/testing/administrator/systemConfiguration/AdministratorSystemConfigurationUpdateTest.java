package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{
	
	// Lifecycle managment 
	
	// Test cases
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String systemCurrency, final String acceptedCurrencies, 
		final String strongSpamWords, final String strongSpamThreshold, 
		final String weakSpamWords, final String weakSpamThreshold) {
				
		super.signIn("administrator", "administrator");
	
		super.clickOnMenu("Administrator", "Show system configuration");
		super.checkFormExists();
		super.fillInputBoxIn("defaultCurrency", systemCurrency);
		super.fillInputBoxIn("currency", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerm", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerm", weakSpamWords);	
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update Configuration");
		super.clickOnMenu("Administrator", "Show system configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("defaultCurrency", systemCurrency);
		super.checkInputBoxHasValue("currency", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerm", strongSpamWords);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerm", weakSpamWords);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		
		
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest (final int recordIndex, final String systemCurrency, final String acceptedCurrencies, 
		final String strongSpamWords, final String strongSpamThreshold, 
		final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Show system configuration");
		
		super.checkFormExists();
		super.fillInputBoxIn("defaultCurrency", systemCurrency);
		super.fillInputBoxIn("currency", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerm", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerm", weakSpamWords);	
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update Configuration");
		
		super.checkErrorsExist();

	
	
		
		
		super.signOut();
	}

}