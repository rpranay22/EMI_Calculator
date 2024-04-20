package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomePage;
import testBase.BaseClass;

public class VerifyCarLoanEMI {
	 BaseClass baseClass;
	    HomePage homePage;
	    @Parameters({"browser"})
		@BeforeClass()
		public void beforeClass(String brow) {
			try {
				WebDriver driver=DriverSetup.getDriver(brow);
				baseClass=new BaseClass(driver);	
	            homePage=new HomePage(driver);
	            
			} catch (Exception e) {
					e.printStackTrace();
			} 
		}
		
		@Test()
		public void validateCarLoanEMI()  {
			    baseClass.launchLink();
			    homePage.clickRequiredloan();
				homePage.setValues();
				homePage.printEMI();			
		}
		
		@AfterClass()
		public void afterClass() {
			DriverSetup.driverTearDown();
		}

}
