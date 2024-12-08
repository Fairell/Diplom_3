package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInputLocator = xpath("//fieldset[1]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By emailInputLocator = xpath("//fieldset[2]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By passwordInputLocator = xpath("//input[@class = 'text input__textfield text_type_main-default' and @type='password']");
    private final By registrationButtonLocator = xpath("//button[text()='Зарегистрироваться']");
    private final By errorTextLocator = xpath("//p[text() = 'Некорректный пароль']");
    private final By logInButtonLocator = xpath("//a[text() = 'Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void fillUserInfo(String name, String email, String password) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputLocator));
        nameInput.sendKeys(name);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
        passwordInput.sendKeys(password);
    }

    public void clickRegistrationButton() {
        WebElement registrationButton = wait.until(ExpectedConditions.elementToBeClickable(registrationButtonLocator));
        registrationButton.click();
    }

    public String errorText() {
        WebElement errorTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
        return errorTextElement.getText();
    }

    public void clickLogInButton() {
        WebElement logInButton = wait.until(ExpectedConditions.elementToBeClickable(logInButtonLocator));
        logInButton.click();
    }
}
