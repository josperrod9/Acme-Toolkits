package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedConfigurationTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/configuration/list-configuration.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String defaultCurrency, final String currency) {

		super.signIn("inventor2","inventor2");
		
		super.clickOnMenu("Authenticated User", "Configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("currency", currency);
		
		}
	}

