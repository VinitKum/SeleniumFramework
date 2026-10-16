package base;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("") String browser)
            throws IOException, MalformedURLException {

        ConfigReader reader = new ConfigReader();

        // Browser from TestNG XML, otherwise config.properties
        if (browser == null || browser.isBlank()) {
            browser = System.getProperty("browser", reader.getBrowser());
        }

        // Execution always from config.properties (or JVM property)
        String execution = System.getProperty("execution", reader.getExecutionMode());

        String gridUrl = reader.getGridUrl();
        boolean headless = reader.isHeadless();

        // ===================== CHROME =====================

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            if (execution.equalsIgnoreCase("local")) {

                driver.set(new ChromeDriver(options));

            } else {

                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));

            }

        }

        // ===================== FIREFOX =====================

        else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            if (execution.equalsIgnoreCase("local")) {

                driver.set(new FirefoxDriver(options));

            } else {

                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));

            }

        }

        // ===================== EDGE =====================

        else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            if (execution.equalsIgnoreCase("local")) {

                driver.set(new EdgeDriver(options));

            } else {

                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));

            }

        }

        else {

            throw new RuntimeException("Invalid Browser : " + browser);

        }

        getDriver().manage().window().maximize();
        getDriver().get(reader.getUrl());
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {

        if (getDriver() != null) {

            Thread.sleep(2000);
            getDriver().quit();
            driver.remove();

        }

    }

}