package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven")
	public void verify_loginDDT(String email, String passwd, String expRes) {
		logger.info("***** Starting TC003_loginTestDDT *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnMyAccountLink();
			logger.info("***** Clicked My Account Link *****");

			hp.clickLoginLink();
			logger.info("***** Clicked Register Link *****");

			LoginPage lp = new LoginPage(driver);
			lp.enterEmailAddress(email);
			lp.enterPassword(passwd);
			lp.clickLoginButton();

			MyAccountPage ap = new MyAccountPage(driver);
			boolean targetPage = ap.isMyAccountPageExists();

			/*
			 * expRes = valid-> login success-> Test Pass 
			 * 					login fail-> Test Fail
			 * 
			 * expRes = Invalid-> login success-> Test Fail 
			 * 					  login fail-> Test Pass
			*/

			if (expRes.equalsIgnoreCase("valid")) {
				if (targetPage == true) {
					ap.clickLogoutBtn();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (expRes.equalsIgnoreCase("invalid")) {
				if (targetPage == true) {
					ap.clickLogoutBtn(); 
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			logger.error("Test Failed...");
		}

		logger.info("***** Starting TC003_loginTestDDT *****");
	}
}
