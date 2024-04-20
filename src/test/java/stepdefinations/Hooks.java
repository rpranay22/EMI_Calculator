package stepdefinations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import testBase.CucumberBaseClass;


public class Hooks {

	 WebDriver driver;
	 Properties p;
     
	@Before
    public void setup() throws IOException
    {
    	driver=CucumberBaseClass.initilizeBrowser();
    	    	
    	p=CucumberBaseClass.getProperties();
    	driver.get(p.getProperty("URL"));
    	driver.manage().window().maximize();
    
    			
	}
		
    
    @After
    public void tearDown(Scenario scenario) {
        		
       driver.quit();
       
    }
    

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	TakesScreenshot ts = (TakesScreenshot) driver;

    	       byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

    	       // Get the step name

    	       String stepName = scenario.getName();

    	       stepName = stepName.replaceAll("[^a-zA-Z0-9]", "");

    	       // Save the screenshot with the step name

    	       if (scenario.isFailed()) {

    	           scenario.attach(screenshot, "image/png", stepName + "_Failure");

    	       } else {

    	           scenario.attach(screenshot,"image/png", stepName + "_Success");

    	       }
      
    }
   
}
