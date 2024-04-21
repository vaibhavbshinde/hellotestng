package basepackages.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyReader {

    public static String getEnvironment(){
        String env = System.getProperty("environment");
        return (env ==null || env.equals("")?"dev":env);
    }

    public static String getBrowser(){
        String browser = System.getProperty("browser");
        return   (browser==null || browser.equals("")?"chrome" : browser);
    }

    public static String getOS(){
        String os = System.getProperty("os");
        return (os==null || os.equals("")?"mac":os);
    }

    public static String getAuthSecret(){
        String authSecret = System.getProperty("authSecret");
        return (authSecret==null || authSecret.equals("") ? "":authSecret);
    }

}
