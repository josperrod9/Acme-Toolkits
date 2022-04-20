package acme.testing.any.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyArtefactListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/artefact/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String code, 
		final String retailPrice, final String technology, final String description) {

		super.clickOnMenu("Anonymous", "List Artefact");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.checkColumnHasValue(recordIndex, 0, type);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, code);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("type", type);
        super.checkInputBoxHasValue("name", name);
        super.checkInputBoxHasValue("code", code);
        super.checkInputBoxHasValue("retailPrice", retailPrice);
        super.checkInputBoxHasValue("technology", technology);
        super.checkInputBoxHasValue("description", description);

		
	}
	
	// Ancillary methods ------------------------------------------------------

}
