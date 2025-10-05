package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		logger.info("***** Starting TC002_LoginTest *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccountLink();
			logger.info("***** Clicked My Account Link *****");
			
			hp.clickLoginLink();
			logger.info("***** Clicked Register Link *****");

			LoginPage lp = new LoginPage(driver);
			lp.enterEmailAddress(prop.getProperty("email"));
			lp.enterPassword(prop.getProperty("password"));
			lp.clickLoginButton();

			MyAccountPage ap = new MyAccountPage(driver);
			boolean msg = ap.isMyAccountPageExists();
			Assert.assertTrue(msg);
		} catch (Exception e) {
			logger.error("Test Failed...");
			Assert.fail();
		}
		logger.info("***** Fnished TC002_LoginTest *****");
	}
}
