package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import listeners.ExtentReporterNG;
import pages.*;
import util.Utilities;

public class HomePageTest extends TestBase {


	HomePage homePage;
	LoginPage loginPage;
	ChangeLogPage changeLog;
	AdminPage adminPage;
	BackFromRepairPage backFromRepairPage;
	CancellationHolidayPage cancellationHolidayPage;
	ChangeLogPage changeLogPage;
	CheckOutItemsPage checkOutItemsPage;
	CreateNewJobPage createNewJobPage;
	DashboardPage dashboardPage;
	DatabasePage databasePage;
	DriverEnterPage driverEnterPage;
	EditJobDetailsPage editJobDetailsPage;
	HoursSummaryExportPage hoursSummaryExportPage;
	ImportEmployeeReportCsvPage importEmployeeReportCsvPage;
	JobSummaryPage jobSummaryPage;
	MyLaborAssignmentsPage myLaborAssignmentsPage;
	OrderForYardPage orderForYardPage;
	PlaceOrderPage placeOrderPage;
	ReturnItemsPage returnItemsPage;
	ScheduleLaborPage scheduleLaborPage;
	SendTextMessagePage sendTextMessagePage;
	TransferItemsPage transferItemsPage;
	TruckManagementPage truckManagementPage;
	UpdateTimesheetsPage updateTimesheetsPage;
	ViewTemplatePage viewTemplatePage;
	WeatherCancellationPage weatherCancellationPage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        loginPage.switchToFrame();
        loginPage.clickSigninButton();
        homePage = loginPage.enterLoginCredentials(prop.getProperty("emailId"), prop.getProperty("password"));
    }
    
    
//    @Test(priority = 0)
//    public void testAllButtonsInSingleLogin() {
//
//        // Click and validate 'Updated' button
//        changeLogPage = homePage.clickOnUpdated();
//        changeLogPage.isHeaderDisplayed();
//        String updatedScreenshot = Utilities.captureScreenshot("UpdatedPage");  // Capture screenshot and store path
//        homePage = changeLogPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(updatedScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Edit Job Details' button
//        homePage.selectJob();
//        editJobDetailsPage = homePage.clickOnEditJobDetails();
//        editJobDetailsPage.isHeaderDisplayed();
//        String editJobScreenshot = Utilities.captureScreenshot("EditJobDetailsPage");  // Capture screenshot and store path
//        homePage = editJobDetailsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(editJobScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Place Order' button
//        placeOrderPage = homePage.clickOnPlaceOrder();
//        placeOrderPage.isHeaderDisplayed();
//        String placeOrderScreenshot = Utilities.captureScreenshot("PlaceOrderPage");  // Capture screenshot and store path
//        homePage = placeOrderPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(placeOrderScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Order for Yard' button
//        orderForYardPage = homePage.clickOnOrderForYard();
//        orderForYardPage.isHeaderDisplayed();
//        String orderForYardScreenshot = Utilities.captureScreenshot("OrderForYardPage");  // Capture screenshot and store path
//        homePage = orderForYardPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(orderForYardScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Dashboard' button
//        dashboardPage = homePage.clickOnDashboard();
//        dashboardPage.isHeaderDisplayed();
//        String dashboardScreenshot = Utilities.captureScreenshot("DashboardPage");  // Capture screenshot and store path
//        homePage = dashboardPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(dashboardScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'View Template' button
//        viewTemplatePage = homePage.clickOnViewTemplate();
//        viewTemplatePage.isHeaderDisplayed();
//        String viewTemplateScreenshot = Utilities.captureScreenshot("ViewTemplatePage");  // Capture screenshot and store path
//        homePage = viewTemplatePage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(viewTemplateScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Check Out Items' button
//        checkOutItemsPage = homePage.clickOnCheckOutItems();
//        checkOutItemsPage.isHeaderDisplayed();
//        String checkOutItemsScreenshot = Utilities.captureScreenshot("CheckOutItemsPage");  // Capture screenshot and store path
//        homePage = checkOutItemsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(checkOutItemsScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Return Items' button
//        returnItemsPage = homePage.clickOnReturnItems();
//        returnItemsPage.isHeaderDisplayed();
//        String returnItemsScreenshot = Utilities.captureScreenshot("ReturnItemsPage");  // Capture screenshot and store path
//        homePage = returnItemsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(returnItemsScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Transfer Items' button
//        transferItemsPage = homePage.clickOnTransferItems();
//        transferItemsPage.isHeaderDisplayed();
//        String transferItemsScreenshot = Utilities.captureScreenshot("TransferItemsPage");  // Capture screenshot and store path
//        homePage = transferItemsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(transferItemsScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Back From Repair' button
//        backFromRepairPage = homePage.clickOnBackFromRepair();
//        backFromRepairPage.isHeaderDisplayed();
//        String backFromRepairScreenshot = Utilities.captureScreenshot("BackFromRepairPage");  // Capture screenshot and store path
//        homePage = backFromRepairPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(backFromRepairScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Truck Management' button
//        truckManagementPage = homePage.clickOnTruckManagement();
//        truckManagementPage.isHeaderDisplayed();
//        String truckManagementScreenshot = Utilities.captureScreenshot("TruckManagementPage");  // Capture screenshot and store path
//        homePage = truckManagementPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(truckManagementScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Schedule Labor' button
//        scheduleLaborPage = homePage.clickOnScheduleLabor();
//        scheduleLaborPage.isHeaderDisplayed();
//        String scheduleLaborScreenshot = Utilities.captureScreenshot("ScheduleLaborPage");  // Capture screenshot and store path
//        homePage = scheduleLaborPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(scheduleLaborScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'My Labor Assignments' button
//        myLaborAssignmentsPage = homePage.clickOnMyLaborAssignments();
//        myLaborAssignmentsPage.isHeaderDisplayed();
//        String myLaborAssignmentsScreenshot = Utilities.captureScreenshot("MyLaborAssignmentsPage");  // Capture screenshot and store path
//        homePage = myLaborAssignmentsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(myLaborAssignmentsScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Update Timesheets' button
//        updateTimesheetsPage = homePage.clickOnUpdateTimesheets();
//        updateTimesheetsPage.isHeaderDisplayed();
//        String updateTimesheetsScreenshot = Utilities.captureScreenshot("UpdateTimesheetsPage");  // Capture screenshot and store path
//        homePage = updateTimesheetsPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(updateTimesheetsScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Weather Cancellation' button
//        weatherCancellationPage = homePage.clickOnWeatherCancellation();
//        weatherCancellationPage.isHeaderDisplayed();
//        String weatherCancellationScreenshot = Utilities.captureScreenshot("WeatherCancellationPage");  // Capture screenshot and store path
//        homePage = weatherCancellationPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(weatherCancellationScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Cancellation Holiday' button
//        cancellationHolidayPage = homePage.clickOnCancellationHoliday();
//        cancellationHolidayPage.isHeaderDisplayed();
//        String cancellationHolidayScreenshot = Utilities.captureScreenshot("CancellationHolidayPage");  // Capture screenshot and store path
//        homePage = cancellationHolidayPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(cancellationHolidayScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Driver Enter' button
//        driverEnterPage = homePage.clickOnDriverEnter();
//        driverEnterPage.isHeaderDisplayed();
//        String driverEnterScreenshot = Utilities.captureScreenshot("DriverEnterPage");  // Capture screenshot and store path
//        homePage = driverEnterPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(driverEnterScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Job Summary' button
//        jobSummaryPage = homePage.clickOnJobSummary();
//        jobSummaryPage.isHeaderDisplayed();
//        String jobSummaryScreenshot = Utilities.captureScreenshot("JobSummaryPage");  // Capture screenshot and store path
//        homePage = jobSummaryPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(jobSummaryScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Create New Job' button
//        createNewJobPage = homePage.clickOnCreateNewJob();
//        createNewJobPage.isHeaderDisplayed();
//        String createNewJobScreenshot = Utilities.captureScreenshot("CreateNewJobPage");  // Capture screenshot and store path
//        homePage = createNewJobPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(createNewJobScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Admin' button
//        adminPage = homePage.clickOnAdmin();
//        adminPage.isHeaderDisplayed();
//        String adminScreenshot = Utilities.captureScreenshot("AdminPage");  // Capture screenshot and store path
//        homePage = adminPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(adminScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Import Employee Report CSV' button
//        importEmployeeReportCsvPage = homePage.clickOnImportEmployeeReportCsv();
//        importEmployeeReportCsvPage.isHeaderDisplayed();
//        String importEmployeeReportCsvScreenshot = Utilities.captureScreenshot("ImportEmployeeReportCsvPage");  // Capture screenshot and store path
//        homePage = importEmployeeReportCsvPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(importEmployeeReportCsvScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Send Text Message' button
//        sendTextMessagePage = homePage.clickOnSendTextMessage();
//        sendTextMessagePage.isHeaderDisplayed();
//        String sendTextMessageScreenshot = Utilities.captureScreenshot("SendTextMessagePage");  // Capture screenshot and store path
//        homePage = sendTextMessagePage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(sendTextMessageScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Hours Summary Export' button
//        hoursSummaryExportPage = homePage.clickOnHoursSummaryExport();
//        hoursSummaryExportPage.isHeaderDisplayed();
//        String hoursSummaryExportScreenshot = Utilities.captureScreenshot("HoursSummaryExportPage");  // Capture screenshot and store path
//        homePage = hoursSummaryExportPage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(hoursSummaryExportScreenshot);  // Attach screenshot to report
//
//        // Click and validate 'Database' button
//        databasePage = homePage.clickOnDatabase();
//        databasePage.isHeaderDisplayed();
//        String databaseScreenshot = Utilities.captureScreenshot("DatabasePage");  // Capture screenshot and store path
//        homePage = databasePage.clickOnHome(); // Navigate back to Home page
//        Assert.assertTrue(homePage.updatedIsDisplayed());
//        ExtentReporterNG.test.addScreenCaptureFromPath(databaseScreenshot);  // Attach screenshot to report
//    }



    @Test(priority = 0)
    public void testAllButtonsInSingleLogin() {


        // Click and validate 'Updated' button
        changeLogPage = homePage.clickOnUpdated();
        changeLogPage.isHeaderDisplayed();
        String updatedScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = changeLogPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(updatedScreenshotBase64, "Updated Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Edit Job Details' button
        homePage.selectJob();
        editJobDetailsPage = homePage.clickOnEditJobDetails();
        editJobDetailsPage.isHeaderDisplayed();
        String editJobScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = editJobDetailsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(editJobScreenshotBase64, "Edit Job Details Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Place Order' button
        placeOrderPage = homePage.clickOnPlaceOrder();
        placeOrderPage.isHeaderDisplayed();
        String placeOrderScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = placeOrderPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(placeOrderScreenshotBase64, "Place Order Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Order for Yard' button
        orderForYardPage = homePage.clickOnOrderForYard();
        orderForYardPage.isHeaderDisplayed();
        String orderForYardScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = orderForYardPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(orderForYardScreenshotBase64, "Order for Yard Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Dashboard' button
        dashboardPage = homePage.clickOnDashboard();
        dashboardPage.isHeaderDisplayed();
        String dashboardScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = dashboardPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(dashboardScreenshotBase64, "Dashboard Page Screenshot");  // Attach screenshot to report

        // Click and validate 'View Template' button
        viewTemplatePage = homePage.clickOnViewTemplate();
        viewTemplatePage.isHeaderDisplayed();
        String viewTemplateScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = viewTemplatePage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(viewTemplateScreenshotBase64, "View Template Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Check Out Items' button
        checkOutItemsPage = homePage.clickOnCheckOutItems();
        checkOutItemsPage.isHeaderDisplayed();
        String checkOutItemsScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = checkOutItemsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(checkOutItemsScreenshotBase64, "Check Out Items Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Return Items' button
        returnItemsPage = homePage.clickOnReturnItems();
        returnItemsPage.isHeaderDisplayed();
        String returnItemsScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = returnItemsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(returnItemsScreenshotBase64, "Return Items Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Transfer Items' button
        transferItemsPage = homePage.clickOnTransferItems();
        transferItemsPage.isHeaderDisplayed();
        String transferItemsScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = transferItemsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(transferItemsScreenshotBase64, "Transfer Items Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Back From Repair' button
        backFromRepairPage = homePage.clickOnBackFromRepair();
        backFromRepairPage.isHeaderDisplayed();
        String backFromRepairScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = backFromRepairPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(backFromRepairScreenshotBase64, "Back From Repair Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Truck Management' button
        truckManagementPage = homePage.clickOnTruckManagement();
        truckManagementPage.isHeaderDisplayed();
        String truckManagementScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = truckManagementPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(truckManagementScreenshotBase64, "Truck Management Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Schedule Labor' button
        scheduleLaborPage = homePage.clickOnScheduleLabor();
        scheduleLaborPage.isHeaderDisplayed();
        String scheduleLaborScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = scheduleLaborPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(scheduleLaborScreenshotBase64, "Schedule Labor Page Screenshot");  // Attach screenshot to report

        // Click and validate 'My Labor Assignments' button
        myLaborAssignmentsPage = homePage.clickOnMyLaborAssignments();
        myLaborAssignmentsPage.isHeaderDisplayed();
        String myLaborAssignmentsScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = myLaborAssignmentsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(myLaborAssignmentsScreenshotBase64, "My Labor Assignments Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Update Timesheets' button
        updateTimesheetsPage = homePage.clickOnUpdateTimesheets();
        updateTimesheetsPage.isHeaderDisplayed();
        String updateTimesheetsScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = updateTimesheetsPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(updateTimesheetsScreenshotBase64, "Update Timesheets Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Weather Cancellation' button
        weatherCancellationPage = homePage.clickOnWeatherCancellation();
        weatherCancellationPage.isHeaderDisplayed();
        String weatherCancellationScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = weatherCancellationPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(weatherCancellationScreenshotBase64, "Weather Cancellation Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Cancellation Holiday' button
        cancellationHolidayPage = homePage.clickOnCancellationHoliday();
        cancellationHolidayPage.isHeaderDisplayed();
        String cancellationHolidayScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = cancellationHolidayPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(cancellationHolidayScreenshotBase64, "Cancellation Holiday Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Driver Enter' button
        driverEnterPage = homePage.clickOnDriverEnter();
        driverEnterPage.isHeaderDisplayed();
        String driverEnterScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = driverEnterPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(driverEnterScreenshotBase64, "Driver Enter Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Job Summary' button
        jobSummaryPage = homePage.clickOnJobSummary();
        jobSummaryPage.isHeaderDisplayed();
        String jobSummaryScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = jobSummaryPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(jobSummaryScreenshotBase64, "Job Summary Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Create New Job' button
        createNewJobPage = homePage.clickOnCreateNewJob();
        createNewJobPage.isHeaderDisplayed();
        String createNewJobScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = createNewJobPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(createNewJobScreenshotBase64, "Create New Job Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Admin' button
        adminPage = homePage.clickOnAdmin();
        adminPage.isHeaderDisplayed();
        String adminScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = adminPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(adminScreenshotBase64, "Admin Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Import Employee Report CSV' button
        importEmployeeReportCsvPage = homePage.clickOnImportEmployeeReportCsv();
        importEmployeeReportCsvPage.isHeaderDisplayed();
        String importEmployeeReportCsvScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = importEmployeeReportCsvPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(importEmployeeReportCsvScreenshotBase64, "Import Employee Report CSV Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Send Text Message' button
        sendTextMessagePage = homePage.clickOnSendTextMessage();
        sendTextMessagePage.isHeaderDisplayed();
        String sendTextMessageScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = sendTextMessagePage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(sendTextMessageScreenshotBase64, "Send Text Message Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Hours Summary Export' button
        hoursSummaryExportPage = homePage.clickOnHoursSummaryExport();
        hoursSummaryExportPage.isHeaderDisplayed();
        String hoursSummaryExportScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = hoursSummaryExportPage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(hoursSummaryExportScreenshotBase64, "Hours Summary Export Page Screenshot");  // Attach screenshot to report

        // Click and validate 'Database' button
        databasePage = homePage.clickOnDatabase();
        databasePage.isHeaderDisplayed();
        String databaseScreenshotBase64 = Utilities.captureScreenshotAsBase64();  // Capture screenshot as Base64
        homePage = databasePage.clickOnHome(); // Navigate back to Home page
        Assert.assertTrue(homePage.updatedIsDisplayed());
        ExtentReporterNG.test.addScreenCaptureFromBase64String(databaseScreenshotBase64, "Database Page Screenshot");  // Attach screenshot to report
    }


    

    

    @Test(priority = 1)
    public void updatedLinkTest() {

        Assert.assertTrue(homePage.updatedIsDisplayed());
    }

    @Test(priority = 2)
    public void clickOnUpdatedTest() {

        homePage.clickOnUpdated().isHeaderDisplayed();
    }

    @Test(priority = 3)
    public void clickOnEditJobDetailsTest() {

        homePage.selectJob();
        homePage.clickOnEditJobDetails().isHeaderDisplayed();
    }

    @Test(priority = 4)
    public void clickOnPlaceOrderTest() {

        homePage.selectJob();
        homePage.clickOnPlaceOrder().isHeaderDisplayed();
    }

    @Test(priority = 5)
    public void clickOnOrderForYardTest() {

        homePage.clickOnOrderForYard().isHeaderDisplayed();
    }

    @Test(priority = 6)
    public void clickOnDashboardTest() {
        homePage.clickOnDashboard().isHeaderDisplayed();
    }

    @Test(priority = 7)
    public void clickOnViewTemplateTest() {
        homePage.selectJob();
        homePage.clickOnViewTemplate().isHeaderDisplayed();
    }

    @Test(priority = 8)
    public void clickOnCheckOutItemsTest() {
        homePage.clickOnCheckOutItems().isHeaderDisplayed();
    }

    @Test(priority = 9)
    public void clickOnReturnItemsTest() {
        homePage.clickOnReturnItems().isHeaderDisplayed();
    }

    @Test(priority = 10)
    public void clickOnTransferItemsTest() {
        homePage.clickOnTransferItems().isHeaderDisplayed();
    }

    @Test(priority = 11)
    public void clickOnBackFromRepairTest() {
        homePage.clickOnBackFromRepair().isHeaderDisplayed();
    }

    @Test(priority = 12)
    public void clickOnTruckManagementTest() {
        homePage.clickOnTruckManagement().isHeaderDisplayed();
    }

    @Test(priority = 13)
    public void clickOnScheduleLaborTest() {
        homePage.selectJob();
        homePage.clickOnScheduleLabor().isHeaderDisplayed();
    }

    @Test(priority = 14)
    public void clickOnMyLaborAssignmentsTest() {
        homePage.clickOnMyLaborAssignments().isHeaderDisplayed();
    }

    @Test(priority = 15)
    public void clickOnUpdateTimesheetsTest() {
        homePage.clickOnUpdateTimesheets().isHeaderDisplayed();
    }

    @Test(priority = 16)
    public void clickOnWeatherCancellationTest() {
        homePage.selectJob();
        homePage.clickOnWeatherCancellation().isHeaderDisplayed();
    }

    @Test(priority = 17)
    public void clickOnCancellationHolidayTest() {
        homePage.selectJob();
        homePage.clickOnCancellationHoliday().isHeaderDisplayed();
    }

    @Test(priority = 18)
    public void clickOnDriverEnterTest() {
        homePage.clickOnDriverEnter().isHeaderDisplayed();
    }

    @Test(priority = 19)
    public void clickOnJobSummaryTest() {
        homePage.clickOnJobSummary().isHeaderDisplayed();
    }

    @Test(priority = 20)
    public void clickOnCreateNewJobTest() {
        homePage.clickOnCreateNewJob().isHeaderDisplayed();
    }
    
    @Test(priority = 21)
    public void clickOnAdminTest() {
        homePage.clickOnAdmin().isHeaderDisplayed();
    }

    @Test(priority = 22)
    public void clickOnImportEmployeeReportCsvTest() {
        homePage.clickOnImportEmployeeReportCsv().isHeaderDisplayed();
    }

    @Test(priority = 23)
    public void clickOnSendTextMessageTest() {
        homePage.clickOnSendTextMessage().isHeaderDisplayed();
    }

    @Test(priority = 24)
    public void clickOnHoursSummaryExportTest() {
        homePage.clickOnHoursSummaryExport().isHeaderDisplayed();
    }

    @Test(priority = 25)
    public void clickOnDatabaseTest() {
        homePage.clickOnDatabase().isHeaderDisplayed();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}