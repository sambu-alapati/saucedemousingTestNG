package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CheckOutStepTwoPage {
    private WebDriver driver;

    private By pageTitle    = By.className("title");
    private By itemTotal    = By.className("summary_subtotal_label");
    private By taxLabel     = By.className("summary_tax_label");
    private By totalLabel   = By.className("summary_total_label");
    private By finishBtn    = By.id("finish");

    public CheckOutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTax() {
        return driver.findElement(taxLabel).getText();
    }

    public String getTotalPrice() {
        return driver.findElement(totalLabel).getText();
    }

    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }
}
