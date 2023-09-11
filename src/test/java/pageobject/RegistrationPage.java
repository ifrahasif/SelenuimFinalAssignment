package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Telephone']")
    private WebElement telephoneInput;

    @FindBy(css = "input#input-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[contains(@id, 'input-confirm') and @type='password']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@type='radio' and @name='newsletter' and @value='0' and @checked='checked']")
    private WebElement noOptionCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and @name='agree' and @value='1']")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//input[@type='submit' and @value='Continue']")
    private WebElement continueButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String telephone, String password) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        noOptionCheckbox.click(); // Uncheck the "No" option
        agreeCheckbox.click(); // Check the agree option
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
