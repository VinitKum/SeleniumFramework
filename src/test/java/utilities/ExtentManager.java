package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getReport() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("OrangeHRM Automation");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Vinit Kumar");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}