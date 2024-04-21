package basepackages.helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Utils {
    private static ConfigMapper apiConfig;
    private static String authSecret;
    protected static RequestSpecification request;
    protected static Response response ;


    public void setApiConfig(ConfigMapper apiConfig,String authType){
        Utils.apiConfig = apiConfig;
        Utils.authSecret = getTestNGUserToken();
    }

    public ConfigMapper getApiConfig(){
        return apiConfig;
    }

    public String getTestNGUserToken(){
        String token = null;

       Map<String,String> requestParam =  new HashMap<>();
       String payLoad = "{\n" +
               "    \"username\": \""+apiConfig.getJiraUser()+"\",\n" +
               "    \"password\": \""+apiConfig.getJiraPassword()+"\"\n" +
               "}";


        RestAssured.baseURI = apiConfig.getBaseJiraApiUri();
        request = RestAssured.given();
        request.body(payLoad);
        request.headers(defaultHeadersMockResponseCode("200"));
        Response responseToken = request.post(apiConfig.getBaseJiraApiUri()+"/rest/auth/1/session");

        // Added here to read the response and find out the specific value instead of creating model class
        JsonPath js = new JsonPath(responseToken.asString());
        token =  js.getString("session.value");
        return token;
    }



    // this method is used to get the token which is gets created after login
    public String getToken(){

        // actual Login URL
        //https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate?username=Admin&password=admin123

        String token = null;
       /* RestAssured.baseURI= apiConfig.getBaseApiUri();
        request = RestAssured.given();

        //setting header and formParameter
        request.header("Content-Type","application/x-www-form-urlencoded");
        request.formParam("username",apiConfig.getUser());
        request.formParam("password",apiConfig.getPassword());

        Response responseToken =  request.post(apiConfig.getBaseApiUri()+"/web/index.php/auth/login");

        try{
            token = new ObjectMapper().readValue(response.body().asString(), UserTokenModel.class).getToken();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("Token Generated : {} ", token);
        System.out.println("Token Generated : "+token);

        */
       return token;
    }

    //Default headers which normally needed for api request
    public Map<String,String> defaultHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");
        return headers;
    }

    //After successful login with user credentials , whatever token/cookie and
    ///session storage is gets created, which we are adding to it for further services
    public Map<String,String> addAuthorizationHeader(Map<String,String> headers){
        headers.put("Cookie","JSESSIONID="+authSecret);
        return headers;
    }

    public Map<String,String> defaultHeadersWithToken(String mockResponseCode){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");
        headers.put("x-mock-response-code",mockResponseCode);
        headers=addAuthorizationHeader(headers);
        return headers;
    }

    public Map<String,String> defaultHeadersMockResponseCode(String mockResponseCode){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");
        headers.put("x-mock-response-code",mockResponseCode);
        return headers;
    }

}
