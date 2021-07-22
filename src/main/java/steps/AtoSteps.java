package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AtoSteps extends BaseSteps {

        private RunContext rc;
        public static final String TAX_MSG="The estimated tax on your taxable income is $";

        public AtoSteps(RunContext rc) {
            super(rc);
            this.rc = rc;
        }

    @When("^I enter income year as ([^\"]*)$")
    public void iEnterIncomeYearAsYear(String year) {
        rc.pageManager.atoPage.enterYear(year);
    }

    @And("^I enter taxable income as ([^\"]*)$")
    public void iEnterTaxableIncomeAsIncome(String income) {
       rc.pageManager.atoPage.enterTaxableIncome(income);
    }

    @And("^I enter residential type as ([^\"]*)$")
    public void iEnterResidentialTypeAsResiType(String resiType) {
        rc.pageManager.atoPage.enterResidencyStatus(resiType);
    }

    @And("^I submit the form$")
    public void iSubmitTheForm() {
       rc.pageManager.atoPage.clickOnSubmitButton();
    }

    @Then("^I should see correct Income tax displayed as ([^\"]*)$")
    public void iShouldSeeCorrectIncomeTaxDisplayedAsTax(String tax) {
        Assert.assertEquals(rc.pageManager.atoPage.verifyCorrectCalculatedTaxDisplayed(),TAX_MSG+tax,"Correct Tax and message displayed");
    }
}
