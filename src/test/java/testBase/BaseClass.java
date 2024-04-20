package testBase;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.Screenshot;

public class BaseClass {
	public WebDriver driver;
	public FileReader fileReader;
	public Properties properties;
	Screenshot screenshot;
	
	
	// Constructor
	public BaseClass(WebDriver driver) throws IOException{
		this.driver=driver;
		screenshot=new Screenshot(driver);
		fileReader=new FileReader(".\\src\\test\\resources\\config.properties");
		properties=new Properties();
		properties.load(fileReader);
		// It helps us to find PageFactory Variables
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	
	public BaseClass() {
		// TODO Auto-generated constructor stub
	}



	// To launch the URL
	public void launchLink() {	
			driver.get(properties.getProperty("URL"));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	// Select required loan
	public void clickRequiredLoan(String option, List<WebElement> options) {
		for (WebElement webElement : options) {
			
			if (webElement.getText().equalsIgnoreCase(option)) {
				webElement.click();
				return;
			}
		}
	}

	public static void fillValue(WebElement valueBox, WebElement clickBtn, String value) {
		valueBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		valueBox.sendKeys(Keys.BACK_SPACE);
		valueBox.sendKeys(value);
		clickBtn.click();
	}
	
	public void scrollPage(WebElement elementInPage) {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",elementInPage);
	}
	
    public  float number(String str) {
 	   int len=str.length();
 	   String org="";
 	   if(str.charAt(len-1)=='L') {
 		   str=str+"00000";
 	   } 
 	  len=str.length(); 
 	   for(int i=0;i<len;i++) {
 		   if((str.charAt(i)>=48 & str.charAt(i)<=57) | (str.charAt(i)=='.')) {
 			   org=org+str.charAt(i);
 		   }
 	   }
 	   
 	   return 	Float.parseFloat(org);
    }
    
  
	
}
