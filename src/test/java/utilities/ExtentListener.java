package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.AllureUtil;

import base.BaseTest;

public class ExtentListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test.set(extent.createTest(result.getName()));

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
    	 System.out.println("========= onTestFailure =========");
        test.get().fail(result.getThrowable());

        BaseTest base = (BaseTest) result.getInstance();

        // Attach Screenshot to Allure
        AllureUtil.attachScreenshot(base.getDriver());

        try {

            String path = ScreenshotUtil.capture(base.getDriver(), result.getName());

            test.get().addScreenCaptureFromPath(path);

        }

        catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
        test.remove();

    }

}