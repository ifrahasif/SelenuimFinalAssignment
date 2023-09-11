package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;

    @FindBy(css = ".product-layout")
    private List<WebElement> productLayouts;

    public String getPlaceholderText() {
        return searchInput.getAttribute("placeholder");
    }


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchTerm) {
        searchInput.sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void waitForSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(productLayouts));
    }

    public void searchForProduct(String searchTerm) {
        setSearchInput(searchTerm);
        clickSearchButton();
        waitForSearchResults();
    }
    public void verifyPlaceholderText(String expectedPlaceholder) {
        String actualPlaceholder = getPlaceholderText();
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Placeholder text does not match expected text.");
    }

    public boolean isProductPresentInResults(String productName) {
        for (WebElement productLayout : productLayouts) {
            String productLayoutText = productLayout.getText();
            if (productLayoutText.toLowerCase().contains(productName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
