Feature: Verify Loan Amount Calculator
		Scenario: Verify correct loan amount is calculated
		  Given choose Loan Calculator from drop down
			When check loan amount calculator page is displayed
			And set the required values of loan amount and check correct value is calculated "Sheet1"
