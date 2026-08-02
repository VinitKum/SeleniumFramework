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

import utilities.ConfigReader;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() throws IOException, MalformedURLException {

        ConfigReader reader = new ConfigReader();

        String browser = reader.getBrowser();
        String execution = reader.getExecutionMode();
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
                        new URL("http://localhost:4444"),
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
                        new URL("http://localhost:4444"),
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
                        new URL("http://localhost:4444"),
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