package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

		// Reads -Dheadless=true from Maven/Jenkins/GitHub Actions (defaults to false locally)
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		WebDriver driver;

		if (selectedBrowser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-notifications", "--disable-save-password-bubble");

			if (isHeadless) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
			}
			driver = new ChromeDriver(options);

		} else if (selectedBrowser.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless=new");
				edgeOptions.addArguments("--window-size=1920,1080");
				edgeOptions.addArguments("--no-sandbox");
				edgeOptions.addArguments("--disable-dev-shm-usage");
			}
			driver = new EdgeDriver(edgeOptions);

		} else if (selectedBrowser.equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("-headless");
				firefoxOptions.addArguments("--width=1920");
				firefoxOptions.addArguments("--height=1080");
			}
			driver = new FirefoxDriver(firefoxOptions);

		} else {
			throw new IllegalArgumentException("Invalid browser name: " + selectedBrowser);
		}

		// Save the driver instance into ThreadLocal for this specific thread
		driverThread.set(driver);

		int waitTime = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
		if (!isHeadless) {
			getDriver().manage().window().maximize();
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
		getDriver().get(ConfigReader.getProperty("url"));
	}

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
