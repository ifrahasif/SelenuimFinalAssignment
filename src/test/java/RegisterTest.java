import org.testng.annotations.Test;
import pageobject.RegistrationPage;

public class RegisterTest extends BaseTest {

    @Test
    public void FormFilling() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Fill the registration form
        registrationPage.fillRegistrationForm("hello", "john", "ifrahasif333@gmail.com", "1234567890", "securepassword");

        // Submit the form
        registrationPage.clickContinueButton();

    }
}

