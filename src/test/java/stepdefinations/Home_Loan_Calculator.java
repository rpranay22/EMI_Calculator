package stepdefinations;

import java.io.IOException;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomeLoanCalculator;
import testBase.CucumberBaseClass;

public class Home_Loan_Calculator {
	    HomeLoanCalculator homeLoanCalculator;
		@Given("choose Home Loan Calculator from dropdown")
		public void choose_home_loan_calculator_from_dropdown() {
			 try {
				homeLoanCalculator=new HomeLoanCalculator(CucumberBaseClass.getDriver());
				homeLoanCalculator.chooseHomeLoan();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		@When("check Home Loan Calculator page is displayed")
		public void check_home_loan_calculator_page_is_displayed() {
			Assert.assertEquals(homeLoanCalculator.getTitle(),"Home Loan EMI Calculator with Prepayments, Taxes & Insurance");
		}
		
		@When("set the required value")
		public void set_the_required_value() {
			homeLoanCalculator.setValues();
			
		}
		
		@Then("save the values to the excel sheet")
		public void save_the_values_to_the_excel_sheet() {
			try {
				homeLoanCalculator.setDataInExcel();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

}
