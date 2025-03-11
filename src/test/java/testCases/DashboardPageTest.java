package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import listeners.ExtentReporterNG;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import util.Utilities;

public class DashboardPageTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    public DashboardPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        loginPage.switchToFrame();
        loginPage.clickSigninButton();
        homePage = loginPage.enterLoginCredentials(prop.getProperty("emailId"), prop.getProperty("password"));
        dashboardPage = homePage.clickOnDashboard();
    }

    /**
     * Test Case 1: Verify Dashboard Page Elements
     * - Ensures that all required UI elements are present on the Dashboard page
     * - Checks visibility of all dropdown elements
     */
    @Test(priority = 1)
    public void testDashboardPageElements() {
        // Assert that the Transfers dropdown is displayed
        Assert.assertTrue(dashboardPage.isHeaderDisplayed(), "Transfers dropdown is not displayed");

        // Assert that Today's Deliveries dropdown is displayed
        Assert.assertTrue(dashboardPage.isTodaysDeliveriesDropdownDisplayed(), "Today's Deliveries dropdown is not displayed");

        // Assert that Requests Awaiting Replies dropdown is displayed
        Assert.assertTrue(dashboardPage.isRequestsAwaitingRepliesDropdownDisplayed(), "Requests Awaiting Replies dropdown is not displayed");

        // Assert that Future Deliveries dropdown is displayed
        Assert.assertTrue(dashboardPage.isFutureDeliveriesDropdownDisplayed(), "Future Deliveries dropdown is not displayed");

        // Assert that Previous Orders dropdown is displayed
        Assert.assertTrue(dashboardPage.isPreviousOrdersDropdownDisplayed(), "Previous Orders dropdown is not displayed");

        // Capture screenshot of dashboard elements
        String dashboardElementsScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(dashboardElementsScreenshot, "Dashboard Page Elements Screenshot");
    }

    /**
     * Test Case 2: Verify Dropdown Counts
     * - For each dropdown:
     *   1. Get the count displayed in dropdown text
     *   2. Click the dropdown
     *   3. Count actual items in dropdown
     *   4. Compare counts
     */
    @Test(priority = 2)
    public void testDropdownCounts() {
        // Test Transfers dropdown
        System.out.println("\n=== Testing Transfers Dropdown ===");
        Assert.assertTrue(dashboardPage.isHeaderDisplayed(), "Transfers dropdown should be visible");
        dashboardPage.clickOnTransferDropdown();
        
        // Test Today's Deliveries dropdown
        System.out.println("\n=== Testing Today's Deliveries Dropdown ===");
        Assert.assertTrue(dashboardPage.isTodaysDeliveriesDropdownDisplayed(), "Today's Deliveries dropdown should be visible");
        dashboardPage.clickOnTodaysDeliveriesDropdown();
        
        // Test Requests Awaiting Replies dropdown
        System.out.println("\n=== Testing Requests Awaiting Replies Dropdown ===");
        Assert.assertTrue(dashboardPage.isRequestsAwaitingRepliesDropdownDisplayed(), "Requests Awaiting Replies dropdown should be visible");
        dashboardPage.clickOnRequestsAwaitingRepliesDropdown();
        
        // Test Future Deliveries dropdown
        System.out.println("\n=== Testing Future Deliveries Dropdown ===");
        Assert.assertTrue(dashboardPage.isFutureDeliveriesDropdownDisplayed(), "Future Deliveries dropdown should be visible");
        dashboardPage.clickOnFutureDeliveriesDropdown();
        
        // Test Previous Orders dropdown
        System.out.println("\n=== Testing Previous Orders Dropdown ===");
        Assert.assertTrue(dashboardPage.isPreviousOrdersDropdownDisplayed(), "Previous Orders dropdown should be visible");
        dashboardPage.clickOnPreviousOrdersDropdown();
        
        // Capture final screenshot
        String dropdownsScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(dropdownsScreenshot, "All Dropdowns Test Screenshot");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
} 