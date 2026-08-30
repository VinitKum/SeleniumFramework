package utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentCucumberManager {

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void removeTest() {
        extentTest.remove();
    }
}