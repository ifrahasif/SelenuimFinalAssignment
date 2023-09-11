
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.SearchPage;


public class SearchProductTest extends BaseTest {

    @Test(description = "Verify that the SearchBar is visible",priority = 1)
    public void searchBarVisibility() {
        SearchPage searchPage = new SearchPage(driver);

        // Check if the search input element is displayed
        Assert.assertTrue(searchPage.getSearchInput().isDisplayed(), "Search bar is not visible on the page");
    }

    @Test(description = "Verify the placeholder text in the SearchBar",priority = 2)
    public void verifyPlaceholderText() {
        SearchPage searchPage = new SearchPage(driver);

        // Specify the expected placeholder text
        String expectedPlaceholder = "Search";

        // Verify the placeholder text in the SearchBar
        searchPage.verifyPlaceholderText(expectedPlaceholder);
    }

    @Test(description = "Verify that Searched Product matches the search results", priority = 3)
    public void searchProduct() {
        SearchPage searchPage = new SearchPage(driver);

        // Search for 'MAC' in the search bar
        searchPage.searchForProduct("MAC");

        // Verify that at least one product layout contains 'Mac'
        Assert.assertTrue(searchPage.isProductPresentInResults("Mac"), "Product layout does not contain 'Mac'");
    }
}

