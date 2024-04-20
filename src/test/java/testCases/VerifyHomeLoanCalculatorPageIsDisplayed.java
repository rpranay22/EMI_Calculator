package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomeLoanCalculator;
import pageObjects.HomePage;
import testBase.BaseClass;

public class VerifyHomeLoanCalculatorPageIsDisplayed {
	HomeLoanCalculator homeLoanCalculator;
	BaseClass baseClass;
    HomePage homePage;
    
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
	 public void validateTitle(){
		baseClass.launchLink();
		homeLoanCalculator.chooseHomeLoan();
		Assert.assertEquals(homeLoanCalculator.getTitle(),"Home Loan EMI Calculator with Prepayments, Taxes & Insurance");
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
