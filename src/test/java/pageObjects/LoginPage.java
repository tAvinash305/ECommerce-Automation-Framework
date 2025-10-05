package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	public void enterEmailAddress(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterPassword(String passwd) {
		passwordField.sendKeys(passwd);
	}
	
	public void clickLoginButton() {
		loginBtn.click();
	}
}
