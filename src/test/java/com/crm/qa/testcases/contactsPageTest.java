package com.crm.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class contactsPageTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage ContactsPage;
	
	String SheetName="contacts";
	
	public contactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		testutil= new TestUtil();
		ContactsPage = new ContactsPage();
		homepage = new HomePage();
		
		homepage = loginpage.login(properties.getProperty("username"),//3. Then get the login page object and call the login method and login to the application
				 properties.getProperty("password"));
		testutil.switchToFrame();
		ContactsPage=homepage.clickOnContactsLink();//will return contactpage object
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		
			}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(ContactsPage.verifyContactsLabel(),"contacts label is missing from the page");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		ContactsPage.selectContactsByName("Test 1");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		ContactsPage.selectContactsByName("Test 1");
		ContactsPage.selectContactsByName("Test 2");
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]=testutil.getTestData(SheetName);
		return data;
	}
	
	@Test(priority=4,dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstname, String lastname, String company) {
		homepage.clickOnNewContactLink();
		//ContactsPage.createNewContact("Mr.", "Tom", "Davessy", "Google");
		ContactsPage.createNewContact(title, firstname, lastname, company);
	}
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
}
