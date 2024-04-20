package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.DriverSetup;
import pageObjects.LoanTenureCalculator;
import testBase.BaseClass;

public class VerifyUIOfLoanTenure {
	LoanTenureCalculator loanTenureCalculator;
	BaseClass baseClass;
	SoftAssert softAssert;
    
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
	 public void validateEMIToBePaid(String loanAmount,String emi,String interestRate,String fee,String expectedValue){
		 
		    loanTenureCalculator.setValuesLTInYears(loanAmount,emi,interestRate,fee);
		    softAssert=new SoftAssert();
			softAssert.assertEquals(loanTenureCalculator.getLoanAmount(), baseClass.number(loanAmount));
			softAssert.assertEquals(loanTenureCalculator.getLoanEMI(), baseClass.number(emi));
			softAssert.assertEquals(loanTenureCalculator.getInterestRate(), baseClass.number(interestRate));
			softAssert.assertEquals(loanTenureCalculator.getFee(),baseClass.number(fee));
		 
		 
	 }  
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
