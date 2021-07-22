package helpers;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

    public class PageManager
    {
        public LifeViewPage lvPage;
        public AtoPage atoPage;

        public PageManager(WebDriver driver) {
            lvPage = new LifeViewPage(driver);
            atoPage = new AtoPage(driver);
        }
}
