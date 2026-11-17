package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utils.TestDataProvider;

public class LoginTest extends BaseClass {
	
	
	 @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
	    public void verifyLoginScenarios(String username, String password, String expectedResult) {
	        LoginPage loginPage = new LoginPage(getDriver());
	        loginPage.login(username, password);

	        if (expectedResult.equals("SUCCESS")) {
	            HomePage homePage = new HomePage(getDriver());
	            Assert.assertEquals(homePage.getPageTitle(), "Products");
	        } else {
	            String actualError = loginPage.getErrorMessage();
	            Assert.assertTrue(actualError.contains(expectedResult),
	                    "Expected error to contain: [" + expectedResult + "] but found: [" + actualError + "]");
	        }
	    }
	
	
	@Test(priority = 1)
	public void testValidLogin() {
	    LoginPage loginPage = new LoginPage(getDriver());
	    loginPage.login("standard_user", "secret_sauce");

	    // Now on Home Page, so use HomePage to get the title
	    HomePage homePage = new HomePage(getDriver());
	    Assert.assertEquals(homePage.getPageTitle(), "Products");
	}

	    @Test(priority = 2)
	    public void testLockedOutUserLogin() {
	        LoginPage loginPage = new LoginPage(getDriver());
	        loginPage.login("locked_out_user", "secret_sauce");

	        String actualError = loginPage.getErrorMessage();
	        Assert.assertTrue(actualError.contains("Sorry, this user has been locked out."));
	    }

}
