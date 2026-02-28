package arijitsinha.pageComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import arijitsinha.AbstractComponents.AbstractComponents;

public class PreOrderPage extends AbstractComponents {
	WebDriver driver;
	
	public PreOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); 
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	
	@FindBy(id="name")
	WebElement nameElement;
	
	@FindBy(id="phone")
	WebElement phoneNumber;
	
	@FindBy(id="pincode")
	WebElement pinCode;
	
	@FindBy(id="locality")
	WebElement locality;
	
	@FindBy(id="address")
	WebElement address;
	
	@FindBy(id="city")
	WebElement city;

	@FindBy(id="state")
	WebElement state;
	
	@FindBy(id="card")
	WebElement creditCard;
	
	@FindBy(xpath="//button[@data-testid='place-order-button']")
	WebElement placeOrderButton;
	
	public void details(String name, String phone, String pincode, String locality, String address, String city, String state) {
		nameElement.clear();
		nameElement.sendKeys(name);
		phoneNumber.sendKeys(phone);
		this.pinCode.sendKeys(pincode);
		this.locality.sendKeys(locality);
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		this.state.sendKeys(state);
	}
	
	public LastPageOfOrder paymentOptionAndPlacingOrder() {
		creditCard.click();
		placeOrderButton.click();
		return new LastPageOfOrder(driver);
	}
	
	public void alertHandling() {
		driver.switchTo().alert().accept();
		waitForAlert();
		driver.switchTo().alert().accept();
	}

}
