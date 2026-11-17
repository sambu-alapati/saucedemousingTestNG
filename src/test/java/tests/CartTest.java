package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class CartTest extends BaseClass {

    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod
    public void addItemAndOpenCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        homePage = new HomePage(getDriver());
        homePage.addBackpackToCart();
        homePage.openCart();

        cartPage = new CartPage(getDriver());
    }

    @Test(priority = 1)
    public void verifyAddedItemInCart() {
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack");
    }

    @Test(priority = 2)
    public void verifyRemoveItemFromCart() {
        cartPage.removeBackpack();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @Test(priority = 3)
    public void verifyContinueShoppingButton() {
        cartPage.clickContinueShopping();
        Assert.assertEquals(homePage.getPageTitle(), "Products");
    }
}