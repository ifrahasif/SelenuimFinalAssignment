import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.ProductDetailsPage;
import pageobject.ProductPage;
import pageobject.WishListPage;
import pageobject.CompareProductPage;


import java.time.Duration;

public class AddProduct extends BaseTest {
    @Test(description = "Select a Product", priority = 1)
    public void selectProductTest() throws InterruptedException {
        String searchText = "MacBook";
        String expectedUrl = "http://opencart.abstracta.us/index.php?route=product/product&product_id=43";

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct(searchText, expectedUrl);
    }

    @Test(description = "Add a Product to the Cart", priority = 2)
    public void addProductToCartTest() {
    ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
    productDetailsPage.addProductToCart();
}
    @Test(description = "Add a Product to the Wishlist", priority = 3)
    public void addProductToWishlist() {
        driver.get("http://opencart.abstracta.us/index.php?route=product/product&product_id=40");

        WishListPage wishListPage = new WishListPage(driver);

        // Click the "Add to Wish List" button using the WishListPage class
        wishListPage.clickAddToWishlistButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

        String expectedMessage = "You must login or create an account to save iPhone to your wish list!";
        String actualMessage = alertMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Alert message text does not match expected message.");

        WebElement wishListLink = alertMessage.findElement(By.linkText("wish list"));
        Assert.assertNotNull(wishListLink, "Wish List link is not found in the alert message.");

        // Click the wish list link using the WishListPage class
        wishListPage.clickWishListLink();

        // Wait for the URL to change to the login page URL
        WebDriverWait loginWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginWait.until(ExpectedConditions.urlToBe("https://opencart.abstracta.us/index.php?route=account/login"));

        // Assert that the current URL matches the expected login page URL
        String expectedLoginPageURL = "https://opencart.abstracta.us/index.php?route=account/login";
        String actualLoginPageURL = wishListPage.getCurrentURL();
        Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL, "Login page URL does not match expected URL.");
    }


    @Test(description = "Add a Product to the Comparison List", priority = 4)
    public void addProductToComparison() {
        CompareProductPage compareProductPage = new CompareProductPage(driver);
        driver.get("http://opencart.abstracta.us/index.php?route=product/product&product_id=40");

        // Click the "Compare this Product" button using the POM class
        compareProductPage.clickCompareButton();

        // Get the alert message text using the POM class
        String expectedMessage = "Success: You have added iPhone to your product comparison!";
        String actualMessage = compareProductPage.getAlertMessageText();

        Assert.assertTrue(actualMessage.contains(expectedMessage), "Alert message text does not match expected message.");

        // Click the "iPhone" product link using the POM class
        //compareProductPage.clickProductLink();

        // Click the "product comparison" link using the POM class
        compareProductPage.clickComparisonLink();

        // Wait for the URL to change to the product comparison page URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://opencart.abstracta.us/index.php?route=product/compare"));

        // Assert that the current URL matches the expected product comparison page URL
        String expectedURL = "http://opencart.abstracta.us/index.php?route=product/compare";
        String actualURL = compareProductPage.getCurrentURL();
        Assert.assertEquals(actualURL, expectedURL, "URL after clicking does not match expected URL.");

        // Get the page heading text using the POM class
        String expectedHeading = "Your Store";
        String actualHeading = compareProductPage.getPageHeadingText();
        Assert.assertEquals(actualHeading, expectedHeading, "Page heading does not match expected heading.");
    }

}
