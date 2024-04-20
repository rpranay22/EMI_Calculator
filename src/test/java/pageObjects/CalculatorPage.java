package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class CalculatorPage extends BaseClass{
    
	@FindBy(xpath="//*[@id=\"menu-item-dropdown-2696\"]") WebElement calculatorBtn;
	@FindBy(xpath="//*[@id=\"menu-item-2696\"]/ul/li") List<WebElement> calculatorOptions;
	
	
	public CalculatorPage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	public void chooseLoan(String calcType) {
		calculatorBtn.click();
		clickRequiredLoan(calcType, calculatorOptions);
	}
		
}
