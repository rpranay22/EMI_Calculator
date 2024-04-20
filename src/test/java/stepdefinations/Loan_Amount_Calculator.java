package stepdefinations;

import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Assert;


import Utilities.ExcelUtilities;
import io.cucumber.java.en.When;
import pageObjects.LoanAmountCalculator;
import testBase.CucumberBaseClass;

public class Loan_Amount_Calculator {
	LoanAmountCalculator loanAmountCalculator;
	
	@When("check loan amount calculator page is displayed")
	public void check_loan_amount_calculator_page_is_displayed() {
		try {
			loanAmountCalculator=new LoanAmountCalculator(CucumberBaseClass.getDriver());
			loanAmountCalculator.chooseLoan();
			Assert.assertEquals(loanAmountCalculator.getTitle(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@When("set the required values of loan amount and check correct value is calculated {string}")
	public void set_the_required_values_of_loan_amount_and_check_correct_value_is_calculated(String string) {
		 
		try {
			DecimalFormat df_obj = new DecimalFormat("#.#");
			String path=".\\TestData\\LoanAmountCalculatorDDT.xlsx";
			int numOfRows=ExcelUtilities.getRowCount(path,"Sheet1");
			int numOfCols=ExcelUtilities.getCellCount(path,"Sheet1",1);
			String[] val=new String[numOfCols];
			for(int i=0;i<numOfRows;i++) {
				for(int j=0;j<numOfCols;j++) {
					val[j]=ExcelUtilities.getCellData(path,"Sheet1", i+1, j);
				}
				Assert.assertEquals(loanAmountCalculator.setValuesLTinYears(val[0], val[1], val[2], val[4]), val[5]);
				Assert.assertEquals(df_obj.format(loanAmountCalculator.getLoanEMI()),
						df_obj.format(CucumberBaseClass.number(val[0])));
				Assert.assertEquals(df_obj.format(loanAmountCalculator.getInterestRate()),
						df_obj.format(CucumberBaseClass.number(val[1])));
				Assert.assertEquals(df_obj.format(loanAmountCalculator.getLTInYears()),
						df_obj.format(CucumberBaseClass.number(val[2])));
				Assert.assertEquals(df_obj.format(loanAmountCalculator.getFee()),df_obj.format(CucumberBaseClass .number(val[4])));
				
				
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		      
		
	}
}
