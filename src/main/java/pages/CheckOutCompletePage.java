package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CheckOutCompletePage  {
    private WebDriver driver;

    private By completeHeader = By.className("complete-header");
    private By backHomeBtn    = By.id("back-to-products");

    public CheckOutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationMessage() {
        return driver.findElement(completeHeader).getText();
    }

    public void clickBackHome() {
        driver.findElement(backHomeBtn).click();
    }
}
