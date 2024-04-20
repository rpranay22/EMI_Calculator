package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomePage;
import pageObjects.LoanAmountCalculator;
import testBase.BaseClass;

public class VerifyLoanAmountCalculatorIsDisplayed {
	LoanAmountCalculator loanAmountCalculator;
	BaseClass baseClass;
    HomePage homePage;
    @Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				loanAmountCalculator= new LoanAmountCalculator(driver);
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test()
	 public void validateTitle(){
		baseClass.launchLink();
		loanAmountCalculator.chooseLoan();
		Assert.assertEquals(loanAmountCalculator.getTitle(),true);
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
		
}
