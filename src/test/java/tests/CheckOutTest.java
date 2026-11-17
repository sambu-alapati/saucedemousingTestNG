package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.CheckOutCompletePage;
import pages.CheckOutStepOnePage;
import pages.CheckOutStepTwoPage;
import pages.HomePage;
import pages.LoginPage;

public class CheckOutTest extends BaseClass {

    private CheckOutStepOnePage stepOnePage;

    @BeforeMethod
    public void proceedToCheckout() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(getDriver());
        homePage.addBackpackToCart();
        homePage.openCart();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickCheckout();

        stepOnePage = new CheckOutStepOnePage(getDriver());
    }

    @Test(priority = 1)
    public void verifyCheckoutFormValidationError() {
        stepOnePage.clickContinue();
        Assert.assertTrue(stepOnePage.getErrorMessage().contains("First Name is required"));
    }

    @Test(priority = 2)
    public void verifyCompleteEndToEndPurchase() {
        stepOnePage.enterShippingDetails("John", "Doe", "560001");
        stepOnePage.clickContinue();

        CheckOutStepTwoPage stepTwoPage = new CheckOutStepTwoPage(getDriver());
        Assert.assertEquals(stepTwoPage.getPageTitle(), "Checkout: Overview");
        Assert.assertEquals(stepTwoPage.getTotalPrice(), "Total: $32.39");
        stepTwoPage.clickFinish();

        CheckOutCompletePage completePage = new CheckOutCompletePage(getDriver());
        Assert.assertEquals(completePage.getConfirmationMessage(), "Thank you for your order!");
    }
}