package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.Utilities;

public class ExtentReporterNG implements ITestListener {

    ExtentReports extent;
    public static ExtentTest test;  // Make ExtentTest accessible in other classes

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // Initialize ExtentTest at the start
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.fail(result.getThrowable());
        // Capture the screenshot when a test fails and attach it to the report
        String base64Screenshot = Utilities.captureScreenshotAsBase64();
        // Attach the screenshot to the Extent Report
        test.addScreenCaptureFromBase64String(base64Screenshot, "Test Failure Screenshot");
        
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Specify the report path under the test-output folder
        String reportPath = Paths.get(System.getProperty("user.dir"), "test-output", "extentReport.html").toString();

        // Create an ExtentSparkReporter for generating the report
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Test Results");
        reporter.config().setDocumentTitle("Test Results");

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Abhishek");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Ensure all logs are written to the report
    }
}