@tag
Feature: Error Validations test in "AutomateQA" E Commerce page
			I want to use this template as feature file
			
@ErrorValidations
Scenario Outline: Negative cases with wrong credentials to show error validation
Given I have landed on the ECommerce page
When Logged in with <username> and <password>
Then an error message is shown as "Login failed Please check your credentials and try again"

Examples:

|username    |password      |
|testuser345 |tyurytyu@234  |