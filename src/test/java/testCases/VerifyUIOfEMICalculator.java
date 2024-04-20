package testCases;

import java.text.DecimalFormat;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.DriverSetup;
import pageObjects.EMICalculator;
import testBase.BaseClass;

public class VerifyUIOfEMICalculator {
	EMICalculator emiCalculator;
	BaseClass baseClass;
	SoftAssert softAssert;
    
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
	 public void validateEMIToBePaid(String loanAmount,String interestRate,String loanTenureInYears,String loanTenureInMonths,String fee,String expectedValue){
		 emiCalculator.setValuesLTinYears(loanAmount,interestRate,loanTenureInYears,fee);
		 DecimalFormat df_obj = new DecimalFormat("#.#");
		 softAssert=new SoftAssert();
		 softAssert.assertEquals(emiCalculator.getLoanAmount(),baseClass.number(loanAmount));
		 softAssert.assertEquals(emiCalculator.getInterestRate(),baseClass.number(interestRate));
		 softAssert.assertEquals(df_obj.format(emiCalculator.getLTInYears()),df_obj.format(baseClass.number(loanTenureInYears)));
		 softAssert.assertEquals(emiCalculator.getFee(),baseClass.number(fee));
		 emiCalculator.setValuesLTinMonths(loanAmount,interestRate,loanTenureInMonths,fee);
		 softAssert.assertEquals(df_obj.format(emiCalculator.getLTInMonths()),df_obj.format(baseClass.number(loanTenureInMonths)));
	 }   
	 
	 @AfterClass()
	 public void afterClass() {
		 DriverSetup.driverTearDown();		 
	 }
}
