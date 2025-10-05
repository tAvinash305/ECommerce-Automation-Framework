package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(id="input-confirm")
	WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement termsCheckBox;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement continueBtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement accountCreationMSG;
	
	public void enterFirstName(String fname) {
		firstNameField.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		lastNameField.sendKeys(lname);
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		telephoneField.sendKeys(telephone);
	}
	
	public void enterPassword(String pass) {
		passwordField.sendKeys(pass);
	}
	
	public void enterConfirmPassword(String cpass) {
		confirmPasswordField.sendKeys(cpass);
	}
	
	public void checkPrivacyPolicy() {
		termsCheckBox.click();
	}
	
	public void clickOnContinueBtn() {
		Actions act = new Actions(driver);
		act.moveToElement(continueBtn).click().build().perform();
	}
	
	public String getConfirmationMSG() {
		try {
			return accountCreationMSG.getText();
		} catch (Exception e){
			return e.getMessage();
		}
	}
}
