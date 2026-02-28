package arijitsinha.pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinha.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//receiving driver from StandAlone Test and then making it as local class variable using 'this'
		PageFactory.initElements(driver, this);//taking driver as argument and using this to initialize elements created by annotations
		//driver provides the browser context, and this tells PageFactory which page object’s elements need to be initialized.
	}
	
	@FindBy(xpath="//button[@data-testid='login-button']")
	WebElement loginButton;
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(css="button[data-testid='login-submit']")
	WebElement loginSubmit;
	
	@FindBy(css="div[class='grid gap-1']")
	WebElement LoginToast;
	
	public ProductCatalogue login(String userName,String passWord) {
		loginButton.click();
		username.sendKeys(userName);
		password.sendKeys(passWord);
		loginSubmit.click();
		return new ProductCatalogue(driver);
		
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(LoginToast);
		String errorToast=LoginToast.getText().replace("\n", " ").trim();
		return errorToast;
	}
	
	public void goTo() {
		driver.get("https://automatedemokart.vercel.app/");
	}
	
	public String getToastMessage() {
		waitForElementToAppear(LoginToast);
		String toast=LoginToast.getText().replace("\n"," ").trim();
		return toast;
	}
	
	public void EndOfLoginFlow() {
		waitForElementToDisAppear(LoginToast);
	}
	
}
