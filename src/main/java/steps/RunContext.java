package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.DataReader;
import helpers.GenericHelper;
import helpers.PageManager;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RunContext {

    public WebDriver driver;
    public Scenario scenario;
    public Map<String, String> params;
    public static String testdataEnvironment;
    public static String testBrowser;
    public List<Header> headerList = null;
    public PageManager pageManager;
    public Response response = null;
    public String requestBody;
    public static String apiSessionToken;

    @Before ("@ui")
    public void initUi(Scenario scenario){
        if(testBrowser.equalsIgnoreCase("chrome"))
        {
            driver = GenericHelper.getChromeDriver();
        }
        else
        {
            driver = GenericHelper.getEdgeDriver();
        }
        this.scenario = scenario;
        this.pageManager = new PageManager(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


     @After ("@ui")
        public void tearDownUi(Scenario scenario){
        //take screenshot
         final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
         scenario.embed(screenshot, "image/png");
         driver.quit();

    }

    @Before ("@api")
    public void initApi(Scenario scenario){
        this.scenario = scenario;
        params = new HashMap<>();
        response = null;
        headerList = new ArrayList<Header>();
        requestBody = "";
        apiSessionToken= null;
    }
    @After ("@api")
    public void tearDownApi(Scenario scenario){


    }

    public static String getDataFromYAML(String yamlVariable)
    {
        String valueFromYAML = new DataReader().yamlReader("test_data.yaml").get(yamlVariable).get(testdataEnvironment).trim();
        return valueFromYAML;
    }

    private static void processEnvDetailsFromCMD() {
        String envurlcmd = "";
        try {
            envurlcmd = System.getProperty("envurlcmd").toLowerCase();
        } catch (Exception cmdLineExceptionMessage) {
            System.out.println("Exception while reading environment details from command line :- " + cmdLineExceptionMessage.getStackTrace());
            try {
                envurlcmd = System.getenv("envurlcmd").toLowerCase();
            } catch (Exception ideExceptionMessage) {
                System.out.println("Exception while reading environment details from IDE Config :- " + ideExceptionMessage.getStackTrace());
            }
        }

        if (!envurlcmd.isEmpty()) {
            if (envurlcmd.contains("test") || envurlcmd.contains("dev")) {
                testdataEnvironment = "test";
            }
            else if (envurlcmd.contains("uat")) {
                testdataEnvironment = "preprod";
            }
            if(envurlcmd.contains("prod")){
                testdataEnvironment = "prod";
            }

        }
        else
            testdataEnvironment = "test";
    }

    private static void processBrowserFromCMD() {
        String browser = "";
        try {
            browser = System.getProperty("browser").toLowerCase();
        } catch (Exception cmdLineExceptionMessage) {
            System.out.println("Exception while reading browser details from command line :- " + cmdLineExceptionMessage.getStackTrace());
                    }

        if (!browser.isEmpty()) {
            if (browser.contains("chrome")) {
                testBrowser = "chrome";
            }
            else if (browser.contains("edge")) {
                testBrowser = "edge";
            }

        }
        else
            testBrowser = "edge";
    }

    static {
        processEnvDetailsFromCMD();
        processBrowserFromCMD();
    }

}
