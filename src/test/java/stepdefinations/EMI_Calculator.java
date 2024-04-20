package stepdefinations;

import java.io.IOException;
import java.text.DecimalFormat;
import org.junit.Assert;
import Utilities.ExcelUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.EMICalculator;
import testBase.CucumberBaseClass;

public class EMI_Calculator {
	EMICalculator emiCalculator;
	
	@Given("choose Loan Calculator from drop down")
	public void choose_loan_calculator_from_drop_down()  {
		try {
			emiCalculator=new EMICalculator(CucumberBaseClass.getDriver());
			emiCalculator.chooseLoanCalculator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("check emi calculator page is displayed")
	public void check_emi_calculator_page_is_displayed() {
		Assert.assertEquals(emiCalculator.getLoanName(),"Loan EMI");
	}

	
	@When("set the required values of EMI and check correct value is calculated {string}")
	public void set_the_required_values_of_emi_and_check_correct_value_is_calculated(String sheetName) {
		try {
		DecimalFormat df_obj = new DecimalFormat("#.#");
		String path=".\\TestData\\EMICalculatorDDT.xlsx";
		int numOfRows=ExcelUtilities.getRowCount(path,"Sheet1");
		int numOfCols=ExcelUtilities.getCellCount(path,"Sheet1",1);
		String[] val=new String[numOfCols];
		for(int i=0;i<numOfRows;i++) {
			for(int j=0;j<numOfCols;j++) {
				val[j]=ExcelUtilities.getCellData(path,"Sheet1", i+1, j);
			}
			Assert.assertEquals(emiCalculator.setValuesLTinYears(val[0], val[1], val[2], val[4]), val[5]);
			Assert.assertEquals(df_obj.format(emiCalculator.getLoanAmount()),
					df_obj.format(CucumberBaseClass.number(val[0])));
			Assert.assertEquals(df_obj.format(emiCalculator.getInterestRate()),
					df_obj.format(CucumberBaseClass.number(val[1])));
			Assert.assertEquals(df_obj.format(emiCalculator.getLTInYears()),
					df_obj.format(CucumberBaseClass.number(val[2])));
			Assert.assertEquals(df_obj.format(emiCalculator.getFee()),df_obj.format(CucumberBaseClass .number(val[4])));
			
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	          
	    
	}
	
	


}
