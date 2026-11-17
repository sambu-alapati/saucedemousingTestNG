package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;



public class HomePageTest extends BaseClass {

    private HomePage homePage;

    // Runs right after BaseClass.invokeBrowser() before each @Test in this class
    @BeforeMethod
    public void loginToHomePage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");
        homePage = new HomePage(getDriver());
    }

    @Test(priority = 1)
    public void verifyHomePageTitleAndProductsCount() {
        Assert.assertEquals(homePage.getPageTitle(), "Products");
        Assert.assertEquals(homePage.getProductCount(), 6);
    }

    @Test(priority = 2)
    public void verifyAddAndRemoveItemFromCart() {
        homePage.addBackpackToCart();
        Assert.assertEquals(homePage.getCartItemCount(), "1");

        homePage.removeBackpackFromCart();
        Assert.assertFalse(homePage.isCartBadgeDisplayed(), "Cart badge should disappear when cart is empty");
    }

    @Test(priority = 3)
    public void verifySortProductsLowToHigh() {
        homePage.sortProductsByVisibleText("Price (low to high)");
        Assert.assertEquals(homePage.getFirstProductPrice(), "$7.99");
    }

    @Test(priority = 4)
    public void verifyLogoutFromHomePage() {
        homePage.logout();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/");
    }
}