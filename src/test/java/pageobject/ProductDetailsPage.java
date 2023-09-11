package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToCart() {
        driver.get("http://opencart.abstracta.us/index.php?route=product/product&product_id=43");

        WebElement addToCartButton = driver.findElement(By.id("button-cart"));

        if (addToCartButton.isDisplayed() && addToCartButton.isEnabled()) {
            addToCartButton.click();
            System.out.println("Clicked on the 'Add to Cart' button.");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

            String expectedMessage = "Success: You have added MacBook to your shopping cart!";
            String actualMessage = successMessage.getText();
            Assert.assertTrue(actualMessage.contains(expectedMessage), "Success message text does not match expected message.");
            System.out.println("Success message is displayed and contains the expected text.");

            WebElement shoppingCartLink = successMessage.findElement(By.partialLinkText("shopping cart"));
            shoppingCartLink.click();

            String expectedCartPageURL = "http://opencart.abstracta.us/index.php?route=checkout/cart";
            String actualCartPageURL = driver.getCurrentUrl();
            Assert.assertEquals(actualCartPageURL, expectedCartPageURL, "Shopping cart page URL does not match expected URL.");
            System.out.println("Shopping cart page URL is as expected.");

            WebElement cartForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[contains(@action, 'route=checkout/cart/edit')]")));
            Assert.assertTrue(cartForm.isDisplayed(), "Shopping cart form is not visible.");
            System.out.println("Shopping cart form is visible.");

            expectedCartPageURL = "http://opencart.abstracta.us/index.php?route=checkout/cart";
            actualCartPageURL = driver.getCurrentUrl();
            Assert.assertEquals(actualCartPageURL, expectedCartPageURL, "Shopping cart page URL does not match expected URL.");
            System.out.println("Shopping cart page URL is as expected.");
        } else {
            System.out.println("The 'Add to Cart' button is not visible or enabled.");
        }
    }
}
