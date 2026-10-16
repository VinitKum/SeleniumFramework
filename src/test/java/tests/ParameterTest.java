package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    @Test
    @Parameters("browser")
    public void launchBrowser(String browser) {
        System.out.println("Browser received from TestNG XML: " + browser);
    }
}