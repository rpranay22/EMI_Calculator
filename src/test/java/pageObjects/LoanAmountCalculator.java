package pageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import Utilities.ExcelUtilities;
import testBase.BaseClass;

public class LoanAmountCalculator extends BaseClass{
	 Object[][] data;
	public LoanAmountCalculator(WebDriver driver) throws IOException {
		super(driver);
	}
	public  LoanAmountCalculator() {
		
	}
	
	@FindBy(css ="#loanemi")WebElement loanEMI;
	@FindBy(css ="#lemiwrapper > div.sep.row.form-group.lemi > div > div > div > span")WebElement loanEMIRupeesBtn;
	@FindBy(css ="#loaninterest")WebElement loanInterest;
	@FindBy(css ="#lintwrapper > div.sep.row.form-group.lint > div > div > div > span")WebElement loanInterestPercentBtn;
	@FindBy(css ="#loanterm")WebElement loanTerm;
	@FindBy(css="#ltermwrapper > div.sep.row.form-group.lterm > div > div > div > div > div > label.btn.btn-secondary.active") WebElement loanTermYearBtn;
	@FindBy(css="#ltermwrapper > div.sep.row.form-group.lterm > div > div > div > div > div > label:nth-child(2)") WebElement loanTermMonthBtn;
	@FindBy(css="#loanfees") WebElement loanFee;
	@FindBy(css="#lfeeswrapper > div.sep.row.form-group.lfees > div > div > div > span") WebElement loanFeeRupeeBtn;
	@FindBy(css="#loansummary-loanamount > p") WebElement loanAmount;
	@FindBy(css="#loanemisteps > span:nth-child(5) > span") WebElement maxEMIAmount;
	@FindBy(css="#loanintereststeps > span:nth-child(9) > span") WebElement maxInterestRate;
	@FindBy(xpath="//*[@id=\"loantermsteps\"]/span[7]/span") WebElement maxLTInYears;
	@FindBy(css="#loantermsteps > span:nth-child(7) > span") WebElement maxLTInMonths;
	@FindBy(css="#loanfeessteps > span:nth-child(5) > span") WebElement maxFee;
	@FindBy(css="#loanemislider > div") WebElement loanEMISlider;
	@FindBy(css="#loaninterestslider > div") WebElement loanInterestSlider ;
	@FindBy(css="#loantermslider > div") WebElement loanTermSlider;
	@FindBy(css="#loanfeesslider > div") WebElement loanFeesSlider;
	@FindBy(css="#loansummary-loanamount > h4") WebElement principalLoanAmount;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]") WebElement calcBtn;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]/ul/li") List<WebElement> loanList;
	@FindBy(css="#loansummary") WebElement loan;
	@FindBy(xpath="//*[@id=\"loan-amount-calc\"]/a[1]") WebElement loanamount;
	
	public String setValuesLTinYears(String emi,String interstRate,String loanTenureInYears,String fee) {
		fillValue(loanEMI,loanEMIRupeesBtn ,emi);
		fillValue(loanInterest,loanInterestPercentBtn ,interstRate);
		fillValue(loanTerm,loanTermYearBtn ,loanTenureInYears);
		fillValue(loanFee,loanFeeRupeeBtn ,fee);	
		return loanAmount.getText();
	}
	
	
	public String setValuesLTinMonths(String emi,String interstRate,String loanTenureInMonths,String fee) {
		fillValue(loanEMI,loanEMIRupeesBtn ,emi);
		fillValue(loanInterest,loanInterestPercentBtn ,interstRate);
		fillValue(loanTerm,loanTermMonthBtn,loanTenureInMonths);
		fillValue(loanFee,loanFeeRupeeBtn ,fee);
		return loanAmount.getText();
	}
	
	
	public void chooseLoan() {
		calcBtn.click();
		clickRequiredLoan(properties.getProperty("Loancalc"),loanList);
		loanamount.click();
	}
	
	public boolean getTitle() {
		
		return loan.isDisplayed();
	}
	
	public float getLoanEMI() {
		
		return number(maxEMIAmount.getText())*number(loanEMISlider.getAttribute("style"))/100;	
	}

	public float getInterestRate() {
	
		return number(maxInterestRate.getText())*number(loanInterestSlider.getAttribute("style"))/100;	
	}
 
	public float getLTInYears() {
		
		return number(maxLTInYears.getText())*number(loanTermSlider.getAttribute("style"))/100;	
	}

	public float getLTInMonths() {
		return number( maxLTInMonths.getText())*number(loanTermSlider.getAttribute("style"))/100;	
	}
	public float getFee() {
		return number( maxFee.getText())*number(loanFeesSlider.getAttribute("style"))/100;	
	}
	
	public String getEMIValue() {
		return loanAmount.getText();
	}
	
	@DataProvider(name="HomeLoanCalculatorDDT")
	public Object[][] TestDataFeed(){
		   
		try {
			String path=".\\TestData\\LoanAmountCalculatorDDT.xlsx";
			int numOfRows=ExcelUtilities.getRowCount(path,"Sheet1");
			int numOfCols=ExcelUtilities.getCellCount(path,"Sheet1",1);
			data=new String[numOfRows][numOfCols];
			for(int i=0;i<numOfRows;i++) {
				for(int j=0;j<numOfCols;j++) {
					data[i][j]=ExcelUtilities.getCellData(path,"Sheet1", i+1, j);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return data;
		
	}
}
