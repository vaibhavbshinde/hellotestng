package testngkit.steps;

import basepackages.models.AddBookModel;
import org.testng.annotations.Test;
import testngkit.testngsetup.TestNGLocalSetup;
import basepackages.helper.BaseApiService;

import org.json.JSONObject;
import org.testng.Assert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddBookSteps_TestNG extends TestNGLocalSetup {
    private static JSONObject testdata;
    private int statusCode=200 ;
    private AddBookModel addBookModel;
    private String bookId = null;

    //commented for now ErrorModel class as dont have valid error response
    //private ErrorModel errorModel;

    @Test
     public void user_execute_add_book_request_api_post_using_valid_data() {
        log.info("user added Add book request");
        testdata = jsonDataReader.searchFile("addbook_details").fileToJson().getJson();
        log.info("test_data {}",testdata);
        addBookModel = dataService.addBookPostCall(testdata.toString());
        if (addBookModel== null){
           //errorModel =  dataService.mapToError(ErrorModel.class);
            log.info("(POST)- Error response in Add Book call !");
        }

    }

    @Test
    public void verify_status_code_for_api_is() {
        log.info("status code {}", BaseApiService.getStatusCode());
        Assert.assertEquals(BaseApiService.getStatusCode(), 200,"Expected status code is not matching");
    }

    @Test
    public void verify_added_book_id(){
      bookId =  addBookModel.getId();
      log.info("created book id : {} ",bookId);
    }



}
