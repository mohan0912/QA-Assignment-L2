package pageObjects;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PgExplore {

	WebDriverWait wait;
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//div/input[@data-testid='SearchBox_Search_Input']")
	WebElement SearchBox;

	@FindBy(how = How.XPATH, using="//article[@data-testid='tweet']")
	List<WebElement> Tweets;

	@FindBy(how = How.XPATH, using="//article[@data-testid='tweet']//time")
	List<WebElement> TweetsTime;
	
	@FindBy(how = How.XPATH, using="//div[@data-testid='TypeaheadUser']//div[@class='css-1dbjc4n r-1ny4l3l']//span/span")
	List<WebElement> searchList;

	@FindBy(how = How.TAG_NAME, using ="body")
	WebElement body;



	public PgExplore(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		this.driver=driver;
	}

	public void setSearchBoxAndSearch(String SearchText) throws InterruptedException {
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(SearchBox)).clear();
		wait.until(ExpectedConditions.visibilityOf(SearchBox)).sendKeys(SearchText);
		Thread.sleep(5000);
		int size = wait.until(ExpectedConditions.visibilityOfAllElements(searchList)).size();
		for(int i =0;i<size;i++) {
			if(wait.until(ExpectedConditions.visibilityOfAllElements(searchList)).get(i).getText().equals(SearchText)){
				action.moveToElement(searchList.get(i)).click().build().perform();
				break;
			}
			else {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();		}
		}
	}

	public List<String> getTweets(int timeDiff) throws InterruptedException{
		boolean flag=true;
		String tempData=null;
		ArrayList<String> twtData = new ArrayList<String>();
		List<WebElement> Tweets=driver.findElements(By.xpath("//article[@data-testid='tweet']"));
		int a = 0;
		//get current date in UTC
		Instant todayDate = java.time.Clock.systemUTC().instant();

		while(flag){
			Thread.sleep(5000);
			for(int i =a;i<Tweets.size();i++) {
				WebElement tweet=Tweets.get(i);
				tempData="";
				a++;
				Thread.sleep(3000);
				//get UTC date from Span
				String timeText=tweet.findElement(By.xpath(".//time")).getAttribute("datetime");
				Instant tweetDate= Instant.parse(timeText);
				//get diff in date in current date an Span Date
				Duration res = Duration.between(tweetDate, todayDate);

				//compare the Diff in Hours is less than or equal to difference given
				if(res.toHours()<=timeDiff) {
					//get the child elemnt for each tweet
					List<WebElement>tweetDatas=tweet.findElements(By.xpath(".//div[@lang='en']/*"));
					for(WebElement tweetData:tweetDatas) {
						tempData= tempData+tweetData.getText();
					}
					//if tweet has less than 50 Chars
					if(tempData.length()<=50) {
						twtData.add(tempData);
					}
					//if tweet has more than 50 chars
					else {
						int val=(tempData.length()%50==0)?tempData.length()/50:(tempData.length()/50)+1;
						int k =1;
						for (int j = 0; j < tempData.length(); j += 50) {

							twtData.add("==="+ k +" of " +val+"====="+tempData.substring(j, Math.min(tempData.length(), j + 50)));
							k++;
						}
					}
					// go to Next tweet if the tweet is not present in current tweet list then add the tweet to the list
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", tweet);
					Thread.sleep(3000);
					List<WebElement> newTweets=driver.findElements(By.xpath(".//article[@data-testid='tweet']"));
					for(WebElement newTweet:newTweets) {
						if(!Tweets.contains(newTweet)) {
							Tweets.add(newTweet);
						}
					}
				}
				//the Diff in Hours is greater than to difference given
				else {
					flag=false;
					break;
				}
			}
		}
		return twtData;
	}		

}


