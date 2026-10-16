package cucumber;
import java.io.IOException;
import java.net.MalformedURLException;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ExtentCucumberManager;
import utilities.ExtentManager;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws IOException, MalformedURLException {

        System.out.println("===== BEFORE 1 : Driver Setup =====");

        ConfigReader reader = new ConfigReader();

        String browser =
                System.getProperty("browser", reader.getBrowser());

        String execution =
                System.getProperty("execution",
                        reader.getExecutionMode());

        String gridUrl = reader.getGridUrl();

        boolean headless = reader.isHeadless();

        DriverManager.initializeDriver(
                browser,
                execution,
                headless,
                gridUrl
        );

        DriverManager.getDriver()
                .get(reader.getUrl());

        ExtentTest extentTest =
                ExtentManager.getReport()
                        .createTest(scenario.getName());

        ExtentCucumberManager.setTest(extentTest);
    }
    @After
    public void tearDown(Scenario scenario) {

        ExtentTest extentTest =
                ExtentCucumberManager.getTest();

        if (scenario.isFailed()) {

            System.out.println("Scenario failed - taking screenshot");

            byte[] screenshot =
                    ((TakesScreenshot) DriverManager.getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            // Cucumber report
            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot"
            );

            // Extent report
            extentTest.fail(
                    "Scenario Failed"
            );

            extentTest.addScreenCaptureFromBase64String(
                    java.util.Base64.getEncoder()
                            .encodeToString(screenshot),
                    "Failure Screenshot"
            );

        } else {

            extentTest.pass("Scenario Passed");
        }

        System.out.println("===== AFTER 1 : Driver TearDown =====");

        DriverManager.quitDriver();

        ExtentManager.getReport().flush();

        ExtentCucumberManager.removeTest();
    }

}