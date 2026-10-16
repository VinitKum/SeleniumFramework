package listeners;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utilities.AllureUtil;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

public class ExtentListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔥 EXTENT: onTestStart -> " + result.getName());

        test.set(extent.createTest(result.getName()));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("🔥 EXTENT: onTestSuccess -> " + result.getName());
        test.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("========= onTestFailure =========");

        test.get().fail(result.getThrowable());

        BaseTest base = (BaseTest) result.getInstance();

        // Allure
        AllureUtil.attachScreenshot(base.getDriver());

        try {

            String path = ScreenshotUtil.capture(
                    base.getDriver(),
                    result.getName()
            );

            // Extent
            test.get().addScreenCaptureFromPath(path);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🔥 EXTENT: onFinish -> FLUSHING REPORT");
        extent.flush();
        test.remove();

    }

}