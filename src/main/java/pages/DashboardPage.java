package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import util.Utilities;

public class DashboardPage extends BasePage {
	
	@FindBy(xpath = "//span[text()='Transfers']")
	WebElement transfersDropdown;

	@FindBy(xpath = "//span[contains(text(), \"Today's Deliveries\")]")
	WebElement todaysDeliveriesDropdown;

	@FindBy(xpath = "//span[contains(text(), 'Requests Awaiting Replies')]")
	WebElement requestsAwaitingRepliesDropdown;

	@FindBy(xpath = "//span[contains(text(), 'Future Deliveries')]")
	WebElement futureDeliveriesDropdown;

	@FindBy(xpath = "//span[contains(text(), 'Previous Orders')]")
	WebElement previousOrdersDropdown;

	@FindBy(xpath = "//div[@data-testid='underlay']")
	WebElement underlay;

	// Locators for dropdown items
	private static final String DROPDOWN_ITEMS = "//div[contains(@class, 'absolute bg-white')]//div[contains(@class, 'px-1')]";
	private static final String RESTOCK_INVENTORY_ITEMS = "//div[contains(@class, 'absolute bg-white')]//div[contains(@class, 'px-1')][.//span[text()='Restock Inventory']]";
	private static final String ORDER_DATE_FIELD = "//td[@data-key='react-aria-10' and contains(text(), 'Order Date:')]";
	private static final String ORDER_TIME_FIELD = "//td[@data-key='react-aria-13' and contains(text(), 'Order Time:')]";
	private static final String ORDER_DATE_VALUE = "//td[contains(text(), 'Order Date:')]/following-sibling::td";
	private static final String ORDER_TIME_VALUE = "//td[contains(text(), 'Order Time:')]/following-sibling::td";
	
	/*
	 * Sets implicit wait to 0
	 */
	private void setZeroImplicitWait() {
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(0));
	}

	/**
	 * Restores implicit wait to default value from Utilities class
	 */
	private void restoreImplicitWait() {
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
	}

	/**
	 * Clicks element with zero implicit wait and checks dropdown items
	 * @param element The element to click
	 */
	private void clickWithZeroWait(WebElement element) {
		try {
			// Get the expected count before clicking
			int expectedCount = getDropdownCount(element);
			
			// Wait for dropdown to be visible before clicking
			waitForElementVisibility(element, 10);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			
			// Wait and retry strategy for finding items
			int actualCount = 0;
			int maxAttempts = 3;  // Reduced from 5 to 3
			
			for (int attempt = 0; attempt < maxAttempts; attempt++) {
				Thread.sleep(200); // Reduced wait between attempts
				List<WebElement> items = driver.findElements(By.xpath(DROPDOWN_ITEMS));
				if (items.size() > actualCount) {
					actualCount = items.size();
				}
				// If we've found at least as many items as expected, we can stop
				if (actualCount >= expectedCount) {
					break;
				}
			}
			
			// Log the comparison
			System.out.println("Expected count from dropdown text: " + expectedCount);
			System.out.println("Actual items found in dropdown: " + actualCount);
			
			if (actualCount != expectedCount) {
				System.out.println("Warning: Mismatch between displayed count and actual items!");
			}
			
		} catch (Exception e) {
			System.out.println("Error in clickWithZeroWait: " + e.getMessage());
		}
	}

	/**
	 * Gets the count from a dropdown element
	 * @param element The dropdown element
	 * @return The count value, or 0 if no count found
	 */
	private int getDropdownCount(WebElement element) {
		try {
			waitForElementVisibility(element, 10);
			String text = element.getText();
			int start = text.indexOf('(') + 1;
			int end = text.indexOf(')');
			if (start > 0 && end > start) {
				String countText = text.substring(start, end);
				return Integer.parseInt(countText);
			}
		} catch (Exception e) {
			System.out.println("Error getting count: " + e.getMessage());
		}
		return 0;
	}
	
	public int returnTransfersCount() {
		return getDropdownCount(transfersDropdown);
	}
	
	public int returnTodaysDeliveriesCount() {
		return getDropdownCount(todaysDeliveriesDropdown);
	}
	
	public int returnRequestsAwaitingrepliesCount() {
		return getDropdownCount(requestsAwaitingRepliesDropdown);
	}
	
	public int returnFutureDeliveriesCount() {
		return getDropdownCount(futureDeliveriesDropdown);
	}
	
	public int returnPreviousDeliveriesCount() {
		return getDropdownCount(previousOrdersDropdown);
	}
	

	public void clickOnTransferDropdown() {
		clickWithZeroWait(transfersDropdown);
	}

	public void clickOnTodaysDeliveriesDropdown() {
		clickWithZeroWait(todaysDeliveriesDropdown);
	}
	
	public void clickOnRequestsAwaitingRepliesDropdown() {
		clickWithZeroWait(requestsAwaitingRepliesDropdown);
	}

	public void clickOnFutureDeliveriesDropdown() {
		clickWithZeroWait(futureDeliveriesDropdown);
	}

	public void clickOnPreviousOrdersDropdown() {
		clickWithZeroWait(previousOrdersDropdown);
	}
	
	// Original display verification methods
	@Override
	public boolean isHeaderDisplayed() {
		waitForElementVisibility(transfersDropdown, 10);
		return transfersDropdown.isDisplayed();
	}

	/**
	 * Checks if Today's Deliveries dropdown is displayed
	 * @return true if the today's deliveries dropdown is visible, false otherwise
	 */
	public boolean isTodaysDeliveriesDropdownDisplayed() {
		waitForElementVisibility(todaysDeliveriesDropdown, 10);
		return todaysDeliveriesDropdown.isDisplayed();
	}

	/**
	 * Checks if Requests Awaiting Replies dropdown is displayed
	 * @return true if the requests awaiting replies dropdown is visible, false otherwise
	 */
	public boolean isRequestsAwaitingRepliesDropdownDisplayed() {
		waitForElementVisibility(requestsAwaitingRepliesDropdown, 10);
		return requestsAwaitingRepliesDropdown.isDisplayed();
	}

	/**
	 * Checks if Future Deliveries dropdown is displayed
	 * @return true if the future deliveries dropdown is visible, false otherwise
	 */
	public boolean isFutureDeliveriesDropdownDisplayed() {
		waitForElementVisibility(futureDeliveriesDropdown, 10);
		return futureDeliveriesDropdown.isDisplayed();
	}

	/**
	 * Checks if Previous Orders dropdown is displayed
	 * @return true if the previous orders dropdown is visible, false otherwise
	 */
	public boolean isPreviousOrdersDropdownDisplayed() {
		waitForElementVisibility(previousOrdersDropdown, 10);
		return previousOrdersDropdown.isDisplayed();
	}

	/**
	 * Finds and clicks the Restock Inventory entry matching the given date and approximate time
	 * @param expectedDate The expected date in MM/dd/yyyy format
	 * @param expectedTime The expected time in hh:mm a format
	 * @return true if matching entry is found and clicked, false otherwise
	 */
	public boolean findAndClickRestockInventory(String expectedDate, String expectedTime) {
		setZeroImplicitWait(); // Set to 0 at start
		try {
			WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
			
			// Initial click on Today's Deliveries dropdown with explicit wait
			waitForElementVisibility(todaysDeliveriesDropdown, 10);
			int expectedCount = getDropdownCount(todaysDeliveriesDropdown);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", todaysDeliveriesDropdown);
			
			// Wait for dropdown items to appear
			int actualCount = 0;
			int maxAttempts = 3;
			for (int attempt = 0; attempt < maxAttempts; attempt++) {
				Thread.sleep(200);
				List<WebElement> items = driver.findElements(By.xpath(DROPDOWN_ITEMS));
				if (items.size() > actualCount) {
					actualCount = items.size();
				}
				if (actualCount >= expectedCount) {
					break;
				}
			}
			
			List<WebElement> restockEntries = driver.findElements(By.xpath(RESTOCK_INVENTORY_ITEMS));
			if (restockEntries.isEmpty()) {
				System.out.println("No Restock Inventory entries found");
				return false;
			}

			System.out.println("\nFound " + restockEntries.size() + " Restock Inventory entries");
			System.out.println("Looking for entry with date: " + expectedDate + " and time around: " + expectedTime);

			// Try each Restock Inventory entry in sequence
			for (int i = 0; i < restockEntries.size(); i++) {
				WebElement entry = restockEntries.get(i);
				
				// Debug info about the entry structure
				System.out.println("\n----------------------------------------");
				System.out.println("Checking Restock Inventory entry #" + (i + 1));
				
				// Get the date and full text with better error handling
				String dropdownDate = "";
				String dropdownText = "";
				try {
					// Try different XPath patterns to find the date
					List<WebElement> divs = entry.findElements(By.xpath(".//div"));
					if (!divs.isEmpty()) {
						dropdownDate = divs.get(0).getText().trim();
					} else {
						System.out.println("Warning: No div elements found in entry");
					}
					dropdownText = entry.getText().trim();
				} catch (Exception e) {
					System.out.println("Error getting dropdown info: " + e.getMessage());
					continue; // Skip to next entry if we can't get the info
				}
				
				System.out.println("In Dropdown Menu:");
				System.out.println("  Date shown: " + dropdownDate);
				System.out.println("  Full text: " + dropdownText);
				
				// Click the entry
				try {
					wait.until(ExpectedConditions.elementToBeClickable(entry));
					js.executeScript("arguments[0].click();", entry);
					Thread.sleep(500); // Short wait for details to load
				} catch (Exception e) {
					System.out.println("Error clicking entry: " + e.getMessage());
					continue; // Skip to next entry if click fails
				}

				// Get the date and time from details view with explicit waits
				String actualDate = null;
				String actualTime = null;
				try {
					WebElement dateElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ORDER_DATE_VALUE)));
					WebElement timeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ORDER_TIME_VALUE)));
					actualDate = dateElement.getText().trim();
					actualTime = timeElement.getText().trim();
				} catch (Exception e) {
					System.out.println("Error getting date/time values: " + e.getMessage());
					if (i < restockEntries.size() - 1) {
						// Click dropdown again for next iteration
						js.executeScript("arguments[0].click();", todaysDeliveriesDropdown);
						Thread.sleep(200);
						// Wait for dropdown items to appear again
						for (int attempt = 0; attempt < maxAttempts; attempt++) {
							Thread.sleep(200);
							List<WebElement> items = driver.findElements(By.xpath(DROPDOWN_ITEMS));
							if (items.size() >= expectedCount) {
								break;
							}
						}
						restockEntries = driver.findElements(By.xpath(RESTOCK_INVENTORY_ITEMS));
					}
					continue;
				}

				System.out.println("After clicking - Details View:");
				System.out.println("  Order Date: " + (actualDate != null ? actualDate : "Not found"));
				System.out.println("  Order Time: " + (actualTime != null ? actualTime : "Not found"));

				// Check if this is our entry
				if (actualDate != null && actualDate.equals(expectedDate)) {
					// Parse times to compare with 2-minute tolerance
					try {
						java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm a");
						java.util.Date actualTimeDate = timeFormat.parse(actualTime);
						java.util.Date expectedTimeDate = timeFormat.parse(expectedTime);
						long timeDiff = Math.abs(actualTimeDate.getTime() - expectedTimeDate.getTime());
						long twoMinutesInMillis = 2 * 60 * 1000;

						if (timeDiff <= twoMinutesInMillis) {
							System.out.println("✓ Found matching entry! Times are within 2 minutes.");
							System.out.println("----------------------------------------\n");
							return true;
						} else {
							System.out.println("✗ Date matches but time difference is too large: " + 
								(timeDiff / 1000 / 60) + " minutes");
						}
					} catch (Exception e) {
						System.out.println("Error comparing times: " + e.getMessage());
					}
				} else {
					System.out.println("✗ Date does not match expected date");
				}

				System.out.println("----------------------------------------");

				// If this wasn't the right entry, click the dropdown again to continue checking
				if (i < restockEntries.size() - 1) {
					js.executeScript("arguments[0].click();", todaysDeliveriesDropdown);
					Thread.sleep(200);
					// Wait for dropdown items to appear again
					for (int attempt = 0; attempt < maxAttempts; attempt++) {
						Thread.sleep(200);
						List<WebElement> items = driver.findElements(By.xpath(DROPDOWN_ITEMS));
						if (items.size() >= expectedCount) {
							break;
						}
					}
					restockEntries = driver.findElements(By.xpath(RESTOCK_INVENTORY_ITEMS));
				}
			}

			System.out.println("\nNo matching Restock Inventory entry found after checking all entries");
			return false;
		} catch (Exception e) {
			System.out.println("Error finding Restock Inventory: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			restoreImplicitWait(); // Only restore at the very end
		}
	}

	/**
	 * Gets the order date from the details view
	 * @return the order date as string, or null if not found
	 */
	public String getOrderDate() {
		try {
			WebElement dateElement = driver.findElement(By.xpath(ORDER_DATE_VALUE));
			return dateElement.getText().trim();
		} catch (Exception e) {
			System.out.println("Error getting order date: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Gets the order time from the details view
	 * @return the order time as string, or null if not found
	 */
	public String getOrderTime() {
		try {
			WebElement timeElement = driver.findElement(By.xpath(ORDER_TIME_VALUE));
			return timeElement.getText().trim();
		} catch (Exception e) {
			System.out.println("Error getting order time: " + e.getMessage());
			return null;
		}
	}
}
