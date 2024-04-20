package testCases;

import java.text.DecimalFormat;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.DriverSetup;
import pageObjects.LoanAmountCalculator;
import testBase.BaseClass;

public class VerifyUIOfLoanAmountCalculator {
	LoanAmountCalculator  loanAmountCalculator;
	BaseClass baseClass;
	SoftAssert softAssert;
    
	@Parameters({"browser"})
	 @BeforeClass()
	 public void beforeClass(String brow) {
		 try {
				WebDriver driver = DriverSetup.getDriver(brow);
				baseClass = new BaseClass(driver);
				loanAmountCalculator=new LoanAmountCalculator(driver);
				baseClass.launchLink();
				loanAmountCalculator.chooseLoan();
			} catch (Exception e) {
					e.printStackTrace();
			} 
	 }
	 
	 @Test(dataProvider="HomeLoanCalculatorDDT", dataProviderClass =LoanAmountCalculator.class)
	 public void validateEMIToBePaid(String loanEMI,String interestRate,String loanTenureInYears,String loanTenureInMonths,String fee,String expectedValue){
		 
		 loanAmountCalculator.setValuesLTinYears(loanEMI,interestRate,loanTenureInYears,fee);
		 DecimalFormat df_obj = new DecimalFormat("#.#");
		 softAssert=new SoftAssert();
		 softAssert.assertEquals(loanAmountCalculator.getLoanEMI(),baseClass.number(loanEMI));
		 softAssert.assertEquals(loanAmountCalculator.getInterestRate(),baseClass.number(interestRate));
		 softAssert.assertEquals(df_obj.format(loanAmountCalculator.getLTInYears()),df_obj.format(baseClass.number(loanTenureInYears)));
		 softAssert.assertEquals(loanAmountCalculator.getFee(),baseClass.number(fee));
		 loanAmountCalculator.setValuesLTinMonths(loanEMI,interestRate,loanTenureInMonths,fee);
		 softAssert.assertEquals(df_obj.format(loanAmountCalculator.getLTInMonths()),df_obj.format(baseClass.number(loanTenureInMonths)));
	 }  
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }

}
