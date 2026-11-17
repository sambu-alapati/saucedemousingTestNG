package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;

public class ProductDetailsTest extends BaseClass {

    private HomePage homePage;
    private ProductDetailsPage detailsPage;

    @BeforeMethod
    public void navigateToProductDetails() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        homePage = new HomePage(getDriver());
        homePage.openBackpackDetails();
        detailsPage = new ProductDetailsPage(getDriver());
    }

    @Test(priority = 1)
    public void verifyProductDetailsInformation() {
        Assert.assertEquals(detailsPage.getProductName(), "Sauce Labs Backpack");
        Assert.assertEquals(detailsPage.getProductPrice(), "$29.99");
    }

    @Test(priority = 2)
    public void verifyAddToCartAndBackToProducts() {
        detailsPage.clickAddToCart();
        Assert.assertEquals(homePage.getCartItemCount(), "1");

        detailsPage.clickBackToProducts();
        Assert.assertEquals(homePage.getPageTitle(), "Products");
    }
}