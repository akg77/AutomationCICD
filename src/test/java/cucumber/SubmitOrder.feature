@tag
Feature: Purchase the order from e-commerce site
  I want to use this template for my feature file

  #Executes before each scenario - Just like Before method in TestNG
  Background: 
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test to submit the order
    Given Logged in with the username <name> and password <password>
    When I add product <productName> from cart
    And checkout <productName> and submit the order for <countryName>
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name           | password  | productName     | countryName |
      | anand@abc.com  | Pa55word! | ZARA COAT 3     | India       |
      | anand2@abc.com | Pa55word! | ADIDAS ORIGINAL | India       |
