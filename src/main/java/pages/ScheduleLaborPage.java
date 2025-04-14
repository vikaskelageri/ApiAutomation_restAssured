package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScheduleLaborPage extends BasePage{

	@FindBy(xpath = "//div[text()='Schedule Labor - ']")
	WebElement scheduleLaborHeader;

	@FindBy(xpath = "//div[text()='Add Workers - Start Date: ']")
	WebElement addWorkerstext;

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement SearchBox;

	//value will take either "Current Assignment" or "Labour type"
	public WebElement Select_CurrAssignOrtype(String value) {
		return driver.findElement(By.xpath("//span[text()='value']/following-sibling::select"));
	}


	@FindBy(xpath = "//div[@class='rw-dropdown-list-input']")
	WebElement SelectWorker_dropdown;

	public WebElement SelectWorker_name(String worker_name) {
		return driver.findElement(By.xpath("//div[@id='worker-select_listbox']/div[contains(text(),'"+worker_name+"')]"));
	}



	//it will work for all buttons for schedule labour page
	public WebElement ScheduleLabour_btns(String btn_name) {
		return driver.findElement(By.xpath("//button[text()='"+btn_name+"']"));
	}

	//We can pass page No "1","2" ....or we can pass "previous" or "next" button also
	public WebElement ScheduleLabour_PaginationBtn(String pageNo){
		return driver.findElement(By.cssSelector("button[aria-label*='"+pageNo+"']"));
	}

	//pass the message tht u want to capture
	public WebElement ScheduleLabour_SuccesfulMsg(String msg){
		return driver.findElement(By.cssSelector("//div[contains(text(),'"+msg+"')]"));
	}

	//pass either first name or last name to select wroker
	public WebElement Select_Worker(String name){
		return driver.findElement(By.cssSelector("(//td[contains(text(),'"+name+"')]/preceding-sibling::td/input)"));
	}













	@Override
	public boolean isHeaderDisplayed() {
		return scheduleLaborHeader.isDisplayed();
	}

	public boolean isAddWorkerTextDisplayed() {
		return addWorkerstext.isDisplayed();
	}



}
