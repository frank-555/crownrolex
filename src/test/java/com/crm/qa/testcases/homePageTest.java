package com.crm.qa.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class homePageTest extends TestBase{

	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	
	public homePageTest() {//1. when we run this test, initially it will call the super class constructor where the properties in TestBase will be defined
		super();
	}
		//constructor of homePageTest. so that we can call super class constructor by using super keyword. so that base class constructor will be called and properties will be initialized.
	
	
	@BeforeMethod
	public void setup() {
		initialization();//2. Then it will come to before method,and initialization() will be called and it will launch chrome,maximize,delete all cookies,enter the url.
		loginpage = new LoginPage();
		testutil= new TestUtil();
		contactspage = new ContactsPage();
		
		homepage = loginpage.login(properties.getProperty("username"),//3. Then get the login page object and call the login method and login to the application
				 properties.getProperty("password"));
			}
	
	@Test(priority=1,enabled=true)
	public void verifyHomePageTitleTest() {
		String homePageTitle= homepage.verifyHomePageTitle();//4. After login it will go to home page and it will go to Test
		Assert.assertEquals(homePageTitle, "CRMPRO","HomePage Title Is Not Matching");
	}
	
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testutil.switchToFrame();
		Assert.assertTrue(homepage.verifyCorrectUsername());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
		
	}
	
	@AfterMethod//5. After all the Tests are completed, it will call driver.quit
	public void tearDown() {
	driver.quit();
	}
	
	
}
