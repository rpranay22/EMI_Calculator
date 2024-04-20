package pageObjects;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Utilities.ExcelUtilities;
import testBase.BaseClass;

public class HomeLoanCalculator extends BaseClass {
	
	String[][] data; 
	public HomeLoanCalculator(WebDriver driver) throws IOException {
		super(driver);
		
	}
	
	
	@FindBy(css="#homeprice") WebElement homeValue;
	@FindBy(css="#homeloanemicalculatorformInner > div:nth-child(1) > div.homeloandetails.col-lg-6.col-xl-12 > div:nth-child(2) > div.col-xl-3.form-group.lhomeprice > div > div > span") WebElement homeValRupeeBtn;
	@FindBy(css="#downpayment") WebElement downPayment;
	@FindBy(css="#homeloanemicalculatorformInner > div:nth-child(1) > div.homeloandetails.col-lg-6.col-xl-12 > div:nth-child(2) > div.col-xl-3.form-group.ldownpayment > div > div > div > div > label.btn.btn-secondary.active") WebElement downPayPerBtn;	
	@FindBy(css="#homeloaninterest") WebElement interestRate;
	@FindBy(css="#homeloanemicalculatorformInner > div:nth-child(1) > div.homeloandetails.col-lg-6.col-xl-12 > div:nth-child(3) > div.col-xl-3.form-group.lint > div > div > span") WebElement interestRatePerBtn;
	@FindBy(css="#homeloanterm") WebElement loanTenure;
	@FindBy(css="#homeloanemicalculatorformInner > div:nth-child(1) > div.homeloandetails.col-lg-6.col-xl-12 > div:nth-child(3) > div.col-xl-3.form-group.lterm > div > div > div > div > label.btn.btn-secondary.active") WebElement loanTenureInYears;
	@FindBy(css="#loanfees") WebElement loanFee;
	@FindBy(css="#homeloanemicalculatorformInner > div:nth-child(1) > div.homeloandetails.col-lg-6.col-xl-12 > div:nth-child(3) > div.col-xl-3.form-group.lfees > div > div > div > div > label.btn.btn-secondary.active") WebElement loanFeeInPer;
	@FindBy(xpath="//*[@id=\"paymentschedule\"]/table/tbody/tr") List<WebElement> rowsInTable;
	@FindBy(xpath="//*[@id=\"paymentschedule\"]/table/tbody/tr[2]/td") List<WebElement> colInTable;
	@FindBy(xpath="//*[@id=\"paymentschedule\"]/table") WebElement table;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]") WebElement calcBtn;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]/ul/li") List<WebElement> loanList;
	@FindBy(xpath="/html/body/header") WebElement header;
	
	
	
	
	
	public void setValues() {
		fillValue(homeValue,homeValRupeeBtn,properties.getProperty("HomeValue"));
		fillValue(downPayment,downPayPerBtn,properties.getProperty("DownPayment"));
		fillValue(interestRate,interestRatePerBtn,properties.getProperty("InterestRate"));
		fillValue(loanTenure,loanTenureInYears,properties.getProperty("LoanTenure"));
		fillValue(loanFee,loanFeeInPer,properties.getProperty("LoanFee"));
	}
	

	public void setDataInExcel() throws IOException {
		String path=".\\TestData\\HomeLoanEMI.xlsx";		
		int numRows=rowsInTable.size();		
		int numCols=colInTable.size();		
		int startRow=1;
		scrollPage(table);
		for(int i=2;i<numRows;i++) {
			for(int j=1;j<=numCols;j++) {
			WebElement dataInTable=driver.findElement(By.xpath("//*[@id=\"paymentschedule\"]/table/tbody/tr["+i+"]/td["+j+"]"));
			String value=dataInTable.getText();	             
			ExcelUtilities.setCellData(path, "Sheet1", startRow,j-1, value);
			}	
			startRow=startRow+1;
			i=i+1;			
		}
		scrollPage(header);
	}
	
	public void chooseHomeLoan()  {
		calcBtn.click();
		clickRequiredLoan(properties.getProperty("LoanType"),loanList);	
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
//	@DataProvider(name="HomeLoanCalculatorDDT")
//	public String[][] TestDataFeed(){
//		   
//		try {
//			String path=".\\TestData\\LoanAmountCalculatorDDT.xlsx";
//			int numOfRows=ExcelUtilities.getRowCount(path,"Sheet1");
//			int numOfCols=ExcelUtilities.getCellCount(path,"Sheet1",1);
//			data=new String[numOfRows][numOfCols];
//			for(int i=0;i<numOfRows;i++) {
//				for(int j=0;j<numOfCols;j++) {
//					data[i][j]=ExcelUtilities.getCellData(path,"Sheet1", i+1, j);
//				}
//			}
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();	
//		}
//		return data;
//		
//	}
	
	
	
	
	

}
