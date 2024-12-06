package pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            switch (browser) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                    return WebDriverManager.chromedriver().capabilities(options).create();
                case "yandex":
                    ChromeOptions yandexOptions = new ChromeOptions();

                    // Получаем путь к исполняемому файлу Yandex Browser из переменной среды
                    String yandexBrowserPath = System.getenv("YANDEX_BROWSER_PATH");
                    if (yandexBrowserPath == null || yandexBrowserPath.isEmpty()) {
                        throw new IllegalStateException("Environment variable YANDEX_BROWSER_PATH is not set");
                    }
                    yandexOptions.setBinary(yandexBrowserPath);

                    // Получаем путь к chromedriver из переменной среды
                    String chromeDriverPath = System.getenv("CHROME_DRIVER_PATH");
                    if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                        throw new IllegalStateException("Environment variable CHROME_DRIVER_PATH is not set");
                    }
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                    yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                    return new ChromeDriver(yandexOptions);
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }
}
