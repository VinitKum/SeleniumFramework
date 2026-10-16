package utilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;

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

        // =========================================================
        // CHROME
        // =========================================================

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            // ---------------- LOCAL ----------------
            if (execution.equalsIgnoreCase("local")) {

                // EXISTING CODE - UNCHANGED
                driver.set(new ChromeDriver(options));

            }

            // ---------------- SELENIUM GRID ----------------
            else if (execution.equalsIgnoreCase("grid")) {

                // EXISTING GRID BEHAVIOR
                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));
            }

            // ---------------- BROWSERSTACK ----------------
            else if (execution.equalsIgnoreCase("browserstack")) {

                driver.set(createBrowserStackDriver(
                        "Chrome",
                        options,
                        "Windows",
                        "11"));
            }

            else {

                throw new RuntimeException(
                        "Invalid Execution Mode : " + execution);
            }
        }

        // =========================================================
        // FIREFOX
        // =========================================================

        else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            // ---------------- LOCAL ----------------
            if (execution.equalsIgnoreCase("local")) {

                // EXISTING CODE - UNCHANGED
                driver.set(new FirefoxDriver(options));

            }

            // ---------------- SELENIUM GRID ----------------
            else if (execution.equalsIgnoreCase("grid")) {

                // EXISTING GRID BEHAVIOR
                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));
            }

            // ---------------- BROWSERSTACK ----------------
            else if (execution.equalsIgnoreCase("browserstack")) {

                driver.set(createBrowserStackDriver(
                        "Firefox",
                        options,
                        "Windows",
                        "11"));
            }

            else {

                throw new RuntimeException(
                        "Invalid Execution Mode : " + execution);
            }
        }

        // =========================================================
        // EDGE
        // =========================================================

        else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            // ---------------- LOCAL ----------------
            if (execution.equalsIgnoreCase("local")) {

                // EXISTING CODE - UNCHANGED
                driver.set(new EdgeDriver(options));

            }

            // ---------------- SELENIUM GRID ----------------
            else if (execution.equalsIgnoreCase("grid")) {

                // EXISTING GRID BEHAVIOR
                driver.set(new RemoteWebDriver(
                        new URL(gridUrl),
                        options));
            }

            // ---------------- BROWSERSTACK ----------------
            else if (execution.equalsIgnoreCase("browserstack")) {

                driver.set(createBrowserStackDriver(
                        "Edge",
                        options,
                        "Windows",
                        "11"));
            }

            else {

                throw new RuntimeException(
                        "Invalid Execution Mode : " + execution);
            }
        }

        else {

            throw new RuntimeException(
                    "Invalid Browser : " + browser);
        }

        getDriver().manage().window().maximize();
    }


    // =============================================================
    // BROWSERSTACK DRIVER
    // =============================================================

    private static WebDriver createBrowserStackDriver(
            String browserName,
            MutableCapabilities browserOptions,
            String os,
            String osVersion)
            throws MalformedURLException {

        String username =
                System.getenv("BROWSERSTACK_USERNAME");

        String accessKey =
                System.getenv("BROWSERSTACK_ACCESS_KEY");

        if (username == null || username.isBlank()) {

            throw new RuntimeException(
                    "BROWSERSTACK_USERNAME environment variable is not set.");
        }

        if (accessKey == null || accessKey.isBlank()) {

            throw new RuntimeException(
                    "BROWSERSTACK_ACCESS_KEY environment variable is not set.");
        }

        HashMap<String, Object> bstackOptions =
                new HashMap<>();

        bstackOptions.put("os", os);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("projectName", "Selenium Automation Framework");
        bstackOptions.put("buildName", "BrowserStack Build");
        bstackOptions.put("sessionName", browserName + " Test");

        browserOptions.setCapability(
                "browserName",
                browserName);

        browserOptions.setCapability(
                "browserVersion",
                "latest");

        browserOptions.setCapability(
                "bstack:options",
                bstackOptions);

        String browserStackUrl =
                "https://" + username
                        + ":" + accessKey
                        + "@hub.browserstack.com/wd/hub";

        return new RemoteWebDriver(
                new URL(browserStackUrl),
                browserOptions);
    }


    // =============================================================
    // QUIT DRIVER
    // =============================================================

    public static void quitDriver() {

        if (getDriver() != null) {

            getDriver().quit();
            driver.remove();
        }
    }
}