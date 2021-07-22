package pageobjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;


public class LifeViewPage {

    private WebDriver driver;

    public LifeViewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button [@class = 'link-icon search-toggle js-popover-toggle']")
    public WebElement searchButton;

    @FindBy(id = "q")
    public WebElement searchBox;

    @FindBy(xpath="(//div[@class='collection-item '])[1]//h2")
    public WebElement lifeViewLink;

    @FindBy(xpath = "//ul[@itemprop='breadcrumb']/li")
    public List<WebElement> breadcrumbs;

    @FindBy(className = "video-desc")
    public WebElement videoText;

    @FindBy(linkText = "Request a demo")
    public WebElement requestDemoButton;

    @FindBy(xpath = "(//div[contains(@class,'form-group')]/input)[1]")
    public WebElement nameField;

    @FindBy(xpath = "(//div[contains(@class,'form-group')]/input)[2]")
    public WebElement companyField;

    @FindBy(xpath = "(//div[contains(@class,'form-group')]/input)[3]")
    public WebElement emailField;

    @FindBy(xpath = "(//div[contains(@class,'form-group')]/input)[4]")
    public WebElement phoneField;

    @FindBy(xpath = "(//div[contains(@class,'form-group')]/input)[5]")
    public WebElement dateField;

    @FindBy(xpath = "(//div[@class ='radio']//input)[1]")
    public WebElement amField;

    @FindBy(xpath = "(//div[@class ='radio']//input)[2]")
    public WebElement pmField;

    @FindBy(xpath = "//textarea")
    public WebElement requestDetails;

    //methods

    public void clickOnSerachButton() {
        searchButton.click();
    }

    public void enterSearchKeyword(String keyword) {
        searchBox.click();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void iClickOnLifeView() {
        lifeViewLink.click();
    }

    public boolean iVerifyBreadCrumbs() {
        boolean flag;
        flag = breadcrumbs.get(0).getText().trim().equals("Home") && breadcrumbs.get(1).getText().trim().equals("Partnering with us") &&
                breadcrumbs.get(2).getText().trim().equals("Superannuation funds") && breadcrumbs.get(3).getText().trim().equalsIgnoreCase("LifeView");
        return flag;
    }

    public void iClickOnRequestADemo() {
        scrollDownToElement(videoText);
        requestDemoButton.click();
    }

    public void iEnterName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void iEnterCompany(String company) {
        companyField.clear();
        companyField.sendKeys(company);
    }

    public void iEnterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void iEnterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void iEnterContactDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void iEnterTime(String time) {
        if(time.equalsIgnoreCase("am"))
        {
            amField.click();
        }
        else
        {
            pmField.click();
        }
    }

    public void iEnterRequestDetails(String details) {
        requestDetails.clear();
        requestDetails.sendKeys(details);
    }


    public void scrollDownToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

}
