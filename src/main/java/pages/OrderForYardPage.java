package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderForYardPage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='Order For Yard']")
	WebElement orderForYardHeader;
	
	@FindBy(xpath = "//p[text()='Delivery Date:']")
	WebElement deliveryDateLabel;
	
	@FindBy(xpath = "//p[text()='Delivery Time:']")
	WebElement deliveryTimeLabel;
	
	@FindBy(xpath = "//p[text()='Select products:']")
	WebElement selectProductsLabel;

	@FindBy(xpath = "//button[text()='Delete Selection']")
	WebElement deleteSelectionButton;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement selectAllProductsCheckbox;

	@FindBy(xpath = "//div[contains(@class, 'rw-dropdown-list-input')]")
	WebElement deliveryTimeDropdown;
	
	@FindBy(xpath = "//div[@role='option'][1]")
	WebElement firstTimeOption;

	@FindBy(xpath = "//span[contains(text(),'Select product(s)')]")
	WebElement productSelectionDropdown;
	
	@FindBy(xpath = "//*[@id='rw_5_listbox']/div[1]")
	WebElement firstProductOption;
	
	@FindBy(xpath = "//input[@placeholder='0']")
	WebElement quantityTextbox;

	@FindBy(xpath = "//button[text()='Place Order']")
	WebElement placeOrderButton;

	@FindBy(xpath = "//div[contains(@class, 'Toastify__toast--success')]//div[contains(text(),'Order placed successfully')]")
	WebElement orderSuccessNotification;

	@FindBy(xpath = "//td[text()='Product list is empty!']")
	WebElement emptyListMessage;

	/**
	 * Checks if the Order For Yard page header is displayed
	 * @return true if the header is visible, false otherwise
	 */
	@Override
	public boolean isHeaderDisplayed() {
		return orderForYardHeader.isDisplayed();
	}
	
	/**
	 * Checks if the Delivery Date label is displayed
	 * @return true if the delivery date label is visible, false otherwise
	 */
	public boolean isDeliveryDateLabelDisplayed() {
		return deliveryDateLabel.isDisplayed();
	}
	
	/**
	 * Checks if the Delivery Time label is displayed
	 * @return true if the delivery time label is visible, false otherwise
	 */
	public boolean isDeliveryTimeLabelDisplayed() {
		return deliveryTimeLabel.isDisplayed();
	}
	
	/**
	 * Checks if the Select Products label is displayed
	 * @return true if the select products label is visible, false otherwise
	 */
	public boolean isSelectProductsLabelDisplayed() {
		return selectProductsLabel.isDisplayed();
	}

	/**
	 * Checks if the Delete Selection button is displayed
	 * @return true if the delete selection button is visible, false otherwise
	 */
	public boolean isDeleteSelectionButtonDisplayed() {
		return deleteSelectionButton.isDisplayed();
	}

	/**
	 * Clicks on the delivery time dropdown to open the options
	 */
	public void clickDeliveryTimeDropdown() {
		waitForElementToBeClickable(deliveryTimeDropdown, 10);
		deliveryTimeDropdown.click();
	}

	/**
	 * Selects the first time option from the dropdown list
	 * Note: Dropdown must be opened first using clickDeliveryTimeDropdown()
	 */
	public void selectFirstTimeOption() {
		waitForElementVisibility(firstTimeOption, 10);
		waitForElementToBeClickable(firstTimeOption, 10);
		firstTimeOption.click();
	}

	/**
	 * Clicks on the product selection dropdown to open the options
	 */
	public void clickProductSelectionDropdown() {
		waitForElementToBeClickable(productSelectionDropdown, 10);
		productSelectionDropdown.click();
	}

	/**
	 * Selects the first product option from the dropdown list
	 * Note: Dropdown must be opened first using clickProductSelectionDropdown()
	 */
	public void selectFirstProductOption() {
		
		firstProductOption.click();
	}

	/**
	 * Inputs the specified quantity for the selected product
	 * @param quantity The quantity to input
	 */
	public void inputQuantity(String quantity) {
		waitForElementVisibility(quantityTextbox, 5);
		quantityTextbox.clear();
		quantityTextbox.sendKeys(quantity);
	}

	/**
	 * Verifies if the order success notification is displayed
	 * @return true if the success notification is visible, false otherwise
	 */
	public boolean isOrderSuccessNotificationDisplayed() {
		waitForElementVisibility(orderSuccessNotification, 10);
		return orderSuccessNotification.isDisplayed();
	}

	/**
	 * Clicks on the Place Order button to submit the order and navigates to Dashboard
	 * Note: This should be called after selecting product and inputting quantity
	 * @return DashboardPage The dashboard page instance after successful order placement
	 */
	public DashboardPage clickPlaceOrderButton() {
		waitForElementToBeClickable(placeOrderButton, 5);
		placeOrderButton.click();
		return new DashboardPage();
	}

	/**
	 * Clicks on the Select All Products checkbox
	 */
	public void clickSelectAllProductsCheckbox() {
		waitForElementToBeClickable(selectAllProductsCheckbox, 10);
		selectAllProductsCheckbox.click();
	}

	/**
	 * Clicks on the Delete Selection button
	 */
	public void clickDeleteSelectionButton() {
		waitForElementToBeClickable(deleteSelectionButton, 10);
		deleteSelectionButton.click();
	}

	/**
	 * Checks if the Select All Products checkbox is selected
	 * @return true if the checkbox is selected, false otherwise
	 */
	public boolean isSelectAllProductsCheckboxSelected() {
		return selectAllProductsCheckbox.isSelected();
	}

	/**
	 * Verifies if the empty list message is displayed
	 * @return true if the empty list message is visible, false otherwise
	 */
	public boolean isEmptyListMessageDisplayed() {
		waitForElementVisibility(emptyListMessage, 10);
		return emptyListMessage.isDisplayed();
	}
}