package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.EMICalculator;
import testBase.BaseClass;

public class VerifyEMICalculatorPageIsDisplayed {
    EMICalculator emiCalculator;
	BaseClass baseClass;
	@Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				emiCalculator=new EMICalculator (driver);
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test()
	 public void validateTitle(){
		baseClass.launchLink();
		emiCalculator.chooseLoanCalculator();
		Assert.assertEquals(emiCalculator.getLoanName(),"Loan EMI");
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }

}
