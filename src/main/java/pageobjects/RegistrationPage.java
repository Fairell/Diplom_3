package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class RegistrationPage {

    private final WebDriver driver;
    private final By nameInputLocator = xpath("//fieldset[1]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By emailInputLocator = xpath("//fieldset[2]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By passwordInputLocator = xpath("//input[@class = 'text input__textfield text_type_main-default' and @type='password']");
    private final By registrationButtonLocator = xpath("//button[text()='Зарегистрироваться']");
    private final By errorTextLocator = xpath("//p[text() = 'Некорректный пароль']");
    private final By logInButtonLocator = xpath("//a[text() = 'Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUserInfo(String name, String email, String password) {
        WebElement nameInput = driver.findElement(nameInputLocator);
        nameInput.sendKeys(name);
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickRegistrationButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement registrationButton = driver.findElement(registrationButtonLocator);
        registrationButton.click();
    }

    public String errorText() {
        return driver.findElement(errorTextLocator).getText();
    }

    public void clickLogInButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement logInButton = driver.findElement(logInButtonLocator);
        logInButton.click();
    }
}
