package utilities;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberReport.html", "json:target/testReport.json"},
        features = "/Users/imanu/Desktop/CashwiseApiFinalProject/src/test/resources/features",
        glue = "step_definitions",
        tags = "@addSellers",
        dryRun = false
)




public class CucumberRunner {

}
