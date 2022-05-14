package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateServiceTest extends TestHarness{

	// Lifecycle managment 
	
		// Test cases
		
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest (final int recordIndex, final String code, final String status,final String statusAfterUpdate) {
			super.signIn("inventor1", "inventor1");
			
			super.clickOnMenu("Inventor", "My Patronages");
			super.checkListingExists();
			super.sortListing(0, "asc");
			super.checkColumnHasValue(recordIndex, 0, code);
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("status", status);
			super.fillInputBoxIn("status", statusAfterUpdate);
			super.checkSubmitExists("Update");
			super.clickOnSubmit("Update");
			
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			
			super.checkInputBoxHasValue("status", statusAfterUpdate);
			super.checkNotSubmitExists("Update");
			
			super.signOut();
		}
}
