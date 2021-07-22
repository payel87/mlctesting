package pageobjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AtoPage {

    private WebDriver driver;

    public AtoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="ddl-financialYear")
    public WebElement yearSelection;

    @FindBy(id = "texttaxIncomeAmt")
    public WebElement incomefield;

    @FindBy(xpath="(//input[@type='radio'])[1]")
    public WebElement resiTofullYear;

    @FindBy(id = "vrb-residentnonResident")
    public WebElement nonResifullYear;

    @FindBy(id = "vrb-residentpartYearResident")
    public WebElement partYearResident;

    @FindBy(id = "bnav-n1-btn4")
    public WebElement submitButton;

    @FindBy(className = "white-block")
    public WebElement calculatedTax;

    @FindBy(id = "ddl-residentPartYearMonths")
    public  WebElement numOfMonths;

    //methods

    public void clickOnSubmitButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }

    public void enterYear(String year) {
        Select selectyear = new Select(yearSelection);
        selectyear.selectByVisibleText(year);
    }

    public void enterTaxableIncome(String income)
    {
        incomefield.clear();
        incomefield.sendKeys(income);
    }

    public void enterResidencyStatus(String resi)
    {
        if(resi.equalsIgnoreCase("Resident for full year"))
        {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resiTofullYear);
        }
        else if(resi.equalsIgnoreCase("Non-resident for full year"))
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nonResifullYear);

        }
        else if (resi.equalsIgnoreCase("Part-year resident"))
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", partYearResident);
          Select selectyear = new Select(numOfMonths);
          selectyear.selectByIndex(2);


        }
        else
        {
            System.out.println("incorrect option");
        }
    }

    public String verifyCorrectCalculatedTaxDisplayed()
    {
      WebDriverWait wait = new WebDriverWait(driver,10);
      wait.until(ExpectedConditions.visibilityOf(calculatedTax));
       return calculatedTax.getText().trim();
    }

}
