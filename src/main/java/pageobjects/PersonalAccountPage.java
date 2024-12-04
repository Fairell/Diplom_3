package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class PersonalAccountPage {

    private final WebDriver driver;
    private final By profileTextLocator = xpath("//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");
    private final By constructorLocator = xpath("//p[text() = 'Конструктор']");
    private final By logoLocator = xpath("//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logOutButtonLocator = xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String profileText() {
        return driver.findElement(profileTextLocator).getText();
    }

    public void clickConstructorButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement constructorButton = driver.findElement(constructorLocator);
        constructorButton.click();
    }

    public void clickLogoButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement logoButton = driver.findElement(logoLocator);
        logoButton.click();
    }

    public void clickLogOutButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement personalAccountButton = driver.findElement(logOutButtonLocator);
        personalAccountButton.click();
    }
}
