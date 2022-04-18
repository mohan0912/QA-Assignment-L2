package stepDefination;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import managers.PageObjectManagers;
import pageObjects.PgLogin;
import pageObjects.PgEnterPassword;
import pageObjects.PgEnterUserNamePhone;
import pageObjects.PgHomeScreen;
import pageObjects.PgExplore;
import pageObjects.PgProfile;
import reusableComponents.ReusableComponents;

public class StepDefination {

	WebDriver driver;
	PageObjectManagers PageObjectManagers;
	PgLogin PgLogin;
	PgEnterPassword PgEnterPassword;
	PgEnterUserNamePhone PgEnterUserNamePhone;
	PgHomeScreen PgHomeScreen;
	PgProfile PgProfile;
	PgExplore PgExplore;
	String url;
	String ImplicitlyWait;
	String Email;
	String Password;
	String UserName;
	BufferedReader reader;
	Properties properties;
	
	public StepDefination() throws IOException {
		reader  = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\testData\\config.properties"));
		properties = new Properties();
		properties.load(reader);
		reader.close();
		
		url=properties.getProperty("url");
		Email=properties.getProperty("Email");
		Password=properties.getProperty("Password");
		UserName=properties.getProperty("UserName");
		ImplicitlyWait = properties.getProperty("ImplicitlyWait");
	}
	
	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ImplicitlyWait)));

	}

	@Given("^I am on the login screen of twitter$")
	public void userOnLoginScreen(){

		driver.get(url);
		PageObjectManagers = new PageObjectManagers(driver);
		PgLogin = PageObjectManagers.getPgLogin();
		Assert.assertTrue(PgLogin.verifySignInLabelExist());
	}


	@And("^I validate required field validation for Email field$")
	public void validateRequiredErrMsgEmail()
	{
		PgLogin = PageObjectManagers.getPgLogin();		
		PgLogin.clickNext();
		Assert.assertTrue(PgLogin.verifyErrInvalidUserName());
	}

	@And("^I enter valid email address and select Next$")
	public void enterEmailAndNext() {
		PgLogin.setUserName(Email);
		PgLogin.clickNext();
	}

	@And("^I navigated to enter Password page having prefilled disabled email field$")
	public void navigatedtoEnterPassword() {
		PgEnterUserNamePhone=PageObjectManagers.getPgEnterUserNamePhone();
		if(PgEnterUserNamePhone.verifyEnterUserNameLabelExist())
		{
			PgEnterUserNamePhone.setUserName(UserName);
			PgEnterUserNamePhone.clickNext();
		}

		PgEnterPassword=PageObjectManagers.getPgEnterPassword();
		PgEnterPassword.verifyEnterPasswordLabelExist();
		PgEnterPassword.verifyEmailValue(Email);
		if(PgEnterPassword.getEmailDisabledAttribute().equalsIgnoreCase("true"))
		{
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		System.out.println(PgEnterPassword.getLoginButtonClassAttribute());
		System.out.println(PgEnterPassword.getPassword());

	}

	@And("^I verify Login button disabled for empty Password field$")
	public void verifyLoginButtonDisabled() {
		if(PgEnterPassword.getPassword().trim()=="" && 
				PgEnterPassword.getLoginButtonClassAttribute().contains("r-icoktb") &&
				PgEnterPassword.getLoginButtonClassAttribute().contains("r-64el8z")) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}

	}

	@And("^I enter valid password and select Login$")
	public void enterPasswordLogin() {
		PgEnterPassword.setPassword(Password);
		if(PgEnterPassword.getLoginButtonClassAttribute().contains("css-18t94o4")) {
			Assert.assertTrue(true);
			PgEnterPassword.clickLogin();
		}
		else
		{
			Assert.assertTrue(false,"Login Button Not Enabled ");
		}
	}

	@Then("^I should be navigated to Home Page$")
	public void navigateToHomeFromLogin() {
		PgHomeScreen=PageObjectManagers.getPgHomeScreen();
		if(PgHomeScreen.verifyHomeLabelExist() && PgHomeScreen.getTitle().contains("Home / Twitter")) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false,"Login was not sucessfull");
		}

	}
	
	@And("^I select Profile link$")
	public void selectProfileLink() {
		PgHomeScreen.clickProfile();
	}
	
	@And("^I select Update Profile on Profile Page$")
	public void selectUpdateProfileLink() {
		PgProfile=PageObjectManagers.getPgProfile();
		PgProfile.clickEditProfile();
	}

	@And("^I update the Header Photo and Profile photo$")
	public void updateHeaderAndProfilePic() throws InterruptedException, AWTException {
		PgProfile.clickAddHeaderPhoto();
		ReusableComponents.uploadFile(System.getProperty("user.dir")+"\\testData\\Images\\HeaderImage.png");
		PgProfile.clickApply();
		PgProfile.clickAddProfilePhoto();
		ReusableComponents.uploadFile(System.getProperty("user.dir")+"\\testData\\Images\\ProfilePiC.jpg");
		PgProfile.clickApply();
	}
	
	@And("^I update the Location as (.*), Website as (.*), Bio as (.*) and Select Save$")
	public void updateLocationUrlBio(String location, String url, String bio) {
		PgProfile.setLocation(location);
		PgProfile.setBio(bio);
		PgProfile.setWebSite(url);
		PgProfile.clickSave();
	}
	
	@Then("^I should see the updated value for Location as (.*), Website as (.*), Bio as (.*) on Profile Page$")
	public void verifyUpdateLocationUrlBio(String location, String url, String bio) throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		if(PgProfile.getLocation().equalsIgnoreCase(location)) {
			Assert.assertTrue(true, "The Location is updated");
		}
		else {
			Assert.assertTrue(false, "The Location is not updated");
		}
		
		if(PgProfile.getWebsite().equalsIgnoreCase(url.replaceAll("http://", "").replaceAll("https://", ""))) {
			Assert.assertTrue(true, "The Website is updated");
		}
		else {
			Assert.assertTrue(false, "The Website is not updated");
		}
		
		if(PgProfile.getYourBio().equalsIgnoreCase(bio)) {
			Assert.assertTrue(true, "The Bio is updated");
		}
		else {
			Assert.assertTrue(false, "The Bio is not updated");
		}
		
	}
	
	@And("^I select Explore link$")
	public void selectExploreLink() {
		PgHomeScreen.clickExplore();
	}
	
	@And("^I search for The Times Of India page$")
	public void searchForTimesOFIndia() throws InterruptedException {
		PgExplore=PageObjectManagers.getPgExplore();
		PgExplore.setSearchBoxAndSearch("The Times Of India");
	}
	
	@And("I extract and display all the tweet received in last {int} hours")
	public void extractAndDispalyTweet(int time) throws InterruptedException {
		List<String> tweets=new ArrayList<String>();
		System.out.println(time);
		tweets=PgExplore.getTweets(time);
		
		for(String s:tweets) {
			System.out.println(s);
		}
	}
	
	@After
	public void afterTest() {
		driver.quit();
	}
	
	

}
