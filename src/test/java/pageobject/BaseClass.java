package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    protected static WebDriver driver;
    private boolean isInitialized = false; // Flag to check if setup has been done
    @BeforeClass

    public void setUp() {

        if (!isInitialized) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            // Initialize the driver with ChromeOptions
            driver = new ChromeDriver(options);

        }
        isInitialized = true; // Set the flag to true to prevent setup from running again
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
