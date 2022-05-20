package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorCreateTest extends TestHarness{
	
	// Lifecycle managment 
	
	// Test cases
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String title, final String body, final String critical, final String info,final String criticalForm) {
		
		String confirmation;
		confirmation = "true";
		
		super.signIn("administrator", "administrator");
	
		super.clickOnMenu("Administrator", "Create an announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", confirmation);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Authenticated User", "List Announcement");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 2, critical);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", criticalForm);
		super.checkInputBoxHasValue("info", info);

		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String body, final String critical, final String info) {

		String confirmation;
		confirmation = "true";
		
		super.signIn("administrator", "administrator");
	
		super.clickOnMenu("Administrator", "Create an announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", confirmation);
		
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
	//hacking tests cannot be easily implemented with the current version of the framework,
    //but they can be documented and conducted manually to ensure that there aren’t any trivial hacking problems
	}
}
