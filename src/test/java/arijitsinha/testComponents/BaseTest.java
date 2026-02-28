package arijitsinha.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import arijitsinha.pageComponents.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver invokeBrowser() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\arijitsinha\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser"); //for maven given browser
		//String browserName=prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			if(browserName.contains("headless")){
				options.addArguments("--headless=new");
			    options.addArguments("--window-size=1920,1080");
			    options.addArguments("--disable-gpu");
			}
			driver=new ChromeDriver(options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
		    options.addPreference("signon.management.page.breach-alerts.enabled", false);
		    options.addPreference("signon.rememberSignons", false);
		    driver=new FirefoxDriver(options);
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
		    Map<String, Object> prefs = new HashMap<>();
		    prefs.put("profile.password_manager_leak_detection", false);
		    options.setExperimentalOption("prefs", prefs);
		    driver=new EdgeDriver(options);
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	//DataProvider from JSON doc
	public List<HashMap<String,String>> getJSONData(String path) throws IOException {
		String jSONContent=FileUtils.readFileToString(new File(path),
				StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jSONContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File myPath=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, myPath);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException{
		driver=invokeBrowser();
		lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		if(driver!=null) {
		driver.quit();
		}
	}
}
