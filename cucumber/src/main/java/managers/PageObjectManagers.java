package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.PgLogin;
import pageObjects.PgEnterPassword;
import pageObjects.PgHomeScreen;
import pageObjects.PgProfile;
import pageObjects.PgEnterUserNamePhone;
import pageObjects.PgExplore;

public class PageObjectManagers {
	
	private WebDriver driver;
	
	private PgLogin PgLogin;
	private PgEnterPassword PgEnterPassword;
	private PgHomeScreen PgHomeScreen;
	private PgProfile PgProfile;
	private PgEnterUserNamePhone PgEnterUserNamePhone;
	private PgExplore PgExplore;
	
	
	public PageObjectManagers(WebDriver driver) {

		this.driver = driver;

	}
	
	public PgLogin getPgLogin() {
		return (PgLogin==null)?PgLogin=new PgLogin(driver) : PgLogin;
		
	}
	
	public PgEnterPassword getPgEnterPassword() {
		return (PgEnterPassword==null)?PgEnterPassword=new PgEnterPassword(driver) : PgEnterPassword;
		
	}
	
	public PgHomeScreen getPgHomeScreen() {
		return (PgHomeScreen==null)?PgHomeScreen=new PgHomeScreen(driver) : PgHomeScreen;
		
	}
	
	public PgProfile getPgProfile() {
		return (PgProfile==null)?PgProfile=new PgProfile(driver) : PgProfile;
		
	}
	
	public PgEnterUserNamePhone getPgEnterUserNamePhone()
	{
		return (PgEnterUserNamePhone==null)?PgEnterUserNamePhone= new PgEnterUserNamePhone(driver):PgEnterUserNamePhone;
	}
	
	public PgExplore getPgExplore()
	{
		return (PgExplore==null)?PgExplore= new PgExplore(driver):PgExplore;
	}
	

}
