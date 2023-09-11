import io.qameta.allure.Attachment;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class LoginTest extends BaseTest{


    @Test(description = "Verify that the login credentials are valid",priority = 1)
    public void testValidLogin() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
        String username = prop.getProperty("login.username");
        String password = prop.getProperty("login.password");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

    }
    // Intentionally Failed Testcase //
    @Test(description = "Verify that the login credentials are invalid",priority = 2)
    public void testInValidLogin() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
        String username = prop.getProperty("login.username");
        String password = prop.getProperty("login.password");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

    }

}
