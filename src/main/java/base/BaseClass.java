package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;

public class BaseClass {

	// ThreadLocal ensures each parallel thread gets its own separate WebDriver instance
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	// Getter method used by all Test classes and TestListener
	public static WebDriver getDriver() {
		return driverThread.get();
	}

	// Method to start the browser
	@Parameters("browser")
	@BeforeMethod
	public void invokeBrowser(@Optional("") String browserName) {
		String selectedBrowser = (browserName != null && !browserName.isEmpty()) ? browserName
				: ConfigReader.getProperty("browser");

		WebDriver driver;

		if (selectedBrowser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-notifications", "--disable-save-password-bubble");
			driver = new ChromeDriver(options);
		} else if (selectedBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (selectedBrowser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + selectedBrowser);
		}

		// Save the driver instance into ThreadLocal for this specific thread
		driverThread.set(driver);

		int waitTime = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
		getDriver().get(ConfigReader.getProperty("url"));
	} // <-- This closing brace was missing!

	// Helper method to safely accept any JavaScript alert if one appears
	public void acceptAlertIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
			wait.until(ExpectedConditions.alertIsPresent());
			getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			// No alert present; continue test execution
		}
	}

	// Method to close the browser
	@AfterMethod
	public void closeBrowser() {
		if (getDriver() != null) {
			getDriver().quit();
			driverThread.remove(); // Prevents memory leaks after the thread finishes
		}
	}
}
