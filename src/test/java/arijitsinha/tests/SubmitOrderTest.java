package arijitsinha.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import arijitsinha.AbstractComponents.AbstractComponents;
import arijitsinha.pageComponents.CartPage;
import arijitsinha.pageComponents.LandingPage;
import arijitsinha.pageComponents.LastPageOfOrder;
import arijitsinha.pageComponents.OrdersPage;
import arijitsinha.pageComponents.PreOrderPage;
import arijitsinha.pageComponents.ProductCatalogue;
import arijitsinha.testComponents.BaseTest;
import arijitsinha.testComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider="getData", retryAnalyzer=Retry.class)
	public void SubmitOrder(HashMap<String,String> hm) throws IOException 
	{
		String[] productName={hm.get("product1"),hm.get("product2"),hm.get("product3")};
		List<String> productNamesAsList= Arrays.asList(productName);
		//Login flow
		ProductCatalogue pc=lp.login(hm.get("username"),hm.get("password"));
		String toast=lp.getToastMessage();
		Assert.assertEquals("Login successful Welcome back!", toast);
		lp.EndOfLoginFlow();
		
		//Adding products to cart
		pc.scrollDowninProductsPage();
		pc.AddingToCart(productNamesAsList);
		pc.disappearingOftoast();
		
		//Going to cart
		CartPage cp=pc.goToCart(); 
		List<String> productsInCartAsList=cp.productsInCart();
		if(productsInCartAsList.equals(productNamesAsList))
			Assert.assertTrue(true);
		PreOrderPage pop=cp.checkOut(); //Checkout
		
		//Pre Order page
		pop.details(hm.get("name"),hm.get("phone"),hm.get("pin"),hm.get("locality"),hm.get("address"),hm.get("city"),hm.get("state"));
		LastPageOfOrder lpo=pop.paymentOptionAndPlacingOrder();
		pop.alertHandling();
		
		//Order Placed Last page
		String orderMessage=lpo.orderMessage();
		Assert.assertTrue("Not showing the placed Order Message", orderMessage.contains("Successfully"));
		
		//Orders Page
		OrdersPage op=cp.goToOrders();
		boolean status=op.ordersPageProductVerification(productName);
		Assert.assertTrue("Product Not Matching", status);
	}
	
	@Test(dataProvider="getData1", retryAnalyzer=Retry.class)
	public void Filters(HashMap<String,String> h1) {
		ProductCatalogue pc=lp.login(h1.get("username"),h1.get("password"));
		lp.EndOfLoginFlow();
		//div[role='option'] span span
		boolean status=pc.allFiltersOfThePage(h1.get("sortBy"),h1.get("categories"),h1.get("priceRangeMin"),h1.get("priceRangeMax"),
				h1.get("brand"),h1.get("rating"));
		Assert.assertTrue("Some errors in filter, might there is no product in cart or some issue", status);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data=getJSONData(System.getProperty("user.dir")+"\\src\\test\\java\\arijitsinha\\data\\JSONData.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	@DataProvider
	public Object[][] getData1() throws IOException{
		List<HashMap<String,String>> data=getJSONData(System.getProperty("user.dir")+"\\src\\test\\java\\arijitsinha\\data\\JSONFilters.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
