package testCases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomeLoanCalculator;
import testBase.BaseClass;

public class SaveDataInExcel  {
		
	HomeLoanCalculator homeLoanCalculator;
	BaseClass baseClass;
	
	@Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				homeLoanCalculator = new HomeLoanCalculator(driver);
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test()
	 public void saveDisplayedData(){  
			try {
				baseClass.launchLink();
				homeLoanCalculator.chooseHomeLoan();
				homeLoanCalculator.setValues();
				homeLoanCalculator.setDataInExcel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}		
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
