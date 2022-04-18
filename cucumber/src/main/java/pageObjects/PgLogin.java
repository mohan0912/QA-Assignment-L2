package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgLogin {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//*[@id='layers']//span[contains(text(),'Sign in to Twitter')]") 
	private WebElement label_SignIn;
	
	@FindBy(how = How.XPATH, using = "//input[@autocomplete='username']") 
	private WebElement txtbx_UserName;
	
	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'Next')]") 
	private WebElement btn_Next;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sorry, we could not find your account.')]") 
	private WebElement err_InvalidUserName;
	
	public PgLogin(WebDriver driver) {
	    PageFactory.initElements(driver, this);
	    this.driver=driver;
	    wait =new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void setUserName(String username) {
		
		wait.until(ExpectedConditions.visibilityOf(txtbx_UserName)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_UserName)).sendKeys(username);
	}
	
	public void clickNext() {
		wait.until(ExpectedConditions.visibilityOf(btn_Next)).click();
	}
	
	public boolean verifySignInLabelExist() {
		return wait.until(ExpectedConditions.visibilityOf(label_SignIn)).isDisplayed();
	}
	
	public boolean verifyErrInvalidUserName() {
		return wait.until(ExpectedConditions.visibilityOf(err_InvalidUserName)).isDisplayed();
	}
	
}
