@tag
Feature: Submit Order test in "AutomateQA" E Commerce page
			I want to use this template as feature file
			
		Background:
		Given I have landed on the ECommerce page
		
@Regression
Scenario Outline: Placing order of products from the site
Given Logged in with <username> and <password>
When I add products <product1>, <product2>, <product3> to cart
And checkout the <product1>, <product2>, <product3>  from cart page
And fill details with <name>, <phone>, <pin>, <locality>, <address>, <city>, <state>
Then my order gets placed and message would come as "Order Placed Successfully!"

Examples:

| username           | password  | product1                  | product2           | product3                  | name   | phone      | pin    | locality   | address     | city       | state    |
| testuser     		 | @Test123  | Samsung Galaxy Watch 6    | Sony WH-1000XM5    | Wilson Pro Tennis Racket  | Arijit | 9876543210 | 380001 | Satellite  | ABC Road    | Ahmedabad  | Gujarat  |


@Regression
Scenario Outline: To check the feature functionality
Given Logged in with <username> and <password>
When filters <sortBy>,<categories>,<priceRangeMin>,<priceRangeMax>,<brand>,<rating> are applied
Then it should be applied successfully

Examples:

| username      | password  | sortBy                | categories     | priceRangeMin   | priceRangeMax  | brand      | rating      |
| testuser      | @Test123  | Price: Low to High    | Electronics    | 70              | 900            | Sony       | 4★ & above  | 