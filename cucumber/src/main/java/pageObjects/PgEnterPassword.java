package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgEnterPassword {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//*[@id='layers']//span[contains(text(),'Enter your password')]") 
	private WebElement label_EnterPassword;
	
	@FindBy(how = How.XPATH, using = "//input[@name='password']") 
	private WebElement txtbx_Password;
	
	@FindBy(how = How.XPATH, using = "//input[@name='email']") 
	private WebElement txtbx_Disabled_Email;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@role,'button') and contains(@data-testid,'LoginForm_Login_Button')]") 
	private WebElement btn_LogIn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Log in')]") 
	private WebElement errInvalidPassword;
	
	public PgEnterPassword(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    PageFactory.initElements(driver, this);
	}
	
	public void setPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(txtbx_Password)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_Password)).sendKeys(password);;
	}
	
	public String getPassword() {
		return wait.until(ExpectedConditions.visibilityOf(txtbx_Password)).getText();
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_LogIn)).click();
	}
	
	public boolean verifyEnterPasswordLabelExist() {
		return wait.until(ExpectedConditions.visibilityOf(label_EnterPassword)).isDisplayed();
	}
	
	public boolean verifyEmailValue(String email) {
		if(txtbx_Disabled_Email.getText().equalsIgnoreCase(email)) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getEmailDisabledAttribute() {
		return txtbx_Disabled_Email.getAttribute("disabled");
	}
	
	public String getLoginButtonClassAttribute() {
		return wait.until(ExpectedConditions.visibilityOf(btn_LogIn)).getAttribute("class");
	}

}
