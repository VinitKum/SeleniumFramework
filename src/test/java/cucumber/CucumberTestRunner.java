package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "cucumber",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
       // tags = "@smoke and @regression"

)
public class CucumberTestRunner extends AbstractTestNGCucumberTests
{

}