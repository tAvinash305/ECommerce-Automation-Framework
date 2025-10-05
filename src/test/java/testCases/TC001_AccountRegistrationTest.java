package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("***** Starting TC001_AccountRegistrationTest *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccountLink();
			logger.info("***** Clicked My Account Link *****");

			hp.clickOnRegisterLink();
			logger.info("***** Clicked Register Link *****");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.enterFirstName(randomString());
			regpage.enterLastName(randomString());
			regpage.enterEmail(randomString() + "@gmail.com");
			regpage.enterTelephone(randomNumber());
			String passwd = randomPassword();
			regpage.enterPassword(passwd);
			regpage.enterConfirmPassword(passwd);
			logger.info("***** Entered User Info to Register *****");

			regpage.checkPrivacyPolicy();
			logger.info("***** Clicked Privacy Policy Chekbox *****");

			regpage.clickOnContinueBtn();
			logger.info("***** Clicked Submit/Continue Button *****");

			String msg = regpage.getConfirmationMSG();
			Assert.assertEquals(msg, "Your Account Has Been Created!");
			logger.info("***** Verified Account Creation Message *****");
		} catch (Exception e) {
			logger.error("Test Failed...");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
}
