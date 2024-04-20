package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;

import Utilities.ExcelUtilities;
import testBase.BaseClass;

public class LoanTenureCalculator extends BaseClass {
	
	Object[][] data;
	public LoanTenureCalculator(WebDriver driver) throws IOException {
		super(driver);
		
	}
	public LoanTenureCalculator(){
		
	}
	
	@FindBy(css = "#loanamount")WebElement loanAmount;
	@FindBy(css = "#lamountwrapper > div.row.form-group.lamount > div > div > div > span")WebElement loanAmountRupeesBtn;
	@FindBy(css ="#loanemi")WebElement loanEMI;
	@FindBy(css ="#lemiwrapper > div.sep.row.form-group.lemi > div > div > div > span")WebElement loanEMIRupeesBtn;
	@FindBy(css = "#loaninterest")WebElement loanInterest;
	@FindBy(css = "#lintwrapper > div.sep.row.form-group.lint > div > div > div > span")WebElement loanInterestPercentBtn;
	@FindBy(css="#loanfees") WebElement loanFee;
	@FindBy(css="#lfeeswrapper > div.sep.row.form-group.lfees > div > div > div > span") WebElement loanFeeRupeeBtn;
    @FindBy(css="#loansummary-tenure > p") WebElement loanTenure;
	@FindBy(css="#loanamountsteps > span:nth-child(9) > span") WebElement maxLoanAmount;
	@FindBy(css="#loanemisteps > span:nth-child(5) > span") WebElement maxEMIAmont;
	@FindBy(css="#loanintereststeps > span:nth-child(9) > span") WebElement maxInterestRate;
	@FindBy(css="#loanfeessteps > span:nth-child(5) > span") WebElement maxFee;
	@FindBy(css="#loanamountslider > div") WebElement loanAmountSlider;
	@FindBy(css="#loanemislider > div") WebElement loanEMISlider;
	@FindBy(css="#loaninterestslider > div") WebElement loanInterestSlider ;
	@FindBy(css="#loanfeesslider > div") WebElement loanFeesSlider;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]") WebElement calcBtn;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]/ul/li") List<WebElement> loanList;
	@FindBy(xpath="//*[@id=\"loan-tenure-calc\"]/a[1]") WebElement loanamount;
	@FindBy(xpath="//*[@id=\"loansummary-tenure\"]/h4") WebElement loan;
    
    public String setValuesLTInYears(String loanamount,String emi,String interstRate,String fee)  {
		fillValue(loanAmount,loanAmountRupeesBtn ,loanamount);
		fillValue( loanEMI,loanEMIRupeesBtn,emi);
		fillValue(loanInterest,loanInterestPercentBtn ,interstRate);
		fillValue(loanFee,loanFeeRupeeBtn ,fee);
		return loanTenure.getText();
	}
    
    public void chooseLoan() {
		calcBtn.click();
		clickRequiredLoan(properties.getProperty("Loancalc"),loanList);
		loanamount.click();
	}
    	
	public boolean getTitle() {
		
		return loan.isDisplayed();
	}
	public float getLoanAmount() {
			
		return number(maxLoanAmount.getText())*number(loanAmountSlider.getAttribute("style"))/100;	
	}

	public float getLoanEMI() {
		
		return number(maxEMIAmont.getText())*number(loanEMISlider.getAttribute("style"))/100;	
	}

	public float getInterestRate() {
	
		return number(maxInterestRate.getText())*number(loanInterestSlider.getAttribute("style"))/100;	
	}
	
	public float getFee() {
		
		return number(maxFee.getText())*number(loanFeesSlider.getAttribute("style"))/100;	
	}
	
	public String getEMIValue() {
		return loanTenure.getText();
	}   
	
	@DataProvider(name="TenureCalculatorDDT")
	public Object[][] TestDataFeed(){
		   
		try {
			String path=".\\TestData\\LoanTenureCalculatorDDT.xlsx";
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

