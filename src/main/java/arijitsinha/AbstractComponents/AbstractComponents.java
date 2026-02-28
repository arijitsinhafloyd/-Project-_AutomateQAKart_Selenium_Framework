package arijitsinha.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import arijitsinha.pageComponents.CartPage;
import arijitsinha.pageComponents.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}

	@FindBy(css="a[data-testid='cart-link'] button")
	WebElement cartButton;
	
	@FindBy(css="button[data-testid='user-menu']")
	WebElement testMenu;
	
	@FindBy(css="div a[data-testid='orders-link']")
	WebElement orderButton;
	
	@FindBy(css="div[class='grid gap-1']")
	WebElement OrderToast;
	
	public void waitForElementToAppear(WebElement findElement) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findElement));
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(List<WebElement> findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	}
	
	public void waitForElementToDisAppear(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisAppear(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void scrollDown() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
	}
	
	public void scrollUp() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0)");
	}
	
	public void clicking(WebElement fb) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",fb);
	}
	public void scrollDownforFilterTest() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2200)");
	}
	
	public CartPage goToCart() {
		waitForElementToAppear(cartButton);
		cartButton.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrders() {
		waitForElementToAppear(testMenu);
		testMenu.click();
		orderButton.click();
		return new OrdersPage(driver);
	}
	
	public void waitForAlert() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
}
