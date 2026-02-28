package arijitsinha.pageComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinha.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
		
	WebDriver driver;
		
	public ProductCatalogue(WebDriver driver) {
		super(driver);//sharing the driver to Abstract class child to parent
		this.driver=driver;//receiving driver from StandAlone Test and then making it as local class variable using 'this'
		PageFactory.initElements(driver, this);//taking driver as argument and using this to initialize elements created by annotations
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	
	@FindBy(css="section[data-testid='featured-products']")
	WebElement sectionElement;
	
	@FindBy(css="div[class='p-6 pt-0 space-y-6'] div button[data-testid='sort-select']")
	WebElement sort;
	
	@FindBy(xpath="(//div[@data-radix-select-viewport]/div[@role='option'])")
	List<WebElement> li;
	
	@FindBy(xpath="//div[@class='space-y-2']//label")
	List<WebElement> categoryCheckboxList;
	
	@FindBy(xpath="//div[@class='space-y-2']//label/parent::div/button")
	List<WebElement> categoryCheckboxButtons;
	
	@FindBy(css="input[placeholder='Min']")
	WebElement inputMin;
	
	@FindBy(css="input[placeholder='Max']")
	WebElement inputMax;
	
	@FindBy(xpath="//div[contains(@class,'max-h-40')]//label")
	List<WebElement> checkBoxComList;
	
	@FindBy(xpath="//div[contains(@class,'max-h-40')]//button")
	List<WebElement> checkBoxButtonsList;
	
	@FindBy(css="button[data-testid='rating-filter']")
	WebElement ratingDropdown;
	
	@FindBy(xpath="//div[@data-testid='products-grid']//div[@class='p-4']/p")
	List<WebElement> productsListAfterFilter;
	
	By productList=By.cssSelector("div[class*='rounded-lg border bg-card text-card-foreground']");
	By toastMessages=By.cssSelector("div[class='grid gap-1'] div[class='text-sm opacity-90']");
	
	public List<WebElement> getProducts(){
		waitForElementToAppear(sectionElement);
		return sectionElement.findElements(productList);
	}
	
	public void scrollDowninProductsPage() {
		scrollDown();
	}
	
	public void AddingToCart(List<String> p) {
		List<WebElement> li=getProducts();
		int count=0;
		for(int i=0;i<li.size();i++) {
			WebElement product=li.get(i).findElement(By.xpath(".//div[@class='p-4']/h3"));
			String productText=product.getText().trim();
				if(p.contains(productText)) {
				WebElement prods=product.findElement(By.xpath("./parent::div//button"));
				clicking(prods);
				count++;
				if(count==p.size())
					break;
				}
		}
	}
	
	public void disappearingOftoast() {
		scrollUp();
		waitForElementToDisAppear(toastMessages);
	}
	
	public boolean allFiltersOfThePage(String sortBy, String categories, String priceRangeMin,
					String priceRangeMax, String brand, String rating) 
	{
		boolean status=false;
		scrollDownforFilterTest();
		waitForElementToAppear(sort);
		clicking(sort);
		waitForElementToAppear(li);
		for(int i=0;i<li.size();i++) {
			String sort=li.get(i).getText().trim();
			if(sort.equalsIgnoreCase(sortBy)) {
				li.get(i).click();
				break;
			}
		}
		
		for(int i=0;i<categoryCheckboxList.size();i++) {
			String categoryCheckboxName=categoryCheckboxList.get(i).getText().trim();
			if(categoryCheckboxName.equalsIgnoreCase(categories)) {
				categoryCheckboxButtons.get(i).click();
				break;
			}
		}
		inputMin.sendKeys(priceRangeMin);
		inputMax.clear();
		inputMax.sendKeys(priceRangeMax);
		
		for(int i=0;i<checkBoxComList.size();i++) {
			String name=checkBoxComList.get(i).getText().trim();
			if(name.equalsIgnoreCase(brand)) {
				checkBoxButtonsList.get(i).click();
				break;
			}
		}
		clicking(ratingDropdown);
		
		for(int i=0;i<li.size();i++) {
			String sort=li.get(i).getText().trim();
			if(sort.equalsIgnoreCase(rating)) {
				li.get(i).click();
				break;
			}
		}
		if(!productsListAfterFilter.isEmpty()) {
			
			for(int i=0;i<productsListAfterFilter.size();i++) {
				String sort=productsListAfterFilter.get(i).getText().trim();
				if(sort.equalsIgnoreCase(brand)) {
					status=true;
				}
			}
		}
		return status;
	}
	
}
