package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Order']")
	WebElement placeOrderHeader;
	
	@FindBy(xpath = "//div[text()='Job:']")
	WebElement jobField;
	
	@FindBy(xpath = "//div[text()='Delivery Date:']")
	WebElement deliveryDateField;
	
	@FindBy(xpath = "//div[text()='Vendor Delivery Time:']")
	WebElement vendorDeliveryTimeField;
	
	@FindBy(xpath = "//div[text()='Deliver to yard:']")
	WebElement deliverToYardField;
	
	@FindBy(xpath = "//input[@id='react-select-2-input']")
	WebElement productsDropdown;
	
	@FindBy(id = "react-select-2-option-0")
	WebElement firstProduct;
	
	@FindBy(xpath = "//input[@placeholder='0']")
	WebElement quantityInput;
	
	@FindBy(xpath = "//button[text()='REVIEW ORDER']")
	WebElement reviewOrderButton;
	
	@FindBy(xpath = "//button[text()='PLACE ORDER']")
	WebElement placeOrderButton;
	
	@FindBy(xpath = "//div[text()='Order placed successfully']")
	WebElement placeOrderSuccessNotification;
	
	
	private By yardProduct(String yardProduct) {
		return By.xpath("//div[contains(text(), '" + yardProduct + "')]");
	}
	
	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	WebElement yardCheckbox;
	
	// WebElement for dropdown
    @FindBy(xpath = "//select[@class='border-b-2 border-black']")
    WebElement deliveryDropdown;
    
    // Method to generate dynamic locator
    private By deliveryTimeCheckbox(int rowNumber, String time) {
        return By.xpath("//input[@type='checkbox' and @value='" + rowNumber + "|" + time + "']");
    }
	
	@Override
	public boolean isHeaderDisplayed() {
		return placeOrderHeader.isDisplayed();
	}
	
    // Method to check if jobField is displayed
    public boolean isJobFieldDisplayed() {
        return jobField.isDisplayed();
    }

    // Method to check if deliveryDateField is displayed
    public boolean isDeliveryDateFieldDisplayed() {
        return deliveryDateField.isDisplayed();
    }

    // Method to check if vendorDeliveryTimeField is displayed
    public boolean isVendorDeliveryTimeFieldDisplayed() {
        return vendorDeliveryTimeField.isDisplayed();
    }

    // Method to check if deliverToYardField is displayed
    public boolean isDeliverToYardFieldDisplayed() {
        return deliverToYardField.isDisplayed();
    }
    
 // Method to click on the products dropdown
    public void clickProductsDropdown() {
        productsDropdown.click();
    }

    // Method to select the first product
    public void selectFirstProduct() {
        firstProduct.click();
    }

    // Method to input the quantity
    public void enterQuantity(String quantity) {
        quantityInput.sendKeys(quantity);
    }

    // Method to click on the review order button
    public void clickReviewOrderButton() {
        reviewOrderButton.click();
    }

    // Method to click on the place order button
    public void clickPlaceOrderButton() {
    	waitForElementToBeClickable(placeOrderButton, 10);
        placeOrderButton.click();
    }

    // Method to check if the place order success notification is displayed
    public boolean isPlaceOrderSuccessNotificationDisplayed() {
        return placeOrderSuccessNotification.isDisplayed();
    }
    
    public void searchAndSelectYardProduct(String yardProduct) {
    	productsDropdown.sendKeys(yardProduct);
    	driver.findElement(yardProduct(yardProduct)).click();
    }
    
    // Method to check order from yard checkbox
    public void checkOrderFromYardCheckbox() {
    	yardCheckbox.click();
    }
    
    // Method to select option from dropdown
    public void selectSelfDeliveryOption(String optionText) {
        selectDropdownByText(deliveryDropdown, optionText);
    }
    
// // Method to select a delivery time for a specific truck, increasing by 2 hours if already selected
//    public void selectTruckDeliveryTime(int rowNumber, String initialTime) {
//        String currentTime = initialTime;
//        
//        while (true) {
//            WebElement checkbox = driver.findElement(deliveryTimeCheckbox(rowNumber, currentTime));
//
//            // Check if the checkbox is already selected
//            if (!checkbox.isSelected()) {
//                checkbox.click();
//                System.out.println("Selected available delivery time: " + currentTime + " for truck row: " + rowNumber);
//                break;  // Exit loop once an available slot is found
//            } else {
//                System.out.println("Time slot " + currentTime + " is already selected, checking next available slot...");
//                
//                // Increase time by 2 hours
//                currentTime = incrementTimeByHours(currentTime, 2);
//            }
//        }
//    }
//
//    // Helper method to increment time by hours (handles AM/PM format)
//    private String incrementTimeByHours(String time, int hours) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // Format: "12:00 AM"
//            Date date = sdf.parse(time);
//
//            // Increment time by given hours
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            calendar.add(Calendar.HOUR, hours);
//
//            return sdf.format(calendar.getTime()); // Return new time in "h:mm a" format
//        } catch (ParseException e) {
//            System.out.println("Error parsing time: " + time);
//            return time; // Return original time if parsing fails
//        }
//    }

 // Method to select a delivery time for a specific truck, increasing time or row if needed
    public void selectTruckDeliveryTime(int rowNumber, String initialTime) {
        String currentTime = initialTime;
        int maxAttempts = 8; // Prevent infinite loops (assume a full 24-hour cycle with 3-hour increments)
        int attempts = 0;
        int maxRowNumber = getMaxTruckRow(); // Get the last truck row dynamically

        // Ensure we start at row 2 or higher
        if (rowNumber < 2) {
            System.out.println("⚠️ Invalid truck row! Starting from Truck 2.");
            rowNumber = 2;
        }

        while (true) {
            WebElement checkbox = null;

            try {
                checkbox = driver.findElement(deliveryTimeCheckbox(rowNumber, currentTime));

                // If checkbox is found and not selected, click it and break
                if (!checkbox.isSelected()) {
                    checkbox.click();
                    System.out.println("✅ Selected available delivery time: " + currentTime + " for Truck " + rowNumber);
                    break;
                } else {
                    System.out.println("⚠️ Time slot " + currentTime + " is already selected for Truck " + rowNumber + ". Checking next available slot...");
                }
            } catch (NoSuchElementException e) {
                System.out.println("❌ No checkbox found for time: " + currentTime + " in Truck " + rowNumber);
            }

            // Increment time by 3 hours
            currentTime = incrementTimeByHours(currentTime, 3);
            attempts++;

            // If we've checked all time slots, move to the next truck row
            if (attempts >= maxAttempts) {
                if (rowNumber + 1 > maxRowNumber) {  // ✅ FIX: Ensure we don't go past the last truck
                    System.out.println("🚨 No available time slots in any truck! Order cannot be placed.");
                    throw new RuntimeException("All trucks are fully booked. No available delivery slots.");
                }

                System.out.println("🚚 All time slots are taken in Truck " + rowNumber + ". Moving to Truck " + (rowNumber + 1) + "...");
                rowNumber++; // Move to next row (truck)
                currentTime = initialTime; // Reset time
                attempts = 0; // Reset attempts
            }
        }
    }

    // ✅ FIX: Get the last truck row correctly
    private int getMaxTruckRow() {
        List<WebElement> truckRows = driver.findElements(By.xpath("//table/tbody/tr[position()>1]")); // ✅ Only count truck rows
        int maxRow = truckRows.size() + 1; // ✅ Adjusted since row 1 is header, trucks start at row 2
        System.out.println("🔍 Detected maximum truck row: " + maxRow);
        return maxRow;
    }

    // Helper method to increment time by hours (handles AM/PM format)
    private String incrementTimeByHours(String time, int hours) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // Format: "12:00 AM"
            Date date = sdf.parse(time);

            // Increment time by given hours
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, hours);

            return sdf.format(calendar.getTime()); // Return new time in "h:mm a" format
        } catch (ParseException e) {
            System.out.println("❌ Error parsing time: " + time);
            return time; // Return original time if parsing fails
        }
    }


    
}
