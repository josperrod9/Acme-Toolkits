package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String code,
		final String description, final String assemblyNotes, final String info, final String totalPrice) {

		super.clickOnMenu("Anonymous", "List Toolkit");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("info", info);
		super.checkButtonExists("Artefacts");

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list-artefact.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveArtefactTest(final int recordIndex, final int recordIndexToolkit, final String amount, final String name,final String type, final String code) {

		super.clickOnMenu("Anonymous", "List Toolkit");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndexToolkit);
        super.checkFormExists();
        super.checkButtonExists("Artefacts");
        super.clickOnButton("Artefacts");
        super.checkListingExists();
        super.sortListing(1, "asc");
        super.checkColumnHasValue(recordIndex, 0, amount);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
        super.checkInputBoxHasValue("amount", amount);
        super.checkInputBoxHasValue("artefact.type", type);
        super.checkInputBoxHasValue("artefact.name", name);
        super.checkInputBoxHasValue("artefact.code", code);
	}
	
	// Ancillary methods ------------------------------------------------------

}
