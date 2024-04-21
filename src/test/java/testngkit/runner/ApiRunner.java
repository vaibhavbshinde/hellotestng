package testngkit.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import testngkit.testngsetup.TestNGLocalSetup;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/angular_practice.feature"},
        glue =  {"apikit.steps"},
        plugin = {"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json" })
public class ApiRunner extends TestNGLocalSetup {



}
