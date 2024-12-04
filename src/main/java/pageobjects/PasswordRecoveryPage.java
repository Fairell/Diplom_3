package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class PasswordRecoveryPage {

    private final WebDriver driver;
    private final By logInButtonLocator = xpath("//a[text() = 'Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
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

