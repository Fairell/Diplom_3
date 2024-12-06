package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInputLocator = xpath("//fieldset[1]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By passwordInputLocator = xpath("//input[@class = 'text input__textfield text_type_main-default' and @type='password']");
    private final By loginButtonLocator = xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void fillLoginInfo(String email, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public boolean logInButtonIsVisible() {
        WebElement logInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
        return logInButton.isDisplayed();
    }
}
