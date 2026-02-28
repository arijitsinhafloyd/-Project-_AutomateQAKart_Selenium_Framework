package arijitsinha.tests;

import java.util.ArrayList;
import arijitsinha.testComponents.Retry;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import arijitsinha.pageComponents.CartPage;
import arijitsinha.pageComponents.ProductCatalogue;
import arijitsinha.testComponents.BaseTest;
import junit.framework.Assert;
public class ErrorValidationsTests extends BaseTest{
	
	@Test(groups={"Errors"})
	public void loginErrorValidation() {
		ProductCatalogue pc= lp.login("testuser", "@@@Test123");
		String errorToast = lp.getToastMessage();
		Assert.assertEquals("Login failed Please check your credentials and try again", errorToast);
	}
	
	@Test(groups={"Errors"}, retryAnalyzer=Retry.class)
	public void ProductErrorValidations() {
		ProductCatalogue pc= lp.login("testuser", "@Test123");
		String[] productName={"Samsung Galaxy Watch 6","KitchenAid Stand Mixer","Wilson Pro Tennis Racket"};
		List<String> productNamesAsList=new ArrayList<>(Arrays.asList(productName));
		String toast=lp.getToastMessage();
		Assert.assertEquals("Login successful Welcome back!", toast);
		lp.EndOfLoginFlow();
		
		//Adding products to cart
		pc.scrollDowninProductsPage();
		pc.AddingToCart(productNamesAsList);
		pc.disappearingOftoast();

		//Going to cart
		CartPage cp=pc.goToCart();
		productNamesAsList.remove(2);
		productNamesAsList.add("Sony WH-1000XM5");
		boolean status=false;
		List<String> productsInCartAsList=cp.productsInCart();
		if(productsInCartAsList.equals(productNamesAsList))
			status=true;
		Assert.assertFalse("Product Error Validation failed", status);
	}
}
