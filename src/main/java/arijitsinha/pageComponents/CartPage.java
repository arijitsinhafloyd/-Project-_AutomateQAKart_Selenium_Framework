package arijitsinha.pageComponents;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinha.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); 
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	 @FindBy(css="div[class='flex justify-between items-start'] div h3 a")
	 List<WebElement> listOfCart;
	 
	 @FindBy(css="button[data-testid='proceed-to-checkout']")
	 WebElement orderButton;
	 
	 public List<String> productsInCart(){
		 List<String> productsInCartAsList=new ArrayList<String>();
			for(int i=0;i<listOfCart.size();i++) {
				String cartProduct=listOfCart.get(i).getText().trim();
				productsInCartAsList.add(cartProduct);
			}
		return productsInCartAsList;
	 }
	 
	 public PreOrderPage checkOut() {
		 orderButton.click();
		 return new PreOrderPage(driver);
	 }

}
