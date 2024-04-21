package apikit.runner;

import apikit.setup.LocalSetup;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/angular_practice.feature"},
        glue =  {"apikit.steps"},
        plugin = {"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json" },
        dryRun = false,
        monochrome = true
)
public class ApiRunner extends LocalSetup {

}
