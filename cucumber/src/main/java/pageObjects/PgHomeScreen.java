package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgHomeScreen {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//h2/span[text()='Home']") 
	private WebElement label_Home;
	
	@FindBy(how = How.XPATH, using = "//a[@href='/home' and @data-testid='AppTabBar_Home_Link']") 
	private WebElement link_Home;
	
	@FindBy(how = How.XPATH, using = "//a[@href='/explore' and @data-testid='AppTabBar_Explore_Link']") 
	private WebElement link_Explore;
	
	@FindBy(how = How.XPATH, using = "//a[@data-testid='AppTabBar_Profile_Link']") 
	private WebElement link_Profile;
	
	public PgHomeScreen(WebDriver driver) {
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	    wait=new WebDriverWait(driver,Duration.ofSeconds(10));    
	}
	
	public void clickHome() {
		wait.until(ExpectedConditions.elementToBeClickable(link_Home)).click();
	}
	
	public void clickExplore() {
		wait.until(ExpectedConditions.elementToBeClickable(link_Explore)).click();
	}
	
	public void clickProfile() {
		wait.until(ExpectedConditions.elementToBeClickable(link_Profile)).click();
	}
	
	public boolean verifyHomeLabelExist() {
		return wait.until(ExpectedConditions.visibilityOf(label_Home)).isDisplayed();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

}
