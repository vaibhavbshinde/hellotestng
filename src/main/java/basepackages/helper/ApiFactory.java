package basepackages.helper;

import basepackages.service.DataService;
import basepackages.helper.Utils;


public class ApiFactory {

    private DataService dataServiceApi;
    private ConfigMapper apiConfig;
    private String authSecret;

    public ApiFactory(ConfigMapper apiConfig,String authSecret){
        new Utils().setApiConfig(apiConfig,authSecret);
    }

    //creating new object the DataServiceApi, if it is not creating earlier
    public DataService getDataService() {
        if(dataServiceApi==null) {
            dataServiceApi =new DataService();
        }
        return dataServiceApi;
    }
}
