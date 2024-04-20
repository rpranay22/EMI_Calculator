package stepdefinations;

import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Assert;

import Utilities.ExcelUtilities;
import io.cucumber.java.en.When;
import pageObjects.LoanTenureCalculator;
import testBase.CucumberBaseClass;

public class Loan_Tenure_Calculator {

	LoanTenureCalculator loanTenureCalculator;
	@When("check loan tenure calculator page is displayed")
	public void check_loan_tenure_calculator_page_is_displayed() throws IOException {
		loanTenureCalculator=new LoanTenureCalculator(CucumberBaseClass.getDriver());
		loanTenureCalculator.chooseLoan();
		Assert.assertEquals(loanTenureCalculator.getTitle(),true);
	}

	@When("set the required values of loan tenure and check correct value is calculated {string}")
	public void set_the_required_values_of_loan_tenure_and_check_correct_value_is_calculated(String string) {
		try {
			DecimalFormat df_obj = new DecimalFormat("#.#");
			String path=".\\TestData\\LoanTenureCalculatorDDT.xlsx";
			int numOfRows=ExcelUtilities.getRowCount(path,"Sheet1");
			int numOfCols=ExcelUtilities.getCellCount(path,"Sheet1",1);
			String[] val=new String[numOfCols];
			for(int i=0;i<numOfRows;i++) {
				for(int j=0;j<numOfCols;j++) {
					val[j]=ExcelUtilities.getCellData(path,"Sheet1", i+1, j);
				}
				Assert.assertEquals(loanTenureCalculator.setValuesLTInYears(val[0], val[1], val[2], val[3]), val[4]);
			Assert.assertEquals(df_obj.format(loanTenureCalculator. getLoanAmount()),
						df_obj.format(CucumberBaseClass.number(val[0])));
				Assert.assertEquals(df_obj.format(loanTenureCalculator.getInterestRate()),
						df_obj.format(CucumberBaseClass.number(val[2])));
				
			Assert.assertEquals(df_obj.format(loanTenureCalculator.getFee()),df_obj.format(CucumberBaseClass .number(val[3])));
				
				
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		      
	}
}
