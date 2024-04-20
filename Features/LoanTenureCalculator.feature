Feature: Verify Loan Amount Calculator
		Scenario: Verify correct loan tenure is calculated
		  Given choose Loan Calculator from drop down
			When check loan tenure calculator page is displayed
			And set the required values of loan tenure and check correct value is calculated "Sheet1"
