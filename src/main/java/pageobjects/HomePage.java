package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class HomePage {

    private final WebDriver driver;
    private final By createOrderButtonLocator = xpath("//button[text()='Оформить заказ']");
    private final By loginButtonLocator = xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButtonLocator = xpath("//p[text()='Личный Кабинет']");
    private final By constructorTextLocator = xpath("//h1[text()='Соберите бургер']");
    private final By activeTabLocator = xpath("//div[contains(@class,'current')]");
    private final By bunTabLocator = xpath("//div[contains(span, 'Булки')]");
    private final By sauceTabLocator = xpath("//span[text()='Соусы']");
    private final By fillingTabLocator = xpath("//span[text()='Начинки']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean createOrderButtonIsEnabled() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator));
        WebElement createOrderButton = driver.findElement(createOrderButtonLocator);
        return createOrderButton.isDisplayed();
    }

    public void clickLoginButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    public void clickPersonalAccountButton() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement personalAccountButton = driver.findElement(personalAccountButtonLocator);
        personalAccountButton.click();
    }

    public void clickBunTab() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement bunTab = driver.findElement(bunTabLocator);
        bunTab.click();
    }

    public void clickSauceTab(){
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement sauceTab = driver.findElement(sauceTabLocator);
        sauceTab.click();
    }

    public void clickFillingTab(){
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement fillingTab = driver.findElement(fillingTabLocator);
        fillingTab.click();
    }

    public String getCurrentTab() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.findElement(activeTabLocator).getText();
    }

    public String constructorText() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.findElement(constructorTextLocator).getText();
    }

}



