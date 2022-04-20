package acme.testing.authenticated.announcements;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementsListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcements/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String creationMoment, final String body, 
		final String critical, final String info) {

		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("authenticated user", "List Announcement");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, critical);
		super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("creationMoment", creationMoment);
        super.checkInputBoxHasValue("body", body);
        super.checkInputBoxHasValue("critical", critical);
        super.checkInputBoxHasValue("info", info);
        
        super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
