package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.DriverSetup;
import pageObjects.LoanTenureCalculator;
import testBase.BaseClass;

public class VerifyLoanTenure {
	LoanTenureCalculator loanTenureCalculator;
	BaseClass baseClass;
   
	@Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				loanTenureCalculator=new LoanTenureCalculator(driver);
				baseClass.launchLink();
				 loanTenureCalculator.chooseLoan();
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test(dataProvider="TenureCalculatorDDT", dataProviderClass =LoanTenureCalculator.class)
	 public void validateTenure(String loanAmount,String emi,String interestRate,String fee,String expectedValue){
		 
		 Assert.assertEquals(loanTenureCalculator.setValuesLTInYears(loanAmount,emi,interestRate,fee),expectedValue);
		 
	 }
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}

