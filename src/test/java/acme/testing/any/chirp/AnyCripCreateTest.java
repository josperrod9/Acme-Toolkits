package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyCripCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String title, final String body, final String email, final String author) {
		
	
		super.clickOnMenu("Anonymous", "List Chirps");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Anonymous", "List Chirps");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 2, email);
		super.checkColumnHasValue(recordIndex, 1, author);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String body, final String email, final String author) {
		super.clickOnMenu("Anonymous", "List Chirps");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");

		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
	//hacking tests cannot be easily implemented with the current version of the framework,
    //but they can be documented and conducted manually to ensure that there aren’t any trivial hacking problems
	}

}
