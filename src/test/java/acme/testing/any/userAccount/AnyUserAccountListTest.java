package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness{

    // Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------

    @ParameterizedTest
    @CsvFileSource(resources = "/any/userAccount/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String roleList, final String username, final String surname, final String name, final String email) {
    	
        super.clickOnMenu("Anonymous", "List User accounts");
        super.checkListingExists();
        super.sortListing(1, "asc");

        super.checkColumnHasValue(recordIndex, 0, username);
        super.checkColumnHasValue(recordIndex, 1, name);
        super.checkColumnHasValue(recordIndex, 2, surname);
        super.checkColumnHasValue(recordIndex, 3, roleList);
        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("username", username);
        super.checkInputBoxHasValue("roleList", roleList);
        super.checkInputBoxHasValue("identity.surname", surname);
        super.checkInputBoxHasValue("identity.name", name);
        super.checkInputBoxHasValue("identity.email", email);

    }
    
    // Ancillary methods ------------------------------------------------------ 
}
