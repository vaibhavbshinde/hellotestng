package basepackages.helper;

import groovy.util.logging.Slf4j;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public class ApiConfigGenerator {
    private static ConfigMapper apiConfig = null;

    public ConfigMapper getApiConfig(){
        String path = "src/test/resources/config";
        ConfigFactory.setProperty("mypath",path);
        apiConfig = ConfigFactory.create(ConfigMapper.class);
        return apiConfig;
    }

}
