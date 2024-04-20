package testCases;


import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomePage;
import testBase.BaseClass;

public class VerifyCarLoanPageIsDisplayed {
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
		public void validateCarLoanPageIsDisplayed() throws IOException, InterruptedException  {
			    baseClass.launchLink();
				homePage.clickRequiredloan();
				Assert.assertEquals(homePage.getCarLoanPage(),"Car Loan Amount");	
				
		}
		
		@AfterClass()
		public void afterClass() {
			DriverSetup.driverTearDown();
		}

}
