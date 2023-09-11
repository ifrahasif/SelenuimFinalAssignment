package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'wish list')]")
    private WebElement wishListLink;

    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    private WebElement addToWishlistButton;

    public WishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickWishListLink() {
        wishListLink.click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void clickAddToWishlistButton() {
        addToWishlistButton.click();
    }
}
