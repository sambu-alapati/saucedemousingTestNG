# Sauce Demo E2E Test Automation Framework

Automated end-to-end UI test suite for [Sauce Demo](https://saucedemo.com) built with **Java, Selenium WebDriver, TestNG, and Maven** using the **Page Object Model (POM)** design pattern.

## Key Features
- **Page Object Model (POM)** with reusable explicit waits (`BasePage`)
- **Thread-Safe Parallel & Cross-Browser Execution** (`ThreadLocal<WebDriver>`) on Chrome and Edge
- **Data-Driven Testing** via TestNG `@DataProvider`
- **Externalized Configuration** via `config.properties`
- **Automated HTML Reporting & Failure Screenshots** using ExtentReports 5 and `ITestListener`

## How to Run
1. Clone the repository.
2. Run the full suite via Maven:
   ```bash
   mvn clean test
   ```
3. View the generated HTML report in the `/reports/` directory.
