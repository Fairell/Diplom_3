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
    private final WebDriverWait wait;

    // Локаторы
    private final By createOrderButtonLocator = xpath("//button[text()='Оформить заказ']");
    private final By loginButtonLocator = xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButtonLocator = xpath("//p[text()='Личный Кабинет']");
    private final By constructorTextLocator = xpath("//h1[text()='Соберите бургер']");
    private final By activeTabLocator = xpath("//div[contains(@class,'current')]");
    private final By bunTabLocator = xpath("//div[contains(span, 'Булки')]");
    private final By sauceTabLocator = xpath("//span[text()='Соусы']");
    private final By fillingTabLocator = xpath("//span[text()='Начинки']");

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Устанавливаем стандартное время ожидания
    }

    public boolean createOrderButtonIsEnabled() {
        WebElement createOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator));
        return createOrderButton.isDisplayed();
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public void clickPersonalAccountButton() {
        WebElement personalAccountButton = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButtonLocator));
        personalAccountButton.click();
    }

    public void clickBunTab() {
        WebElement bunTab = wait.until(ExpectedConditions.elementToBeClickable(bunTabLocator));
        bunTab.click();
    }

    public void clickSauceTab() {
        WebElement sauceTab = wait.until(ExpectedConditions.elementToBeClickable(sauceTabLocator));
        sauceTab.click();
    }

    public void clickFillingTab() {
        WebElement fillingTab = wait.until(ExpectedConditions.elementToBeClickable(fillingTabLocator));
        fillingTab.click();
    }

    public String getCurrentTab() {
        WebElement activeTab = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTabLocator));
        return activeTab.getText();
    }

    public String constructorText() {
        WebElement constructorText = wait.until(ExpectedConditions.visibilityOfElementLocated(constructorTextLocator));
        return constructorText.getText();
    }
}
