package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProduct(String searchText, String expectedUrl) throws InterruptedException {
        while (true) {
            try {
                WebElement element = driver.findElement(By.xpath("//a[contains(text(), '" + searchText + "')]"));

                if (element.isDisplayed()) {
                    element.click();

                    // Wait for the expected URL to load
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.urlToBe(expectedUrl));

                    String currentUrl = driver.getCurrentUrl();
                    Assert.assertEquals(currentUrl, expectedUrl, "URL after clicking does not match expected URL");
                    break;
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(1000);
            }
        }
    }
}
