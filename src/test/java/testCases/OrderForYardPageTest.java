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
import pages.OrderForYardPage;
import pages.PlaceOrderPage;
import util.Utilities;

public class OrderForYardPageTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    OrderForYardPage orderForYardPage;
    PlaceOrderPage placeOrderPage;

    public OrderForYardPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        loginPage.switchToFrame();
        loginPage.clickSigninButton();
        homePage = loginPage.enterLoginCredentials(prop.getProperty("emailId"), prop.getProperty("password"));
        orderForYardPage = homePage.clickOnOrderForYard();
        placeOrderPage = new PlaceOrderPage();
    }

    /**
     * Test Case 1: Verify Order For Yard Page Elements
     * - Ensures that all required UI elements are present on the Order For Yard page
     * - Checks visibility of header, delivery date label, delivery time label, and select products label
     */
    @Test(priority = 1)
    public void testOrderForYardPageElements() {
        // Assert that the header is displayed
        Assert.assertTrue(orderForYardPage.isHeaderDisplayed(), "Order For Yard Header is not displayed");

        // Assert that the Delivery Date label is displayed
        Assert.assertTrue(orderForYardPage.isDeliveryDateLabelDisplayed(), "Delivery Date Label is not displayed");

        // Assert that the Delivery Time label is displayed
        Assert.assertTrue(orderForYardPage.isDeliveryTimeLabelDisplayed(), "Delivery Time Label is not displayed");

        // Assert that the Select Products label is displayed
        Assert.assertTrue(orderForYardPage.isSelectProductsLabelDisplayed(), "Select Products Label is not displayed");

        // Assert that the Delete Selection button is displayed
        Assert.assertTrue(orderForYardPage.isDeleteSelectionButtonDisplayed(), "Delete Selection Button is not displayed");

        // Capture screenshot for report
        String pageElementsScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(pageElementsScreenshot, "Order For Yard Page Elements Screenshot");
    }

    /**
     * Test Case 2: Test Place Order For Yard
     * - Tests the complete process of placing an order for yard
     * - Selects delivery time and product
     * - Inputs quantity for selected product
     * - Submits the order by clicking Place Order button
     * - Verifies success notification
     * - Verifies navigation to Dashboard page
     */
    @Test(priority = 2)
    public void testPlaceOrderForYard() {
        // Click on delivery time dropdown
        orderForYardPage.clickDeliveryTimeDropdown();
        
        // Select the first time option
        orderForYardPage.selectFirstTimeOption();
        
        // Click on product selection dropdown
        orderForYardPage.clickProductSelectionDropdown();

        // Select the first product option
        orderForYardPage.selectFirstProductOption();

        // Input quantity for the selected product
        orderForYardPage.inputQuantity(prop.getProperty("orderForYardQuantity"));

        // Click Place Order button to submit the order
        DashboardPage dashboardPage = orderForYardPage.clickPlaceOrderButton();

        // Verify success notification is displayed
        Assert.assertTrue(orderForYardPage.isOrderSuccessNotificationDisplayed(), "Order success notification not displayed");

        // Capture screenshot of success notification
        String successNotificationScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(successNotificationScreenshot, "Order Success Notification Screenshot");

        // Verify navigation to Dashboard page
        Assert.assertTrue(dashboardPage.isHeaderDisplayed(), "Navigation to Dashboard page failed");

        // Capture screenshot of dashboard page
        String dashboardScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(dashboardScreenshot, "Dashboard Navigation Screenshot");
    }

    /**
     * Test Case 3: Test Delete Selection Functionality
     * - Tests the functionality of deleting selected products
     * - Selects a product from dropdown
     * - Selects the product using checkbox
     * - Deletes the selection
     * - Verifies empty list message
     */
    @Test(priority = 3)
    public void testDeleteSelection() {
        // Click on product selection dropdown
        orderForYardPage.clickProductSelectionDropdown();

        // Select the first product option
        orderForYardPage.selectFirstProductOption();

        // Click on Select All Products checkbox
        orderForYardPage.clickSelectAllProductsCheckbox();

        // Verify checkbox is selected
        Assert.assertTrue(orderForYardPage.isSelectAllProductsCheckboxSelected(), "Select All Products checkbox is not selected");

        // Capture screenshot after selecting product and checkbox
        String selectionScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(selectionScreenshot, "Product Selected and Checkbox Checked Screenshot");

        // Click Delete Selection button
        orderForYardPage.clickDeleteSelectionButton();

        // Verify empty list message is displayed
        Assert.assertTrue(orderForYardPage.isEmptyListMessageDisplayed(), "Empty list message is not displayed");

        // Verify checkbox is not selected after deletion
        Assert.assertFalse(orderForYardPage.isSelectAllProductsCheckboxSelected(), "Select All Products checkbox is still selected after deletion");

        // Capture screenshot after deletion showing empty list message
        String deletionScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(deletionScreenshot, "Empty Product List After Deletion Screenshot");
    }

    /**
     * Test Case 4: Verify Order Appears in Today's Deliveries
     * - Places an order for yard
     * - Navigates to Dashboard
     * - Verifies the order appears in Today's Deliveries dropdown as "Restock Inventory"
     * - Finds and clicks the matching Restock Inventory entry by date/time
     */
    @Test(priority = 4)
    public void testOrderAppearsInTodaysDeliveries() {
        // Get current CST time before placing order
        java.util.TimeZone cst = java.util.TimeZone.getTimeZone("America/Chicago");
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm a");
        dateFormat.setTimeZone(cst);
        timeFormat.setTimeZone(cst);
        String expectedDate = dateFormat.format(new java.util.Date());
        String expectedTime = timeFormat.format(new java.util.Date());
        System.out.println("Expected CST Date: " + expectedDate);
        System.out.println("Expected CST Time: " + expectedTime);
        
        // Click on delivery time dropdown and select first time option
        orderForYardPage.clickDeliveryTimeDropdown();
        orderForYardPage.selectFirstTimeOption();
        
        // Click on product selection dropdown and select first product
        orderForYardPage.clickProductSelectionDropdown();
        orderForYardPage.selectFirstProductOption();
        
        // Input quantity
        orderForYardPage.inputQuantity(prop.getProperty("orderForYardQuantity"));
        
        // Place the order and get dashboard page
        DashboardPage dashboardPage = orderForYardPage.clickPlaceOrderButton();
        
        // Verify order success notification
        Assert.assertTrue(orderForYardPage.isOrderSuccessNotificationDisplayed(), 
            "Order success notification not displayed");
        
        // Verify navigation to Dashboard page
        Assert.assertTrue(dashboardPage.isHeaderDisplayed(), 
            "Navigation to Dashboard page failed");
            
        // Get initial count from Today's Deliveries
        int todaysDeliveriesCount = dashboardPage.returnTodaysDeliveriesCount();
        System.out.println("Today's Deliveries count: " + todaysDeliveriesCount);
        
        // Click Today's Deliveries dropdown
        dashboardPage.clickOnTodaysDeliveriesDropdown();
        
        // Capture screenshot before clicking any Restock Inventory entry
        String beforeClickScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(beforeClickScreenshot, 
            "Today's Deliveries Dropdown Open");
            
        // Find and click the matching Restock Inventory entry
        Assert.assertTrue(dashboardPage.findAndClickRestockInventory(expectedDate, expectedTime),
            "Could not find matching Restock Inventory entry with expected date/time");
        
        // Wait briefly for any animations or loading
        Utilities.sleep(1);
        
        // Capture screenshot of the matching entry's details
        String afterClickScreenshot = Utilities.captureScreenshotAsBase64();
        ExtentReporterNG.test.addScreenCaptureFromBase64String(afterClickScreenshot, 
            "Matching Restock Inventory Details");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
