package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.HomePage;
import pageObjects.LoanTenureCalculator;
import testBase.BaseClass;

public class VerifyLoanTenureCalculatorIsDisplayed {
	LoanTenureCalculator loanTenureCalculator;
	BaseClass baseClass;
    HomePage homePage;
    
    @Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				loanTenureCalculator= new LoanTenureCalculator(driver);
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test()
	 public void validateTitle(){
		baseClass.launchLink();
		loanTenureCalculator.chooseLoan();
		Assert.assertEquals(loanTenureCalculator.getTitle(),true);
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
