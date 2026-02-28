package arijitsinha.pageComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinha.AbstractComponents.AbstractComponents;

public class LastPageOfOrder extends AbstractComponents {
	
	WebDriver driver;

	public LastPageOfOrder(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); 
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	
	@FindBy(tagName="h1")
	WebElement orderMessageElement;
	
	public String orderMessage() {
		String orderMessage=orderMessageElement.getText().trim();
		return orderMessage;
	}
	
}
