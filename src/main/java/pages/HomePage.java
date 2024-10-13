package pages;

import java.time.Duration;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Utilities;

public class HomePage extends BasePage {

	@FindBy(xpath = "//img[contains(@src, 'logo-e9c21d2c.jpg')]")
	WebElement logoImage;
	
	@FindBy(css = "//a[@href='/' and contains(text(),'BOSS 4.0')]")
	WebElement bossHeader;
	
	@FindBy(css = "//a[contains(@href, '/') and contains(text(), 'Home')]")
	WebElement homeButton;

	@FindBy(css = "a[href='/changeLog']")
	WebElement updatedButton;

	@FindBy(xpath = "//p[contains(text(), 'bulleys boss')]")
	WebElement userIcon;
	
	@FindBy(xpath = "//span[text()='Select a job']")
	WebElement jobsDropdown;

	@FindBy(xpath = "//button[contains(text(), 'EDIT JOB DETAILS')]")
	WebElement editJobDetailsButton;

	@FindBy(xpath = "//button[contains(text(), 'Place Order')]")
	WebElement placeOrderButton;

	@FindBy(xpath = "//button[contains(text(), 'Order For Yard')]")
	WebElement orderForYardButton;

	@FindBy(xpath = "//button[contains(text(), 'Dashboard')]")
	WebElement dashboardButton;

	@FindBy(xpath = "//button[contains(text(), 'View Template')]")
	WebElement viewTemplateButton;

	@FindBy(xpath = "//button[contains(text(), 'Check Out Items')]")
	WebElement checkOutItemsButton;

	@FindBy(xpath = "//button[contains(text(), 'Return Items')]")
	WebElement returnItemsButton;

	@FindBy(xpath = "//button[contains(text(), 'Transfer Items')]")
	WebElement transferItemsButton;

	@FindBy(xpath = "//button[contains(text(), 'Back From Repair')]")
	WebElement backFromRepairButton;

	@FindBy(xpath = "//button[contains(text(), 'Truck Management')]")
	WebElement truckManagementButton;

	@FindBy(xpath = "//button[contains(text(), 'Schedule Labor')]")
	WebElement scheduleLaborButton;

	@FindBy(xpath = "//button[contains(text(), 'My Labor Assignments')]")
	WebElement myLaborAssignmentsButton;

	@FindBy(xpath = "//button[contains(text(), 'Update Timesheets')]")
	WebElement updateTimesheetsButton;

	@FindBy(xpath = "//button[contains(text(), 'Weather Cancellation')]")
	WebElement weatherCancellationButton;

	@FindBy(xpath = "//button[contains(text(), 'Cancellation Holiday')]")
	WebElement cancellationHolidayButton;

	@FindBy(xpath = "//button[contains(text(), 'Driver Enter')]")
	WebElement driverEnterButton;

	@FindBy(xpath = "//button[contains(text(), 'Job Summary')]")
	WebElement jobSummaryButton;

	@FindBy(xpath = "//button[contains(text(), 'Create New Job')]")
	WebElement createNewJobButton;

	@FindBy(xpath = "//button[contains(text(), 'Admin')]")
	WebElement adminButton;

	@FindBy(xpath = "//button[contains(text(), 'Import Employee Report CSV')]")
	WebElement importEmployeeReportCSVButton;

	@FindBy(xpath = "//button[contains(text(), 'Send Text Message')]")
	WebElement sendTextMessageButton;

	@FindBy(xpath = "//button[contains(text(), 'Hours Summary/Export')]")
	WebElement hoursSummaryExportButton;

	@FindBy(xpath = "//button[contains(text(), 'Database')]")
	WebElement databaseButton;
	
	@FindBy(xpath = "//span[text()='4240374-Test-chicago']")
	WebElement selectedJob;
	
	@FindBy(xpath = "(//div[@role='option'])[2]") // To select second job from the dropdown
	WebElement jobToSelect;
	


	

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    @Override
    public boolean isHeaderDisplayed() {
		return bossHeader.isDisplayed();
	}

    public void selectJob() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    	wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(jobsDropdown)).click();
                wait.until(ExpectedConditions.elementToBeClickable(jobToSelect)).click();
                break;  // Exit loop if successful
            }catch(TimeoutException e) {
            	System.out.println("Jobs were not displayed. Retrying...");
            }
            catch (StaleElementReferenceException e) {
                System.out.println("Stale Element. Retrying...");
            }
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
    }

    public boolean updatedIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(updatedButton)).isDisplayed();
    }

    public ChangeLogPage clickOnUpdated() {
        updatedButton.click();
        return new ChangeLogPage();
    }

    public EditJobDetailsPage clickOnEditJobDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(editJobDetailsButton)).click();
        return new EditJobDetailsPage();
    }

    public PlaceOrderPage clickOnPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        return new PlaceOrderPage();
    }

    public OrderForYardPage clickOnOrderForYard() {
        wait.until(ExpectedConditions.elementToBeClickable(orderForYardButton)).click();
        return new OrderForYardPage();
    }

    public DashboardPage clickOnDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardButton)).click();
        return new DashboardPage();
    }

    public ViewTemplatePage clickOnViewTemplate() {
        wait.until(ExpectedConditions.elementToBeClickable(viewTemplateButton)).click();
        return new ViewTemplatePage();
    }

    public CheckOutItemsPage clickOnCheckOutItems() {
        wait.until(ExpectedConditions.elementToBeClickable(checkOutItemsButton)).click();
        return new CheckOutItemsPage();
    }

    public ReturnItemsPage clickOnReturnItems() {
        wait.until(ExpectedConditions.elementToBeClickable(returnItemsButton)).click();
        return new ReturnItemsPage();
    }

    public TransferItemsPage clickOnTransferItems() {
        wait.until(ExpectedConditions.elementToBeClickable(transferItemsButton)).click();
        return new TransferItemsPage();
    }

    public BackFromRepairPage clickOnBackFromRepair() {
        wait.until(ExpectedConditions.elementToBeClickable(backFromRepairButton)).click();
        return new BackFromRepairPage();
    }

    public TruckManagementPage clickOnTruckManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(truckManagementButton)).click();
        return new TruckManagementPage();
    }

    public ScheduleLaborPage clickOnScheduleLabor() {
        wait.until(ExpectedConditions.elementToBeClickable(scheduleLaborButton)).click();
        return new ScheduleLaborPage();
    }

    public MyLaborAssignmentsPage clickOnMyLaborAssignments() {
        wait.until(ExpectedConditions.elementToBeClickable(myLaborAssignmentsButton)).click();
        return new MyLaborAssignmentsPage();
    }

    public UpdateTimesheetsPage clickOnUpdateTimesheets() {
        wait.until(ExpectedConditions.elementToBeClickable(updateTimesheetsButton)).click();
        return new UpdateTimesheetsPage();
    }

    public WeatherCancellationPage clickOnWeatherCancellation() {
        wait.until(ExpectedConditions.elementToBeClickable(weatherCancellationButton)).click();
        return new WeatherCancellationPage();
    }

    public CancellationHolidayPage clickOnCancellationHoliday() {
        wait.until(ExpectedConditions.elementToBeClickable(cancellationHolidayButton)).click();
        return new CancellationHolidayPage();
    }

    public DriverEnterPage clickOnDriverEnter() {
        wait.until(ExpectedConditions.elementToBeClickable(driverEnterButton)).click();
        return new DriverEnterPage();
    }

    public JobSummaryPage clickOnJobSummary() {
        wait.until(ExpectedConditions.elementToBeClickable(jobSummaryButton)).click();
        return new JobSummaryPage();
    }

    public CreateNewJobPage clickOnCreateNewJob() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewJobButton)).click();
        return new CreateNewJobPage();
    }
    
    public AdminPage clickOnAdmin() {
    	wait.until(ExpectedConditions.elementToBeClickable(adminButton)).click();
		return new AdminPage();
	}

    public ImportEmployeeReportCsvPage clickOnImportEmployeeReportCsv() {
        wait.until(ExpectedConditions.elementToBeClickable(importEmployeeReportCSVButton)).click();
        return new ImportEmployeeReportCsvPage();
    }

    public SendTextMessagePage clickOnSendTextMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(sendTextMessageButton)).click();
        return new SendTextMessagePage();
    }

    public HoursSummaryExportPage clickOnHoursSummaryExport() {
        wait.until(ExpectedConditions.elementToBeClickable(hoursSummaryExportButton)).click();
        return new HoursSummaryExportPage();
    }

    public DatabasePage clickOnDatabase() {
        wait.until(ExpectedConditions.elementToBeClickable(databaseButton)).click();
        return new DatabasePage();
    }

	public void clickOnHomeButton() {
		homeButton.click();
		
	}

	
}
