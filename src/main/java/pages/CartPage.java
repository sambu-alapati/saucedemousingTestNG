package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class CartPage{
    private WebDriver driver;

    private By pageTitle           = By.className("title");
    private By cartItems           = By.className("cart_item");
    private By itemName            = By.className("inventory_item_name");
    private By removeBackpackBtn   = By.id("remove-sauce-labs-backpack");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By checkoutBtn         = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public String getFirstItemName() {
        return driver.findElement(itemName).getText();
    }

    public void removeBackpack() {
        driver.findElement(removeBackpackBtn).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}