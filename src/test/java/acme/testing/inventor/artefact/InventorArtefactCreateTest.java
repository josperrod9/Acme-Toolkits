package acme.testing.inventor.artefact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorArtefactCreateTest  extends TestHarness{
	
	// Lifecycle managment 
	
	// Test cases
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String type, final String name, final String code, final String retailPrice,
		final String technology, final String description, final String info) {
		
		super.signIn("inventor1", "inventor1");
	
		super.clickOnMenu("Inventor", "My Artefacts");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);	
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "My Artefacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		super.checkColumnHasValue(recordIndex, 3, type);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/artefact/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex,  final String type, final String name, final String code, final String retailPrice,
		final String technology, final String description, final String info) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My Artefacts");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);	
		super.fillInputBoxIn("info", info);
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
