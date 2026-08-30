package utilities;

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

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver(
            String browser,
            String execution,
            boolean headless,
            String gridUrl)
            throws MalformedURLException {

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

            throw new RuntimeException(
                    "Invalid Browser : " + browser);
        }

        getDriver().manage().window().maximize();
    }

    public static void quitDriver() {

        if (getDriver() != null) {

            getDriver().quit();
            driver.remove();
        }
    }
}