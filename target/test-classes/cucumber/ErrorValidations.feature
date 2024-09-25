
@tag
Feature: Error Validation
  I want to use this template for my feature file

 

  @ErrorValidation
  Scenario Outline: login error validations test
    Given I landed on a ecommmerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  												| password   | 
      | seleniumpractice45@gmail.com  | Tester@12  |
