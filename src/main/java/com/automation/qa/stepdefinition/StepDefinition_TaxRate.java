package com.automation.qa.stepdefinition;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.automation.qa.base.TestBase;
import com.automation.qa.pages.Calculator_Savings_Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition_TaxRate extends TestBase {	
	
	static Calculator_Savings_Account  cs;
	
	public StepDefinition_TaxRate() throws IOException {
		super();		
	}

	@Given("The User is on Homepage of WestPac")
	public void the_User_is_on_Homepage_of_WestPac() throws IOException {
		initialize_and_move_to_homepage();
		cs= tocalculatorpage();
	}

	@When("Investments->Calculators Link is Clicked")
	public void investments_Calculators_Link_is_Clicked() throws IOException {
		cs.calculatorlink();
	}

	@Then("Calculator Page is Displayed and Best Return Link is clicked")
	public void calculator_Page_is_Displayed_and_Best_Return_Link_is_clicked() {
	  cs.bestreturnlink();
	}

	@Then("Savings Account is clicked and Tax Calculation Page is Displayed")
	public void savings_Account_is_clicked_and_Tax_Calculation_Page_is_Displayed() {
	   cs.savingaccountlink();
	}

	@Then("User Provides Input to Different Fields")
	public void user_Provides_Input_to_Different_Fields() {
	   cs.taxcalculationdata(1);
	   cs.verify_messageicon();
	}

	@Then("Final Tax Rate is Displayed")
	public void final_Tax_Rate_is_Displayed() {
	  cs.taxrate();
	  cs.endReport();
	}
}
