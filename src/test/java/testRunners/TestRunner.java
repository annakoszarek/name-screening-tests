package testRunners;

import org.junit.runner.RunWith;
import cucumber.api.junit.*;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {"steps"},
        tags = {"@edit_assignment1"},
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber-reports/cucumber-report.json"},
        monochrome = true
)
public class TestRunner {
}

