	package pageObjects;


import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;

public class HomePage extends BaseClass{
	
	
	// Finding Elements by PageFactory
    @FindBy(xpath="//*[@id=\"emicalculatordashboard\"]/ul/li") List<WebElement> loanElements;
    @FindBy(css="#loanamount") WebElement loanAmount;
    @FindBy(css="#emicalculatorinnerform > div.row.form-group.lamount > div > div > div > span") WebElement loanAmountRupeesBtn;
    @FindBy(css="#loaninterest") WebElement loanInterest;
    @FindBy(css="#emicalculatorinnerform > div.sep.row.form-group.lint > div > div > div > span") WebElement loanInterestPercentBtn;
    @FindBy(css="#loanterm") WebElement loanTerm;
    @FindBy(css="#emicalculatorinnerform > div.sep.row.form-group.lterm > div > div > div > div > div > label.btn.btn-secondary.active") WebElement loanTermYearBtn;
    @FindBy(xpath=" //*[@id=\"year2024\"]") WebElement yearBtn;
    @FindBy(xpath="//*[@id=\"emipaymenttable\"]/table/tbody/tr[3]//tr/td[1]") WebElement monthName;
    @FindBy(xpath="//*[@id=\"emipaymenttable\"]/table/tbody/tr[3]//tr/td[2]") WebElement principalAmount;
    @FindBy(xpath="//*[@id=\"emipaymenttable\"]/table/tbody/tr[3]//tr/td[3]") WebElement interestAmount;
    @FindBy(css="#emicalculatorinnerform > div.row.form-group.lamount > label") WebElement carLoan;
    
 
    
	// Constructor
	public HomePage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	// To click required loan option
	public void clickRequiredloan()  {	
		
		clickRequiredLoan(properties.getProperty("LaunchPage"), loanElements);	
	}
	
	public void setValues() {
		fillValue(loanAmount,loanAmountRupeesBtn,properties.getProperty("CarLoanAmount"));
		fillValue(loanInterest,loanInterestPercentBtn,properties.getProperty("CarInterestRate"));
		fillValue(loanTerm,loanTermYearBtn,properties.getProperty("LonTenure"));
	}
	
	public void printEMI() {
		yearBtn.click();
		System.out.println(monthName.getText());
		System.out.println(principalAmount.getText()); 
		System.out.println(interestAmount.getText());
	}	
	
	public String getCarLoanPage() {
		return carLoan.getText();
	}
}
