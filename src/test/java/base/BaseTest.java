package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.ConfigReader;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {

        ConfigReader reader = new ConfigReader();

        String browser = reader.getBrowser();
        boolean headless = reader.isHeadless();

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            driver.set(new ChromeDriver(options));

        }

        else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            driver.set(new FirefoxDriver(options));

        }

        else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            driver.set(new EdgeDriver(options));

        }

        else {

            throw new RuntimeException("Invalid Browser : " + browser);

        }

        getDriver().manage().window().maximize();
        getDriver().get(reader.getUrl());

    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {

            getDriver().quit();
            driver.remove();

        }

    }

}