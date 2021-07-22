package steps;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class LifeViewSteps extends BaseSteps {

        private RunContext rc;

        public LifeViewSteps(RunContext rc) {
            super(rc);
            this.rc = rc;
        }

        @Given("^I am on ([^\"]*) page$")
        public  void iNavigateToPage(String page)
        {
            if(page.equals("mlclifeview"))
            {
                rc.driver.navigate().to(rc.getDataFromYAML("valid mlc url"));
                rc.driver.manage().window().maximize();
                WebDriverWait wait = new WebDriverWait(rc.driver,10);
                wait.until(ExpectedConditions.visibilityOf(rc.pageManager.lvPage.searchButton));
            }
            else if(page.equalsIgnoreCase("ato"))
            {
                 rc.driver.navigate().to(rc.getDataFromYAML("valid ato url"));
                rc.driver.manage().window().maximize();
                WebDriverWait wait = new WebDriverWait(rc.driver,10);
                wait.until(ExpectedConditions.visibilityOf(rc.pageManager.atoPage.incomefield));
            }
            else
            {
                System.out.println("Incorrect page option");
            }
        }

    @When("^I search for ([^\"]*) on Homepage$")
    public void iSearchForLifeviewOnHomepage(String word) {
        rc.pageManager.lvPage.clickOnSerachButton();
        rc.pageManager.lvPage.enterSearchKeyword(word);

    }

    @And("^I click on Lifeview link$")
    public void iClickOnLifeviewLink() {
      rc.pageManager.lvPage.iClickOnLifeView();
    }

    @Then("^I should see the correct breadcrumbs successfully$")
    public void iShouldSeeTheCorrectBreadcrumbsSuccessfully() {
        Assert.assertTrue(rc.pageManager.lvPage.iVerifyBreadCrumbs(), "Correct breadcrumbs displayed");
    }

    @When("^I click on Request a demo button$")
    public void iClickOnRequestADemoButton() {
       rc.pageManager.lvPage.iClickOnRequestADemo();
    }

    @Then("^I should be able to enter relevant data ([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*) in the form$")
    public void iShouldBeAbleToEnterRelevantDataInTheForm(String name,String company,String email,String phone,String date,String time,String details) {
            rc.pageManager.lvPage.iEnterName(name);
            rc.pageManager.lvPage.iEnterCompany(company);
            rc.pageManager.lvPage.iEnterEmail(email);
            rc.pageManager.lvPage.iEnterPhone(phone);
            rc.pageManager.lvPage.iEnterContactDate(date);
            rc.pageManager.lvPage.iEnterTime(time);
            rc.pageManager.lvPage.iEnterRequestDetails(details);
    }
}
