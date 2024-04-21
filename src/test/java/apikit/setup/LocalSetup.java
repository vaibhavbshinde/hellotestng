package apikit.setup;

import basepackages.helper.*;
import basepackages.service.DataService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

@Slf4j
public class LocalSetup extends AbstractTestNGCucumberTests{

    protected static ApiFactory apiFactory = null;
    protected static DataService dataService;
    protected static ConfigMapper config;

    protected static JSONDataReader jsonDataReader;
    protected static JSONDataModifier jsonDataModifier;

    protected static TestParameter testParameter;

    @BeforeSuite
    public void start(){
       System.out.println("i ma herererearewrwerewqwrqwrqwr");
        String testEnvironment  = PropertyReader.getEnvironment();
        String testBrowser = PropertyReader.getBrowser();
        String authSecret = PropertyReader.getAuthSecret();
        config = new ApiConfigGenerator().getApiConfig();

         jsonDataReader = new JSONDataReader();
        jsonDataModifier = new JSONDataModifier();
        testParameter = new TestParameter();

        apiFactory = new ApiFactory(config,authSecret);
        dataService = apiFactory.getDataService();


    }

    @AfterSuite
    public void stop() throws JSONException, InterruptedException, IOException{


    }

}
