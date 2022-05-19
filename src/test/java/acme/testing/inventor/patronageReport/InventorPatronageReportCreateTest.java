package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness{

	// Lifecycle managment 
	
	// Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String memorandum, final String info) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Create patronage report");

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Patronage Reports");

		super.checkColumnHasValue(recordIndex, 0, memorandum);
		super.checkColumnHasValue(recordIndex, 2, info);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("info", info);

		super.signOut();
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	
	public void negativeTest (final int recordIndex, final String memorandum, final String info) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Create patronage report");

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		
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
