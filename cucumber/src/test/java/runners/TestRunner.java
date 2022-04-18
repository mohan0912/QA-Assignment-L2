package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/resources/features",
		glue= {"stepDefination"},
		tags= "@getTweets"
		)
public class TestRunner extends AbstractTestNGCucumberTests {
}