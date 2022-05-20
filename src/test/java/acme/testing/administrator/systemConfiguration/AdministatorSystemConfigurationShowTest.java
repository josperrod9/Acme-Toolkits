package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministatorSystemConfigurationShowTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/configuration/list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positive(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, 
			final String strongSpamWords, final String strongSpamThreshold, 
			final String weakSpamWords, final String weakSpamThreshold) {
			
			super.signIn("administrator", "administrator");

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

		// Ancillary methods ------------------------------------------------------


}