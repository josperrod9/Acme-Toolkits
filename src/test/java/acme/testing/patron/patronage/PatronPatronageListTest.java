package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness {
	

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationDate, final String startDate, 
		final String endDate, final String info, final String inventor, final String patron, final String inventorCompany,
		final String inventorStatement, final String inventorInfo) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		super.checkColumnHasValue(recordIndex, 3, creationDate);
		super.checkColumnHasValue(recordIndex, 4, startDate);
		super.checkColumnHasValue(recordIndex, 5, endDate);
		super.checkColumnHasValue(recordIndex, 6, budget);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		
		super.checkInputBoxHasValue("inventor.userAccount.username", inventor);
		super.checkInputBoxHasValue("inventor.company", inventorCompany);
    	super.checkInputBoxHasValue("inventor.statement", inventorStatement);
    	super.checkInputBoxHasValue("inventor.inventorInfo", inventorInfo);
    	
    	
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/listreport.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTest1(final int recordIndex,final int recordIndexReport, final String creationMoment,
		final String memorandum, final String information, final String automaticSequenceNumber) {
		super.signIn("patron3", "patron3");
		super.clickOnMenu("Patron", "Patronages");
		
		super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkButtonExists("Reports");
        super.clickOnButton("Reports");
        super.checkListingExists();
        super.checkColumnHasValue(recordIndexReport, 0, creationMoment);
		super.clickOnListingRecord(recordIndexReport);
		super.checkFormExists();
        super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
        super.checkInputBoxHasValue("creationMoment", creationMoment);
        super.checkInputBoxHasValue("memorandum", memorandum);
        super.checkInputBoxHasValue("info", information);
        super.signOut();
	}
	
}