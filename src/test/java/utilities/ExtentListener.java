package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;

public class ExtentListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        BaseTest base = (BaseTest) result.getInstance();

        String path = null;
		try {
			path = ScreenshotUtil.capture(base.getDriver(),
			                       result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}