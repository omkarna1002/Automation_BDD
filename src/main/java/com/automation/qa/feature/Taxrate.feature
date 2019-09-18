Feature: Final Tax Rate Calculation Functionality

  @Functional
  Scenario: Validation of Tax Rate
  
    Given The User is on Homepage of WestPac
    When Investments->Calculators Link is Clicked
    Then Calculator Page is Displayed and Best Return Link is clicked
    Then Savings Account is clicked and Tax Calculation Page is Displayed
    And User Provides Input to Different Fields
    Then Final Tax Rate is Displayed 
   
   



   