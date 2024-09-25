#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from 	Ecommerce Website
  I want to use this template for my feature file
 Background: 
 Given I landed on a ecommmerce page
  
  @tag2
  Scenario Outline: positive test for submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout product <productName> and submit the order
    Then I verify the "Thankyou for the order." message is displayed on the confirmation.

    Examples: 
      | name  												| password   | productName |
      | seleniumpractice45@gmail.com  | Tester@123 | ZARA COAT 3 |
      
