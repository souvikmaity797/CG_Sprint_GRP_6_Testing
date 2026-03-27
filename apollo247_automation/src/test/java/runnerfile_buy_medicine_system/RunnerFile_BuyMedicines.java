package runnerfile_buy_medicine_system;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/featurefile",
        glue = {"stepdefinitions", "hooks"},
//        tags = "@TC_04",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class RunnerFile_BuyMedicines extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) 
    public Object[][] scenarios() {
        return super.scenarios();
    }
}