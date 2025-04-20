package listeners;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.*;
import utils.ExtentReportManager;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.test = ExtentReportManager.extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.test.log(Status.PASS, "Test Passed");
        logResponseFromAttribute(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        logResponseFromAttribute(result);
    }

    private void logResponseFromAttribute(ITestResult result) {
        Object responseObj = result.getAttribute("response");
        if (responseObj instanceof Response) {
            Response response = (Response) responseObj;
            ExtentReportManager.test.log(Status.INFO, "Status Code: " + response.getStatusCode());
            ExtentReportManager.test.log(Status.INFO, "Response Body: " + response.getBody().asPrettyString());
        }
    }
}
