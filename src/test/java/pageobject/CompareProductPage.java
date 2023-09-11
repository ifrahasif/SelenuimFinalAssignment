package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CompareProductPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[@data-original-title='Compare this Product']")
    private WebElement compareButton;

    @FindBy(css = ".alert-success")
    private WebElement alertMessage;

    @FindBy(linkText = "iPhone")
    private WebElement productLink;

    @FindBy(linkText = "product comparison")
    private WebElement comparisonLink;

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    public CompareProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCompareButton() {
        compareButton.click();
    }

    public String getAlertMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        return alertMessage.getText();
    }

    public void clickProductLink() {
        productLink.click();
    }

    public void clickComparisonLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout to 20 seconds
        WebElement comparisonLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='http://opencart.abstracta.us:80/index.php?route=product/compare']")));
        comparisonLink.click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getPageHeadingText() {
        return pageHeading.getText();
    }
}
