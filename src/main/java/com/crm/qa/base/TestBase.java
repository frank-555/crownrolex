/*Author: Jay Kay*/

package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties properties;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){      
		try {
		properties = new Properties();
		FileInputStream inputstream = new FileInputStream("F:\\JK Academics\\Studio\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		properties.load(inputstream);
	}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
}
	
	
	public static void initialization() {
		 String browserName = properties.getProperty("browser");
	
		 if(browserName.equals("chrome")) {
		 System.setProperty("webdriver.chrome.driver","F:\\chromedriver\\chromedriver.exe"); 
		 driver=new ChromeDriver(); }
		 if(browserName.equals("firefox")) {
		 System.setProperty("webdriver.gecko.driver","F:\\chromedriver\\geckodriver.exe"); 
		 driver=new FirefoxDriver(); }
		 
		e_driver=new EventFiringWebDriver(driver);
		//Now create object of EventListnerHandler to register it with EventFiringWebDriver
		eventListener= new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(properties.getProperty("url"));
	
		
	}
	
	
}