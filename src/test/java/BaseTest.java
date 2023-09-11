import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageobject.loginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BaseTest {
    protected WebDriver driver;
    protected loginPage loginPage;
    protected Properties prop;
    private boolean isInitialized = false; // Flag to check if setup has been done

    @BeforeClass
    public void setUp() {
        if (!isInitialized) {
            // Initialize ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            // Initialize the driver with ChromeOptions
            driver = new ChromeDriver(options);
            driver.get("https://opencart.abstracta.us/");

            // Initialize pageobject.loginPage and properties
            loginPage = new loginPage(driver);
            prop = new Properties();

            try {
                FileInputStream configFile = new FileInputStream("src/test/java/config.properties");
                prop.load(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            isInitialized = true; // Set the flag to true to prevent setup from running again
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to switch to a new window by its title
    protected void switchToWindowByTitle(String windowTitle) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(windowTitle)) {
                return;
            }
        }
    }

    // Method to switch back to the main window
    protected void switchToMainWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 0) {
            driver.switchTo().window((String) windowHandles.toArray()[0]);
        }
    }
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

/**   import org.openqa.selenium.WebDriver;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected pageobject.loginPage pageobject.loginPage;
    protected Properties prop;
    private boolean isInitialized = false; // Flag to check if setup has been done

    @BeforeClass

    public void setUp() {
        if (!isInitialized) {
            // Initialize ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            // Initialize the driver with ChromeOptions
            driver = new ChromeDriver(options);
            driver.get("https://opencart.abstracta.us/");

            //   pageobject.loginPage = new pageobject.loginPage(driver);
            prop = new Properties();

            try {
                FileInputStream configFile = new FileInputStream("src/test/java/config.properties");
                prop.load(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            isInitialized = true; // Set the flag to true to prevent setup from running again
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
 **/
