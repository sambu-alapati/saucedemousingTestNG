package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class ProductDetailsPage  {
    private WebDriver driver;

    private By productName      = By.className("inventory_details_name");
    private By productPrice     = By.className("inventory_details_price");
    private By addToCartBtn     = By.id("add-to-cart");
    private By removeBtn        = By.id("remove");
    private By backToProducts   = By.id("back-to-products");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public void clickRemove() {
        driver.findElement(removeBtn).click();
    }

    public void clickBackToProducts() {
        driver.findElement(backToProducts).click();
    }
}