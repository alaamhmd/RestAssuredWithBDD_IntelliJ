package steps;

import data.LoadProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import pages.PageBase;

public class VerifyAttributeValueSteps extends PageBase {
    PageBase pageBase;
    Response response;
    SoftAssert softAssert;
    String baseURL =LoadProperties.userData.getProperty("baseURL");
    String path =LoadProperties.userData.getProperty("path");
    String agent=LoadProperties.userData.getProperty("agent");
    String systemInfo= LoadProperties.userData.getProperty("systemInfo");
    String attributeName= LoadProperties.userData.getProperty("attributeName");
    @Given("API URL")
    public void apiURL() {

        pageBase = new PageBase();
        pageBase.setBaseURL(baseURL);
        path = pageBase.setPath(path);
    }

    @When("We send a get request to get response")
    public void weSendAGetRequestToGetResponse() {
        response =pageBase.getPageResponse(path,agent, systemInfo);

    }

    @Then("We will check that value of numViews is greater than {int}")
    public void we_will_check_that_value_of_num_views_is_greater_than(Integer desiredValue) {

        softAssert = new SoftAssert();
        attributeValue = pageBase.getSpecificAttributeValue
                (attributeName, response);

        for (String i : attributeValue) {
            int attributeValue =
                    Integer.parseInt(i);
            softAssert.assertEquals((attributeValue > desiredValue),true);

        }
        softAssert.assertAll();
    }

    }



