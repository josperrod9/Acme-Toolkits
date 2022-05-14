package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author, final String email, final String body) {

		super.clickOnMenu("Anonymous", "List Chirps");
		super.checkListingExists();
		super.sortListing(2, "asc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, email);
		super.checkColumnHasValue(recordIndex, 3, body);

	}
	
	// Ancillary methods ------------------------------------------------------ 
}
