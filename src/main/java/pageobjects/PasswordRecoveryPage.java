package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class PasswordRecoveryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logInButtonLocator = xpath("//a[text() = 'Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickLogInButton() {
        WebElement logInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonLocator));
        logInButton.click();
    }
}

