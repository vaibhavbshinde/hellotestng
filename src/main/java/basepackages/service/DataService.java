package basepackages.service;

import basepackages.helper.BaseApiService;
import basepackages.models.AddBookModel;
import basepackages.models.LoginModel;
import basepackages.models.AddJiraTicketModel;
import basepackages.models.errormodel.ErrorModel;
import basepackages.models.jiraticket.JiraTicketModel;

public class DataService extends BaseApiService {

   //POST CALL
    public AddJiraTicketModel addJiraTicketPostCall(String parsedTestData,String mockResponseCode){
        postCall(defaultHeadersWithToken(mockResponseCode),parsedTestData,getApiConfig().getBaseJiraApiUri()+"/rest/api/2/issue");
        return mapToModel(AddJiraTicketModel.class);
    }

    //PUT CALL
    public AddJiraTicketModel updateJiraTicketPutCall(String parsedTestData,String id ,String mockResponseCode){
        putCall(defaultHeadersWithToken(mockResponseCode),parsedTestData,getApiConfig().getBaseJiraApiUri()+"/rest/api/2/issue/"+id);
        return mapToModel(AddJiraTicketModel.class);
    }

    //GET CALL
    public <T> T getJiraTicketDetailsCall(String id,String mockResponseCode){
        getCall(defaultHeadersWithToken(mockResponseCode),getApiConfig().getBaseJiraApiUri()+"/rest/api/2/issue/"+id);
        return mapToModelOrError(JiraTicketModel.class, ErrorModel.class);
    }

    public AddBookModel addBookPostCall(String parsedTestData){
        postCall(defaultHeaders(),parsedTestData,getApiConfig().getBaseApiUri()+"/Library/Addbook.php");
        return mapToModel(AddBookModel.class);
    }

    public LoginModel loginPostCall(String parsedTestData){
        System.out.println("now got here parseddata , "+ parsedTestData);
        postCall(defaultHeaders(),parsedTestData,getApiConfig().getBaseApiUri()+"/Library/Addbook.php");
        return mapToModel(AddBookModel.class);
    }

    /*
     * Example for how to call api services
     *
    public AgencyModel[] agencyPostCall(String parsedTestData){
        postCall(defaultHeaders(),parsedTestData,getApiConfig().getBaseApiUri()+"/agencies");
        return mapToModel(AgencyModel[].class);
    }

    public AgencyModel[] agencyPutCall(String parsedTestData){
        putCall(defaultHeaders(),parsedTestData,getApiConfig().getBaseApiUri()+"/agencies/"+id);
        return mapToModel(AgencyModel[].class);
    }

    public <T> T deleteAgencyCall(String parsedTestData){
        deleteCall(defaultHeaders(),parsedTestData,getApiConfig().getBaseApiUri()+"/agencies/"+id);
        return mapToModelOrError(AgencyModel.class, ErrorModel.class);
    }

    public <T> T getAgencyCall(String Id){
        getCall(defaultHeaders(),getApiConfig().getBaseApiUri()+"/agencies/"+id);
        return mapToModelOrError(AgencyModel.class, ErrorModel.class);
    }

    public <T> T growthMetricsGetCall(String startEndDate){
        getCall(defaultHeaders(),getApiConfig().getBaseApiUri()+"/metrics/growth-platform-metrics?"+startEndDate);
        return mapToModelOrError(growthMetrics.class, ErrorModel.class);
    }
   */
}
