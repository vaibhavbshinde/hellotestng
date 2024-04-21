package testngkit.steps;

import basepackages.helper.JSONDataReader;
import basepackages.models.AddJiraTicketModel;
import basepackages.models.jiraticket.JiraTicketModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.testng.annotations.Test;
import testngkit.testngsetup.TestNGLocalSetup;
import basepackages.models.LoginModel;
import basepackages.helper.BaseApiService;
import org.json.JSONObject;
import org.testng.Assert;

@Slf4j
public class JiraAPISteps_TestNG  extends TestNGLocalSetup {

    private static JSONObject testdata;
    private int statusCode = 200;
    private String jiraTicketId ;

    private AddJiraTicketModel addJiraTicketModel;
    private JiraTicketModel jiraTicketModel;

    /*
     * [RESTASSURED-POST] - Create JIRA ticket request
     */

    @Test(groups = { "functional", "smoke" })
    public void user_execute_add_jira_ticket_api_post_using_valid_data() {
        testdata = jsonDataReader.searchFile("add_jira_ticket").fileToJson().getJson();
        log.info("test_data {}", testdata);
        String mockResponseCode ="200";
        addJiraTicketModel = dataService.addJiraTicketPostCall(testdata.toString(),mockResponseCode);
        if (addJiraTicketModel == null) {
            //errorModel =  dataService.mapToError(ErrorModel.class);
            log.info("(POST)- Error response in Jira Add Ticket request !");
        }
    }

    @Test(groups = { "functional", "smoke","regular" })
    public void verify_status_code_for_add_jira_ticket_api_is() {
        log.info("status code {}", BaseApiService.getStatusCode());
        Assert.assertEquals(BaseApiService.getStatusCode(), 200,"Expected status code is not matching");
    }

    @Test
    public void verify_created_jira_ticket_id(){
        jiraTicketId =addJiraTicketModel.getId();
        log.info("created Jira Ticket id : {} ",jiraTicketId);
    }


    /*
     * [RESTASSURED-GET] - GET JIRA ticket request
     */
    @Test(groups = { "regular" })
    public void verify_jira_ticket_filter_by_id(){
        String mockResponseCode ="200";
        String jiraTicketId= "10000";
        jiraTicketModel = dataService.getJiraTicketDetailsCall(jiraTicketId,mockResponseCode);
        if (jiraTicketModel == null) {
            log.info("(GET)- Jira ticket found with id {} ",jiraTicketId);
            Assert.assertEquals(!(jiraTicketModel.getProject().getProjectCategory().getName().equalsIgnoreCase("FIRST")),"Name is not matching!!");
            Assert.assertNotNull(jiraTicketModel.getKey(),"ID field is missing or null");
        }
    }

    /*
     * [RESTASSURED-PUT] - Update JIRA ticket request
     */
    @Test(groups = { "functional", "smoke","regular" })
    public void verify_update_jira_is_working(){
        String mockResponseCode ="200";
        String jiraTicketId= "10000";
        testdata = jsonDataReader.searchFile("update_jira_ticket").fileToJson().getJson();
        log.info("test_data {}", testdata);
        addJiraTicketModel = dataService.updateJiraTicketPutCall(testdata.toString(),jiraTicketId,mockResponseCode);
        if (addJiraTicketModel == null) {
            log.info("(PUT)- Error response in Jira Update Ticket request !");
        }
    }


}
