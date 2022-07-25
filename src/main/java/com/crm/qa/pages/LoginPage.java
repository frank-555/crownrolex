package com.crm.qa.pages;


import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase{

	//PageFactory -OR://Object Repository
	
	@FindBy(name="username")//input[@placeholder='Username']// this is equalent to driver.findElement
	WebElement username;
	
	@FindBy(name="password")//input[@placeholder='Password']
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[normalize-space()='Sign Up']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the page objects
	public  LoginPage() {//constructor.it's pointing to the current class object 
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public String validateLoginPageTitle() {
		//initialize();
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		//initialize();
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd)  {
		//initialize();
		username.sendKeys(un);
		password.sendKeys(pwd);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", loginBtn);
		return new HomePage();//After clicking login button it's redirecting to Homepage
	}
	
}
