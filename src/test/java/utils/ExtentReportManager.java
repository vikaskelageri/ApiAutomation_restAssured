package utils; // optional package name

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    // Static ExtentReports and ExtentTest objects
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initialize the report (call this in @BeforeClass or @BeforeSuite)
    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Optional metadata
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Vikas Kelageri");
        extent.setSystemInfo("Tool", "REST Assured");
    }

    // Flush the report (call this in @AfterClass or @AfterSuite)
//    Write Data to Output: During test execution, test result
//    (pass/fail/skip status, logs, screenshots, etc.) are added
//    to the Extent Reports object in memory. The flush() operation writes this
//    accumulated data from memory to the output destination, which is usually an HTML file.
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
