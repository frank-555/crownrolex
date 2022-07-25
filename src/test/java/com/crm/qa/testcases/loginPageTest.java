package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class loginPageTest extends TestBase{// used to write testcases
		
	LoginPage loginpage;//through out program we can use this variable. hence it's declared at class level
	HomePage homepage;
	
	public loginPageTest() {
		super();//calling testBaseclass-constructor.//constructor of loginPageTest. so that we can call super class constructor by using super keyword. so that base class constructor will be called and properties will be initialized.
	}
	@BeforeMethod
	public void setup() {
	initialization();//calling from parent class
	 loginpage = new LoginPage();
	}
	
	@Test(priority=1,enabled=false)
	public void loginPageTitleTest() {
		String Title=loginpage.validateLoginPageTitle();
		Assert.assertEquals(Title,"CRMPRO - CRM software for customer relationship management, sales, and support." ,"Title is not Matching");
	}
	
	@Test(priority=2,enabled=false)
	public void crmLogoImageTest() {
		boolean flag=loginpage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	
	 @Test(priority=3) 
	 public void loginTest() {
	 homepage=loginpage.login(properties.getProperty("username"),
	 properties.getProperty("password")); 
	 }
	 
	
	 @AfterMethod
	 public void tearDown() {
	 driver.quit();
	 }
	
}
