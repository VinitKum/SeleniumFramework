package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getReport()
    {
        if(extent == null)
        {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Vinit Kumar");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }

        return extent;
    }
}