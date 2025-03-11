package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import base.TestBase;
import listeners.ExtentReporterNG;
import pages.HomePage;
import pages.LoginPage;
import pages.PlaceOrderPage;
import util.Utilities;

public class PlaceOrderPageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	PlaceOrderPage placeOrderPage;

	public PlaceOrderPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		loginPage.switchToFrame();
		loginPage.clickSigninButton();
		homePage = loginPage.enterLoginCredentials(prop.getProperty("emailId"), prop.getProperty("password"));
		homePage.selectJob();
		placeOrderPage = homePage.clickOnPlaceOrder();
	}

	/**
	 * Test Case 1: Verify Place Order Page Elements
	 * - Ensures that all required UI elements are present on the Place Order page.
	 * - Checks visibility of job, delivery date, vendor delivery time, and yard fields.
	 */
	@Test(priority = 1)
	public void testPlaceOrderPageElements() {
		// Assert that the "Place Order Header" is displayed
		Assert.assertTrue(placeOrderPage.isHeaderDisplayed(), "Place Order Header is not displayed");

		// Assert that the "Job Field" is displayed
		Assert.assertTrue(placeOrderPage.isJobFieldDisplayed(), "Job Field is not displayed");

		// Assert that the "Delivery Date Field" is displayed
		Assert.assertTrue(placeOrderPage.isDeliveryDateFieldDisplayed(), "Delivery Date Field is not displayed");

		// Assert that the "Vendor Delivery Time Field" is displayed
		Assert.assertTrue(placeOrderPage.isVendorDeliveryTimeFieldDisplayed(),
				"Vendor Delivery Time Field is not displayed");

		// Assert that the "Deliver to Yard Field" is displayed
		Assert.assertTrue(placeOrderPage.isDeliverToYardFieldDisplayed(), "Deliver to Yard Field is not displayed");
	}

	/**
	 * Test Case 2: Place an Order from a Vendor
	 * - Selects a product from the vendor.
	 * - Enters the required quantity.
	 * - Proceeds to review and places the order.
	 * - Verifies that the success notification appears.
	 */
	@Test(priority = 2)
	public void testPlaceOrderFromVendor() {
		placeOrderPage.clickProductsDropdown();
		placeOrderPage.selectFirstProduct();
		placeOrderPage.enterQuantity(prop.getProperty("productQuantityToOrder"));
		placeOrderPage.clickReviewOrderButton();
		placeOrderPage.clickPlaceOrderButton();
		Assert.assertTrue(placeOrderPage.isPlaceOrderSuccessNotificationDisplayed());
		String selectedJobScreenshot = Utilities.captureScreenshotAsBase64();
		ExtentReporterNG.test.addScreenCaptureFromBase64String(selectedJobScreenshot,
				"Order Placed From Vendor Screenshot");
	}

	/**
	 * Test Case 3: Place an Order from Yard with Self-Delivery
	 * - Selects a product from the yard.
	 * - Enters quantity and selects the self-delivery option.
	 * - Proceeds to review and places the order.
	 * - Verifies that the success notification appears.
	 */
	@Test(priority = 3)
	public void testPlaceOrderFromYardWithSelfDelivery() {
		placeOrderPage.clickProductsDropdown();
		placeOrderPage.searchAndSelectYardProduct(prop.getProperty("yardProduct"));
		placeOrderPage.enterQuantity(prop.getProperty("productQuantityToOrder"));
		placeOrderPage.checkOrderFromYardCheckbox();
		placeOrderPage.clickReviewOrderButton();
		placeOrderPage.clickPlaceOrderButton();
		Assert.assertTrue(placeOrderPage.isPlaceOrderSuccessNotificationDisplayed());
		String selectedJobScreenshot = Utilities.captureScreenshotAsBase64();
		ExtentReporterNG.test.addScreenCaptureFromBase64String(selectedJobScreenshot,
				"Order Placed From Yard With Self Delivery Screenshot");

	}

	/**
	 * Test Case 4: Place an Order from Yard with Truck Delivery
	 * - Selects a product from the yard.
	 * - Enters the quantity and chooses the truck delivery option.
	 * - Selects an available truck and time slot.
	 * - Proceeds to review and places the order.
	 * - Verifies that the success notification appears.
	 */
	@Test(priority = 4)
	public void testPlaceOrderFromYardWithTruckDelivery() {
		placeOrderPage.clickProductsDropdown();
		placeOrderPage.searchAndSelectYardProduct(prop.getProperty("yardProduct"));
		placeOrderPage.enterQuantity(prop.getProperty("productQuantityToOrder"));
		placeOrderPage.checkOrderFromYardCheckbox();
		placeOrderPage.clickReviewOrderButton();
		placeOrderPage.selectSelfDeliveryOption("NO");

		int truckRow = Integer.parseInt(prop.getProperty("truckNumber"));
		String initialTime = prop.getProperty("truckDeliveryTime");

		System.out.println(
				"🚛 Searching for available delivery time starting at " + initialTime + " for Truck " + truckRow);

		placeOrderPage.selectTruckDeliveryTime(truckRow, initialTime);
		placeOrderPage.clickPlaceOrderButton();
		Assert.assertTrue(placeOrderPage.isPlaceOrderSuccessNotificationDisplayed());

		String selectedJobScreenshot = Utilities.captureScreenshotAsBase64();
		ExtentReporterNG.test.addScreenCaptureFromBase64String(selectedJobScreenshot,
				"✅ Order Placed From Yard With Truck Delivery Screenshot");
	}

	/**
	 * Test Case 5: Place a Pickup Order
	 * - Selects a pickup product.
	 * - Selects a truck and an available time slot.
	 * - Proceeds to review and places the order.
	 * - Verifies that the success notification appears.
	 */
	@Test(priority = 5)
	public void testPlacePickupOrder() {
		placeOrderPage.clickProductsDropdown();
		placeOrderPage.searchAndSelectYardProduct(prop.getProperty("pickUp"));
		placeOrderPage.clickReviewOrderButton();
		int truckRow = Integer.parseInt(prop.getProperty("truckNumber"));
		String initialTime = prop.getProperty("truckDeliveryTime");

		System.out.println(
				"🚛 Searching for available delivery time starting at " + initialTime + " for Truck " + truckRow);
		placeOrderPage.selectTruckDeliveryTime(truckRow, initialTime);
		placeOrderPage.clickPlaceOrderButton();
		Assert.assertTrue(placeOrderPage.isPlaceOrderSuccessNotificationDisplayed());

		String selectedJobScreenshot = Utilities.captureScreenshotAsBase64();
		ExtentReporterNG.test.addScreenCaptureFromBase64String(selectedJobScreenshot,
				"✅ Order Placed For Pickup Screenshot");
	}

	/**
	 * Test Case 6: Place Orders from Vendor, Yard, and Pickup Together
	 * - Selects a vendor product, a yard product, and a pickup product.
	 * - Enters the required quantity.
	 * - Selects a truck and an available time slot.
	 * - Proceeds to review and places all orders together.
	 * - Verifies that the success notification appears.
	 */
	@Test(priority = 6)
	public void testPlaceVendorYardPickupOrdersTogether() {
		placeOrderPage.clickProductsDropdown();
		placeOrderPage.selectFirstProduct();
		placeOrderPage.enterQuantity(prop.getProperty("productQuantityToOrder"));
		placeOrderPage.searchAndSelectYardProduct(prop.getProperty("yardProduct"));
		placeOrderPage.enterQuantity(prop.getProperty("productQuantityToOrder"));
		placeOrderPage.checkOrderFromYardCheckbox();
		placeOrderPage.searchAndSelectYardProduct(prop.getProperty("pickUp"));
		placeOrderPage.clickReviewOrderButton();
		int truckRow = Integer.parseInt(prop.getProperty("truckNumber"));
		String initialTime = prop.getProperty("truckDeliveryTime");

		System.out.println(
				"🚛 Searching for available delivery time starting at " + initialTime + " for Truck " + truckRow);
		Utilities.sleep(5);
		 // Use JavaScriptExecutor to scroll to the bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		placeOrderPage.selectTruckDeliveryTime(truckRow, initialTime);
		placeOrderPage.clickPlaceOrderButton();
		Assert.assertTrue(placeOrderPage.isPlaceOrderSuccessNotificationDisplayed());

		String selectedJobScreenshot = Utilities.captureScreenshotAsBase64();
		ExtentReporterNG.test.addScreenCaptureFromBase64String(selectedJobScreenshot,
				"✅ Orders Placed From Vendor, Yard And Pickup Together Screenshot");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
