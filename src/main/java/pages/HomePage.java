package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class HomePage {
    private WebDriver driver;

    // Locators
    private By pageTitle          = By.className("title");
    private By productItems       = By.className("inventory_item");
    private By addBackpackBtn     = By.id("add-to-cart-sauce-labs-backpack");
    private By removeBackpackBtn  = By.id("remove-sauce-labs-backpack");
    private By cartBadge          = By.className("shopping_cart_badge");
    private By sortDropdown       = By.className("product_sort_container");
    private By firstItemPrice     = By.cssSelector(".inventory_item:first-child .inventory_item_price");
    private By menuButton         = By.id("react-burger-menu-btn");
    private By logoutLink         = By.id("logout_sidebar_link");
    
    
    
    private By cartIcon     = By.className("shopping_cart_link");
    private By backpackLink = By.id("item_4_title_link");
    
    
    

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public int getProductCount() {
        List<WebElement> items = driver.findElements(productItems);
        return items.size();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpackBtn).click();
    }

    public void removeBackpackFromCart() {
        driver.findElement(removeBackpackBtn).click();
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void sortProductsByVisibleText(String optionText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    public String getFirstProductPrice() {
        return driver.findElement(firstItemPrice).getText();
    }

    public void logout() {
        driver.findElement(menuButton).click();
        driver.findElement(logoutLink).click();
    }
    
    
    
    
    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void openBackpackDetails() {
        driver.findElement(backpackLink).click();
    }
}