package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgProfile {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(how = How.XPATH, using = "//a[@href='/i/flow/setup_profile']/div/span/span") 
	private WebElement link_SetUpProfile;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Pick a profile picture')]/following-sibling::div//div[@aria-label='Add photos or video']") 
	private WebElement link_AddProfilePhotoFirst;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Pick a header')]/following-sibling::div//div[@aria-label='Add photos or video']") 
	private WebElement link_AddHeaderPhotoFirst;

	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'Skip for now')]") 
	private WebElement btn_SkipForNow;

	@FindBy(how = How.XPATH, using = "//div/textarea[@data-testid='ocfEnterTextTextInput']") 
	private WebElement txtbx_YourBioFirst;

	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'See profile')]") 
	private WebElement btn_SeeProfile;

	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'Apply')]") 
	private WebElement btn_Apply;

	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'Next')]") 
	private WebElement btn_Next;

	@FindBy(how = How.XPATH, using = "//span/span/span[contains(text(),'Your profile is updated')]") 
	private WebElement label_ProfileUpdated;

	@FindBy(how = How.XPATH, using = "//a[@href='/settings/profile']/div/span/span") 
	private WebElement link_EditProfile;

	@FindBy(how = How.XPATH, using = "//div[@aria-label='Add banner photo']/div") 
	private WebElement link_AddHeaderPhoto;

	@FindBy(how = How.XPATH, using = "//div[@aria-label='Add avatar photo']/div") 
	private WebElement link_AddProfilePhoto;

	@FindBy(how = How.XPATH, using = "//textarea[@name='description']") 
	private WebElement txtbx_YourBio;

	@FindBy(how = How.XPATH, using = "//input[@name='location']") 
	private WebElement txtbx_Location;

	@FindBy(how = How.XPATH, using = "//input[@name='url']") 
	private WebElement txtbx_Website;

	@FindBy(how = How.XPATH, using = "//div/span/span[contains(text(),'Save')]") 
	private WebElement btn_Save;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='UserDescription']/span") 
	private WebElement Label_YourBio;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='UserProfileHeader_Items']/span[1]") 
	private WebElement Label_Location;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='UserProfileHeader_Items']/a/span") 
	private WebElement Label_Website;



	public PgProfile(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		this.driver=driver;
	}

	public void clickEditProfile() {
		wait.until(ExpectedConditions.visibilityOf(link_EditProfile)).click();
	}

	public void clickAddHeaderPhoto() {
		wait.until(ExpectedConditions.visibilityOf(link_AddHeaderPhoto)).click();
	}

	public void clickAddProfilePhoto() {
		wait.until(ExpectedConditions.visibilityOf(link_AddProfilePhoto)).click();
	}
	
	public void clickApply() {
		wait.until(ExpectedConditions.visibilityOf(btn_Apply)).click();
	}

	public void setBio(String bio) {

		wait.until(ExpectedConditions.visibilityOf(txtbx_YourBio)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_YourBio)).sendKeys(bio);
	}

	public void setLocation(String location) {

		wait.until(ExpectedConditions.visibilityOf(txtbx_Location)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_Location)).sendKeys(location);
	}

	public void setWebSite(String website) {

		wait.until(ExpectedConditions.visibilityOf(txtbx_Website)).clear();
		wait.until(ExpectedConditions.visibilityOf(txtbx_Website)).sendKeys(website);
	}
	
	public void clickSave() {
		wait.until(ExpectedConditions.visibilityOf(btn_Save)).click();
	}
	
	public String getYourBio() {
		return wait.until(ExpectedConditions.visibilityOf(Label_YourBio)).getText();
	}
	
	public String getLocation() {
		return wait.until(ExpectedConditions.visibilityOf(Label_Location)).getText();
	}
	
	public String getWebsite() {
		return wait.until(ExpectedConditions.visibilityOf(Label_Website)).getText();
	}
}
