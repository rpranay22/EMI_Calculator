package pageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import Utilities.ExcelUtilities;
import testBase.BaseClass;

public class EMICalculator extends BaseClass{
     Object[][] data;
	public EMICalculator(WebDriver driver) throws IOException {
		super(driver);
	}
	
	public EMICalculator() {
		super();
	}
	@FindBy(css = "#loanamount")WebElement loanAmount;
	@FindBy(css = "#lamountwrapper > div.row.form-group.lamount > div > div > div > span")WebElement loanAmountRupeesBtn;
	@FindBy(css = "#loaninterest")WebElement loanInterest;
	@FindBy(css = " #lintwrapper > div.sep.row.form-group.lint > div > div > div > span")WebElement loanInterestPercentBtn;
	@FindBy(css = "#loanterm")WebElement loanTerm;
	@FindBy(xpath="//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[1]") WebElement loanTermYearBtn;
	@FindBy(css="#ltermwrapper > div.sep.row.form-group.lterm > div > div > div > div > div > label:nth-child(2)") WebElement loanTermMonthBtn;
	@FindBy(css="#loanfees") WebElement loanFee;
	@FindBy(css="#lfeeswrapper > div.sep.row.form-group.lfees > div > div > div > span") WebElement loanFeeRupeeBtn;
	@FindBy(css="#loanamountsteps > span:nth-child(9) > span") WebElement maxLoanAmount;
	@FindBy(css="#loanintereststeps > span:nth-child(9) > span") WebElement maxInterestRate;
	@FindBy(xpath="//*[@id=\"loantermsteps\"]/span[7]/span") WebElement maxLTInYears;
	@FindBy(css="#loantermsteps > span:nth-child(7) > span") WebElement maxLTInMonths;
	@FindBy(css="#loanfeessteps > span:nth-child(5) > span") WebElement maxFee;
	@FindBy(css="#loanamountslider > div") WebElement loanAmountSlider;
	@FindBy(css="#loaninterestslider > div") WebElement loanInterestSlider ;
	@FindBy(css="#loantermslider > div") WebElement loanTermSlider;
	@FindBy(css="#loanfeesslider > div") WebElement loanFeesSlider;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]/ul/li") List<WebElement> loanList;
	@FindBy(xpath="//*[@id=\"loansummary-emi\"]/h4") WebElement loanEMI;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]") WebElement calcBtn;
	@FindBy(xpath="//*[@id=\"loansummary-emi\"]") WebElement emiValue;
	
	
	public String setValuesLTinYears(String loanamount,String interstRate,String loanTenureInYears,String fee)  {
		fillValue(loanAmount,loanAmountRupeesBtn ,loanamount);
		fillValue(loanInterest,loanInterestPercentBtn ,interstRate);
		loanTermYearBtn.click();
		fillValue(loanTerm,loanTermYearBtn ,loanTenureInYears);
		fillValue(loanFee,loanFeeRupeeBtn ,fee);
	    
		return emiValue.getText().substring(9);
	}

	public String setValuesLTinMonths(String loanamount, String interstRate,
		String loanTenureInMonths, String fee)  {
		fillValue(loanAmount, loanAmountRupeesBtn, loanamount);
		fillValue(loanInterest, loanInterestPercentBtn, interstRate);
		loanTermMonthBtn.click();
		fillValue(loanTerm, loanTermMonthBtn, loanTenureInMonths);
		fillValue(loanFee, loanFeeRupeeBtn, fee);
		
		return emiValue.getText().substring(9);
	}
   
	@DataProvider(name="HomeLoanCalculatorDDT")
	public Object[][] TestDataFeed(){
		   
		try {
			String path=".\\TestData\\EMICalculatorDDT.xlsx";
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
	public void chooseLoanCalculator() {
		calcBtn.click();
		clickRequiredLoan(properties.getProperty("Loancalc"),loanList);	
		
	}
	
	public String getLoanName() {
		return loanEMI.getText();
	}
	public float getLoanAmount() {
		
		
		return number(maxLoanAmount.getText())*number(loanAmountSlider.getAttribute("style"))/100;	
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
		return emiValue.getText();
	}

	
	
	
	
	
	
	
	
			
}
