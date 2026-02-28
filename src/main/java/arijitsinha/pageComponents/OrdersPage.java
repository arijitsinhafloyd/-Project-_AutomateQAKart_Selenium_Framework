package arijitsinha.pageComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinha.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//receiving driver from StandAlone Test and then making it as local class variable using 'this'
		PageFactory.initElements(driver, this);//taking driver as argument and using this to initialize elements created by annotations
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	
	@FindBy (css="div[class='space-y-3'] div div h4")
	private List<WebElement> ordersElementList;
	
	public List<WebElement> waitForProducts(){
		 waitForElementToAppear(ordersElementList); 
		 return ordersElementList;
	}
	public boolean ordersPageProductVerification(String[] productName) {
		waitForProducts();
		List<String> actualOrderList=new ArrayList<String>(productName.length);
		for(int i=0;i<ordersElementList.size();i++) {
			String text=ordersElementList.get(i).getText().trim();
			actualOrderList.add(text);
		}
		List<String> expectedList=Arrays.asList(productName);
		Collections.sort(expectedList);
		Collections.sort(actualOrderList);
		return actualOrderList.equals(expectedList);
	}
	
}
