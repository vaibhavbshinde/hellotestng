package testngkit.testngsetup;

import basepackages.helper.ApiConfigGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test_TestNG {

    public static void main(String args[]){
        ApiConfigGenerator apiConfigGenerator = new ApiConfigGenerator();
        String str = apiConfigGenerator.getApiConfig().getBaseApiUri();
        System.out.println("Base URL from the configuration file : "+ str);
    }
}
