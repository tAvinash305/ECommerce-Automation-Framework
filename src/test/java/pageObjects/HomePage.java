package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccountLink;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement loginLink;
	
	@FindBy(xpath="//h1[text()='Account Logout']")
	WebElement logoutHeader;
	
	public void clickOnMyAccountLink() {
		myAccountLink.click();
	}
	
	public void clickOnRegisterLink() {
		registerLink.click();
	}
	
	public void clickLoginLink() {
		loginLink.click();
	}
	
	public String getLogoutHeader() {
		return logoutHeader.getText();
	}
}
