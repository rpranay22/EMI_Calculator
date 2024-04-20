package stepdefinations;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import testBase.CucumberBaseClass;

public class CarLoanPage {
	
	HomePage homePage;
	@Given("click on car loan")
	public void click_on_required_loan() throws IOException {
		
			homePage=new HomePage(CucumberBaseClass.getDriver());
			homePage.clickRequiredloan();
		
		
	}

	@When("check car loan page is displayed")
	public void check_car_loan_page_is_displayed() {
		homePage.clickRequiredloan();
		Assert.assertEquals(homePage.getCarLoanPage(),"Car Loan Amount");	
	}

	@When("set the required values")
	public void set_the_required_values() {
		homePage.setValues();
	
	}

	@Then("print the EMI of any month")
	public void print_the_emi_of_any_month() {
		homePage.printEMI();	    
	}

}
