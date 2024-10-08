package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {  // Accepting ITestContext for attaching WebDriver to context
        initialization();  // Passing the context to initialization for WebDriver setup
        loginPage = new LoginPage();
    }

    @Test
    public void loginTest() {
        loginPage.switchToFrame();  // Switch to iframe if necessary
        loginPage.clickSigninButton();  // Click the sign-in button
        loginPage.enterLoginCredentials(prop.getProperty("emailId"), prop.getProperty("password"));  // Enter login credentials

        homePage = new HomePage();  // Navigate to the HomePage after login
        Assert.assertTrue(homePage.updatedIsDisplayed());  // Verify if update is displayed on HomePage
    }

    @AfterMethod
    public void tearDown() {
        // Check if driver is not null before quitting the session
        if (driver != null) {
            driver.quit();
        }
    }
}
