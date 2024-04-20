package Utilities;


import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class DriverSetup {
	public static WebDriver driver;
	
	public static WebDriver getDriver(String browser) throws InterruptedException, IOException {
			if(browser.equalsIgnoreCase("edge")) {		
			driver=new EdgeDriver();	
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		return driver;
	}
	
	public static void driverTearDown() {
		driver.quit();
	}

}
