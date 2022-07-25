package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase{

//PageFactory: Object Repository
@FindBy(xpath="//tr/td[contains(text(), 'User: john honai')]")
@CacheLookup//create a memory and will store element into that memory. instaed of going to browser it'll get element from cache memory which will speed up execution
WebElement usernameLabel;

@FindBy(xpath="//a[@title='Contacts']")
@CacheLookup
WebElement contactsLink;

@FindBy(xpath="//a[contains(text(),'Deals')]")
WebElement dealsLink;

@FindBy(xpath="//a[contains(text(),'Tasks')]")
WebElement taskLink;

@FindBy(xpath="//a[@title='New Contact']")
WebElement newContactLink;


public HomePage() {//Constructor of Homepage
	PageFactory.initElements(driver, this);
}

public String verifyHomePageTitle() {
	return driver.getTitle();
}

public ContactsPage clickOnContactsLink() {
	contactsLink.click();
	return new ContactsPage();//returns of next landing page which is the contacts page
}

public DealsPage clickOnDealsLink() {
	dealsLink.click();
	return new DealsPage();////returns of next landing page which is the deals page
}

public TaskPage clickOnTaskLink() {
	taskLink.click();
	return new TaskPage();
}

public boolean verifyCorrectUsername() {
	return usernameLabel.isDisplayed();
}

public void clickOnNewContactLink() {
	Actions action = new Actions(driver);
	action.moveToElement(contactsLink).perform();
	JavascriptExecutor executor=(JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", newContactLink);
	//newContactLink.click();
	
}

}
