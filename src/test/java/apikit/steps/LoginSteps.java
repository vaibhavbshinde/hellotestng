package apikit.steps;

import apikit.setup.LocalSetup;
import basepackages.helper.BaseApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.testng.Assert;

@Slf4j
public class LoginSteps extends LocalSetup {
    private static JSONObject testdata;


    @When("User execute login request API \\(POST) using valid data")
    public void user_execute_login_request_api_post_using_valid_data() {
        log.info("in the step defination");
        System.out.println("in the step defination");

        testdata = jsonDataReader.searchFile("login_details").fileToJson().getJson();

        log.info("test_data {}",testdata);
        dataService.loginPostCall(testdata.toString());
    }

    @Then("Verify status code for api is {int}")
    public void verify_status_code_for_api_is(Integer statuscode) {
        log.info("status code {}", BaseApiService.getStatusCode());
        Assert.assertEquals(BaseApiService.getStatusCode(),(int) statuscode,"Expected status code is not matching");
    }
}
