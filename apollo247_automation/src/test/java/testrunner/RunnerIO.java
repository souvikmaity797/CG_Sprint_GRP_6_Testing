package testrunner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/apollo.feature",
        glue = "stepdefinitions",
        dryRun=false,
        plugin = {"pretty", "html:target/report.html"},
        monochrome = true
)
public class RunnerIO extends AbstractTestNGCucumberTests {
}
