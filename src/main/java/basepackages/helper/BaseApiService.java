    package basepackages.helper;

    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import io.restassured.RestAssured;
    import io.restassured.response.Response;
    import io.restassured.specification.RequestSpecification;
    import lombok.extern.slf4j.Slf4j;

    import java.util.Map;

    @Slf4j
    public class BaseApiService extends Utils {
        protected static RequestSpecification request;
        protected static Response response;


        /*
         * Creating given request using RequestSpecification
         */
        public RequestSpecification getRequest() {
            RestAssured.baseURI = getApiConfig().getBaseApiUri();
            request = RestAssured.given();
            return request;
        }

        /*
         * POST method call request
         * parameter : header, payLoadBody, endPoint
         */
        protected void postCall(Map<String, String> header, String payLoadBody, String endPoint) {
            request = getRequest();
            request.headers(header);
            request.body(payLoadBody);
           //request.log().all();
            response = request.post(endPoint);
        }

        /*
         * PUT method call request
         * parameter : header, payLoadBody, endPoint
         */
        protected void putCall(Map<String, String> header, String payLoadBody, String endPoint) {
            request = getRequest();
            request.headers(header);
            request.body(payLoadBody);
            response = request.put(endPoint);
        }

        /*
         * GET method call request
         * parameter : header, endPoint
         * Description : make sure while sending endpoint ,
         * it should also contain the queryparam as well,
         * as we are not inserting seperately query
         */
        protected void getCall(Map<String, String> header, String endPoint) {
            request = getRequest();
            request.headers(header);
            response = request.put(endPoint);
        }

        /*
         * delete method call request
         * parameter : header, payLoadBody, endPoint
         */
        protected void deleteCall(Map<String, String> header, String endPoint) {
            request = getRequest();
            request.headers(header);
            response = request.delete(endPoint);
        }

        /*
         * return response body
         */
        public static String getResponseBody() {
            return response.getBody().asString();
        }

        /*
         * return ContentType
         */
        public static String getContentType() {
            return response.getContentType();
        }

        /*
         * return response status code
         */
        public static int getStatusCode() {
            return response.getStatusCode();
        }
        public <T> T mapToModel(Class mapper) {

            try {
                return (T) new ObjectMapper().readValue(response.body().asString(), mapper);
            } catch (JsonProcessingException e) {
                log.info("Class Mapping error occurred {}", e);
                return null;
            }
        }


        public <T> T mapToError(Class error) {
            try {
                return (T) new ObjectMapper().readValue(response.body().asString(), error);
            } catch (JsonProcessingException e) {
                log.info("Class Mapping error occurred {}", e);
                return null;
            }
        }

        public <T> T mapToModelOrError(Class mapper, Class error) {

            if (getStatusCode() == 200) {
                try{
                    return (T) new ObjectMapper().readValue(response.body().asString(),mapper);
                }catch(JsonProcessingException e){
                    log.info("Error occurred while model mapping {}",e.getMessage());
                    return null;
                }
            } else {
                try{
                    return (T) new ObjectMapper().readValue(response.body().asString(),error);
                }catch(JsonProcessingException e){
                    log.info("Error occurred while error class mapping {}",e.getMessage());
                    return null;
               }
            }
        }
    }
