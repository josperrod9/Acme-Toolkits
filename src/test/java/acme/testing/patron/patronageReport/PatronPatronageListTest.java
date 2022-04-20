package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.entities.patronages.Status;
import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness {
	

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final Status status, final String code, 
		final String legalStuff, final String budget, final String creationDate, final String startDate, 
		final String endDate, final String info, final String inventor, final String patron, final String inventorCompany,
		final String inventorStatement, final String inventorInfo) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		//super.checkColumnHasValue(recordIndex, 0, status.toString());
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, budget);
		super.checkColumnHasValue(recordIndex, 4, creationDate);
		super.checkColumnHasValue(recordIndex, 5, startDate);
		super.checkColumnHasValue(recordIndex, 6, endDate);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		//super.checkInputBoxHasValue("status", status.toString());
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		
		super.checkInputBoxHasValue("username", inventor);
		super.checkInputBoxHasValue("company", inventorCompany);
    	super.checkInputBoxHasValue("statement", inventorStatement);
    	super.checkInputBoxHasValue("inventorInfo", inventorInfo);
		super.signOut();
	}
}