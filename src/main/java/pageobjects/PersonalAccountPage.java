package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class PersonalAccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By profileTextLocator = xpath("//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");
    private final By constructorLocator = xpath("//p[text() = 'Конструктор']");
    private final By logoLocator = xpath("//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logOutButtonLocator = xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String profileText() {
        WebElement profileTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(profileTextLocator));
        return profileTextElement.getText();
    }

    public void clickConstructorButton() {
        WebElement constructorButton = wait.until(ExpectedConditions.elementToBeClickable(constructorLocator));
        constructorButton.click();
    }

    public void clickLogoButton() {
        WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(logoLocator));
        logoButton.click();
    }

    public void clickLogOutButton() {
        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(logOutButtonLocator));
        logOutButton.click();
    }
}
