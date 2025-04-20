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
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Optional metadata
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Vikas Kelageri");
        extent.setSystemInfo("Tool", "REST Assured");
    }

    // Flush the report (call this in @AfterClass or @AfterSuite)
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
