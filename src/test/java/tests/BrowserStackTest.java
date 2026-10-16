package tests;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;

public class BrowserStackTest {

    private static final String URL =
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    // ============================================================
    // 1. SINGLE BROWSER EXECUTION - CHROME + WINDOWS 11
    // ============================================================

    @Test
    public void browserStackLoginTest() throws Exception {

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        MutableCapabilities options = new MutableCapabilities();

        options.setCapability("browserName", "Chrome");
        options.setCapability("browserVersion", "latest");

        HashMap<String, Object> bstackOptions = new HashMap<>();

        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("projectName", "BrowserStack Learning");
        bstackOptions.put("buildName", "Login Test");
        bstackOptions.put("sessionName", "OrangeHRM Login - Chrome");

        options.setCapability("bstack:options", bstackOptions);

        String browserStackUrl =
                "https://" + username + ":" + accessKey
                        + "@hub.browserstack.com/wd/hub";

        WebDriver driver = new RemoteWebDriver(
                new URL(browserStackUrl),
                options);

        try {

            driver.get(URL);

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login("Admin", "admin123");

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/dashboard"),
                    "User was not redirected to Dashboard");

            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                            "\"arguments\": {\"status\":\"passed\", " +
                            "\"reason\":\"Chrome login test passed successfully\"}}"
            );

            System.out.println(
                    "Chrome login successful on BrowserStack!");

        } finally {

            driver.quit();
        }
    }


    // ============================================================
    // 2. PARALLEL CROSS-BROWSER EXECUTION
    //    CHROME + FIREFOX + EDGE
    // ============================================================

    @DataProvider(name = "parallelBrowsers", parallel = true)
    public Object[][] parallelBrowsers() {

        return new Object[][]{
                {"Chrome"},
                {"Firefox"},
                {"MicrosoftEdge"}
        };
    }

    @Test(dataProvider = "parallelBrowsers")
    public void parallelBrowserTest(String browser) throws Exception {

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        MutableCapabilities options = new MutableCapabilities();

        options.setCapability("browserName", browser);
        options.setCapability("browserVersion", "latest");

        HashMap<String, Object> bstackOptions = new HashMap<>();

        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("projectName", "BrowserStack Learning");
        bstackOptions.put("buildName", "Parallel Browser Test");
        bstackOptions.put(
                "sessionName",
                "OrangeHRM Login - " + browser
        );

        options.setCapability("bstack:options", bstackOptions);

        String browserStackUrl =
                "https://" + username + ":" + accessKey
                        + "@hub.browserstack.com/wd/hub";

        WebDriver driver = new RemoteWebDriver(
                new URL(browserStackUrl),
                options);

        try {

            driver.get(URL);

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login("Admin", "admin123");

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/dashboard"),
                    browser + " login failed");

            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                            "\"arguments\": {\"status\":\"passed\", " +
                            "\"reason\":\"" + browser +
                            " login test passed successfully\"}}"
            );

            System.out.println(
                    browser +
                            " login successful on BrowserStack!");

        } finally {

            driver.quit();
        }
    }


    // ============================================================
    // 3. DIFFERENT OS EXECUTION
    //    CHROME + macOS SONOMA
    // ============================================================

    @Test
    public void chromeOnMacTest() throws Exception {

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        MutableCapabilities options = new MutableCapabilities();

        options.setCapability("browserName", "Chrome");
        options.setCapability("browserVersion", "latest");

        HashMap<String, Object> bstackOptions = new HashMap<>();

        bstackOptions.put("os", "OS X");
        bstackOptions.put("osVersion", "Sonoma");
        bstackOptions.put("projectName", "BrowserStack Learning");
        bstackOptions.put("buildName", "OS Test");
        bstackOptions.put("sessionName", "Chrome on Mac");

        options.setCapability("bstack:options", bstackOptions);

        String browserStackUrl =
                "https://" + username + ":" + accessKey
                        + "@hub.browserstack.com/wd/hub";

        WebDriver driver = new RemoteWebDriver(
                new URL(browserStackUrl),
                options);

        try {

            driver.get(URL);

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login("Admin", "admin123");

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/dashboard"),
                    "User was not redirected to Dashboard");

            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", " +
                            "\"arguments\": {\"status\":\"passed\", " +
                            "\"reason\":\"Chrome on macOS login test passed successfully\"}}"
            );

            System.out.println(
                    "Chrome + macOS login successful on BrowserStack!");

        } finally {

            driver.quit();
        }
    }
}