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

public class VerifyEMIToBePaid {
	
	
	EMICalculator emiCalculator;
	BaseClass baseClass;
	@Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				emiCalculator=new EMICalculator(driver);
				baseClass.launchLink();
				emiCalculator.chooseLoanCalculator();
			} catch (Exception e) { 
					e.printStackTrace();
			} 
	 }
	 
	 @Test(dataProvider="HomeLoanCalculatorDDT", dataProviderClass =EMICalculator.class)
	 public void validateEMIToBePaidLTInYear(String loanAmount,String interestRate,String loanTenureInYears,String loanTenureInMonths,String fee,String expectedValue) throws InterruptedException{
		
		Assert.assertEquals(emiCalculator.setValuesLTinYears(loanAmount,interestRate,loanTenureInYears,fee),expectedValue);
		
	 }
	 
	 @Test(dataProvider="HomeLoanCalculatorDDT", dataProviderClass =EMICalculator.class)
	 public void validateEMIToBePaidLTInMonths(String loanAmount,String interestRate,String loanTenureInYears,String loanTenureInMonths,String fee,String expectedValue){
		
		Assert.assertEquals(emiCalculator.setValuesLTinMonths(loanAmount,interestRate,loanTenureInMonths,fee),expectedValue);
		
	 }
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
