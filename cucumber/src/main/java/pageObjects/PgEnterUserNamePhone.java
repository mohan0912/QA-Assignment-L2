package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgEnterUserNamePhone {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//*[@id='layers']//span[contains(text(),'Enter your phone number or username')]") 
	private WebElement label_EnterUserNamePhone;
	
	@FindBy(how = How.XPATH, using = "//input[@name='text']") 
	private WebElement txtbx_UserName;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@role,'button') and contains(@data-testid,'ocfEnterTextNextButton')]") 
	private WebElement btn_Next;
	
	public PgEnterUserNamePhone(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	
	public void setUserName(String password) {
		//wait.until(ExpectedConditions.visibilityOf(txtbx_UserName)).click();
		wait.until(ExpectedConditions.visibilityOf(txtbx_UserName)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_UserName)).sendKeys(password);;
	}
	
	public void clickNext() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_Next)).click();
	}
	
	public boolean verifyEnterUserNameLabelExist() {
		return driver.findElements(By.xpath("//*[@id='layers']//span[contains(text(),'Enter your phone number or username')]")).size()>0;
	}
	
}
