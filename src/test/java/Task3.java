import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        // Dynamic XPath
        WebElement firstNameInput = driver.findElement(By.xpath("//input[contains(@id, 'input-firstname')]"));
        firstNameInput.sendKeys("Ifrah");

        // XPath using Position
        WebElement lastNameInput = driver.findElement(By.xpath("(//input[contains(@id, 'input-lastname')])[1]"));
        lastNameInput.sendKeys("Asif");

        // XPath using Index - Using Contains
        WebElement emailInput = driver.findElement(By.xpath("//input[contains(@id, 'input-email')]"));
        emailInput.sendKeys("ifrahasif178@example.com");

        // Use of Text ()
        WebElement telephoneInput = driver.findElement(By.xpath("//input[@placeholder='Telephone']"));
        telephoneInput.sendKeys("1234567890");

        // XPath using AND and OR Operators
        WebElement passwordInput = driver.findElement(By.xpath("//input[contains(@id, 'input-password') and @type='password']"));
        passwordInput.sendKeys("securepassword");

        WebElement ConfirmPasswordInput = driver.findElement(By.xpath("//input[contains(@id, 'input-confirm') and @type='password']"));
        ConfirmPasswordInput.sendKeys("securepassword");

        WebElement noOptionCheckbox = driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='0' and @checked='checked']"));
        noOptionCheckbox.click(); // This will uncheck the "No" option

        WebElement Agree = driver.findElement(By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']"));
        Agree.click(); // This will check agree option

        // Submit the form
        WebElement continueButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']"));
        continueButton.click();

        driver.quit();
    }
}
