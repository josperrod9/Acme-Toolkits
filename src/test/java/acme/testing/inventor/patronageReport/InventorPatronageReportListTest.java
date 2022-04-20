package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/list-patronageReport.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String memorandum, final String automaticSequenceNumber, final String creationMoment, 
		final String info) {

		super.signIn("inventor2","inventor2");
		
		super.clickOnMenu("Inventor", "My Patronage Reports");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, memorandum);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, info);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("info", info);
	}
}
