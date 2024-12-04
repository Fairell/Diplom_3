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
    private final By emailInputLocator = xpath("//fieldset[1]//input[@class = 'text input__textfield text_type_main-default' and @type='text']");
    private final By passwordInputLocator = xpath("//input[@class = 'text input__textfield text_type_main-default' and @type='password']");
    private final By loginButtonLocator = xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillLoginInfo(String email, String password) {
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public boolean logInButtonIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
        WebElement logInButton = driver.findElement(loginButtonLocator);
        return logInButton.isDisplayed();
    }
}
