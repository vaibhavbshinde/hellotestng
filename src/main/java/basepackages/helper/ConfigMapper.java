package basepackages.helper;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:${mypath}/config.properties"})
public interface ConfigMapper extends Config {
        @Key("base.api.uri")
        String getBaseApiUri();

        @Key("user")
        String getUser();

        @Key("password")
        String getPassword();

        @Key("base.jira.api.url")
        String getBaseJiraApiUri();

        @Key("jira.user")
        String getJiraUser();

        @Key("jira.password")
        String getJiraPassword();
}

