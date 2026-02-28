package arijitsinha.stepdefinition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import arijitsinha.pageComponents.CartPage;
import arijitsinha.pageComponents.LandingPage;
import arijitsinha.pageComponents.LastPageOfOrder;
import arijitsinha.pageComponents.PreOrderPage;
import arijitsinha.pageComponents.ProductCatalogue;
import arijitsinha.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class StepDefinitionimplementation extends BaseTest{

	LandingPage lp;
	ProductCatalogue pc;
	CartPage cp;
	PreOrderPage pop;
	LastPageOfOrder lpo;
	
	@Given("I have landed on the ECommerce page")
	public void I_have_landed_on_the_ECommerce_page() throws IOException {
		lp=launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		pc=lp.login(username,password);
		
	}
	
	@When("^I add products (.+), (.+), (.+) to cart$")
	public void When_I_add_products_to_cart(String product1, String product2, String product3) {
		lp.EndOfLoginFlow();
		String[] productName= {product1,product2,product3};
		List<String> productNamesAsList= Arrays.asList(productName);
		pc.scrollDowninProductsPage();
		pc.AddingToCart(productNamesAsList);
		pc.disappearingOftoast();
	}
	
	@And("^checkout the (.+), (.+), (.+) from cart page$")
	public void checking_out_products_from_cart_page(String product1, String product2, String product3) {
		String[] productName= {product1,product2,product3};
		List<String> productNamesAsList= Arrays.asList(productName);
		cp=pc.goToCart(); 
		List<String> productsInCartAsList=cp.productsInCart();
		if(productsInCartAsList.equals(productNamesAsList))
			Assert.assertTrue(true);
		 pop=cp.checkOut();
	}
	
	@And("^fill details with (.+), (.+), (.+), (.+), (.+), (.+), (.+)$")
	public void fill_details(String name, String phone, String pin, String locality, String address, String city, String state){
		pop.details(name, phone, pin, locality, address, city, state);
		lpo=pop.paymentOptionAndPlacingOrder();
		pop.alertHandling();
	}
	
	@Then("my order gets placed and message would come as {string}")
	public void my_order_gets_placed_message(String string) {
		String orderMessage=lpo.orderMessage();
		string.trim();
		Assert.assertEquals(string, orderMessage);
		driver.close();
	}
	
	@Then ("an error message is shown as {string}")
	public void errorMessge(String string) {
		String errorToast = lp.getToastMessage();
		Assert.assertEquals(string, errorToast);
		driver.close();
		}
	
	@When ("^filters (.+),(.+),(.+),(.+),(.+),(.+) are applied$")
	public void filters(String sortBy, String categories, String priceRangeMin,
			String priceRangeMax, String brand, String rating) {
		lp.EndOfLoginFlow();
		boolean status=pc.allFiltersOfThePage(sortBy,categories,priceRangeMin,priceRangeMax,brand,rating);
		Assert.assertTrue("Some errors in filter, might there is no product in cart or some issue", status);
	}
	
	@Then ("it should be applied successfully")
	public void last() {
		//already covered in @When
		driver.close();
	}
}
