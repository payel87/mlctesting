package steps;

import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import helpers.GenericHelper;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.jayway.jsonpath.DocumentContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuspostApiSteps extends BaseSteps {

    private RunContext rc;
    private static final String AUTH_KEY_VALUE = "c6d2fab4-9841-4cdd-b24f-7be589410f21";

    public AuspostApiSteps(RunContext rc) {
        super(rc);
        this.rc = rc;
    }

    private GenericHelper functions = new GenericHelper();


    @Given("^I read the base url$")
    public void iReadTheBaseUrl() throws Throwable {

        RestAssured.baseURI = rc.getDataFromYAML("apiEndPointUrl");

    }

    @When("^I make a get call for shipping cost for ([^\"]*) , ([^\"]*) and ([^\"]*)$")
    public void iMakeAGetCallForShippingCostForCountry_codeWeightAndService_code(String countryCode, String weight, String serviceCode) throws Throwable {
        //GET call
        iAddHeaderWithKeyAndValue("AUTH-KEY", AUTH_KEY_VALUE);
        Headers headers = new Headers(rc.headerList);
        Map<String, String> params = rc.params;

        String resource = "/postage/parcel/international/calculate";
        params.put("country_code", countryCode);

        params.put("weight", weight);
        params.put("service_code", serviceCode);
        iMakeTheGETCallWithParams(headers, params, resource);
    }

    @And("^The response status is \"([^\"]*)\"$")
    public void theResponseStatusIs(int statusCode) throws Throwable {
        // Validate response code
        rc.response.then().statusCode(statusCode);
    }

    @And("^I save the response in \"([^\"]*)\"$")
    public void iSaveTheResponseIn(String fileName) throws Throwable {
        // This method saved the response in a file

        String path = System.getProperty("user.dir") + "\\target\\api-response-files\\" + fileName + ".txt";
        System.out.println(path);
        functions.saveResponse(rc.response, path);

    }

    @And("^The actual response values should match with \"([^\"]*)\" reference json for \"([^\"]*)\"$")
    public void actualResponseValuesShouldMatchWithReferenceJsonForBelowElements(String jsonResponseRef,String jsonPath) {
        //Match with response
        String jsonResponseRefFileName = jsonResponseRef+".json";
        JSONParser parser = new JSONParser();

        try {

            Object objExpected = parser.parse(new FileReader("src/main/resources/data/jsonResponseReferences/"+jsonResponseRefFileName)) ;
            JSONObject jsonObjectExpected = (JSONObject) objExpected;
            String jsonStringExpected = jsonObjectExpected.toJSONString();
            DocumentContext jsonContextExpected = com.jayway.jsonpath.JsonPath.parse(jsonStringExpected);

            ResponseBody actualResponseBody = rc.response.getBody();
            DocumentContext jsonContextActual = JsonPath.parse(actualResponseBody.asString());

            String jsonValueExpected = jsonContextExpected.read(jsonPath).toString();
            String jsonValueActual = jsonContextActual.read(jsonPath).toString();
            Assert.assertEquals(jsonValueActual, jsonValueExpected , "Actual Value matches the expected Value");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    //Reusable Methods

    public void iAddHeaderWithKeyAndValue(String key, String value) throws Throwable {
        // Add header to header List
        Header h1 = new Header(key, value);
        rc.headerList.add(h1);
    }

    public void iMakeTheGETCallWithParams(Headers headers, Map<String, String> params, String resource) throws Throwable {
        // actual GET call
        rc.response = given().when().contentType("application/json; charset=UTF-8").log().all().
                headers(headers).config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames())).
                relaxedHTTPSValidation().
                params(params).
                body("").when().
                get(resource);

    }


}
