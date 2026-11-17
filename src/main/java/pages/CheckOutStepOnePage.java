package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CheckOutStepOnePage {
    private WebDriver driver;

    private By firstNameInput  = By.id("first-name");
    private By lastNameInput   = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueBtn     = By.id("continue");
    private By cancelBtn       = By.id("cancel");
    private By errorMessage    = By.cssSelector("h3[data-test='error']");

    public CheckOutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterShippingDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}