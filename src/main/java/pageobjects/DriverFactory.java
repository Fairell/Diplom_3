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

                    // Указываем путь до исполняемого файла Yandex browser
                    yandexOptions.setBinary("C:\\Users\\Arseniy\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                    yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");

                    // Указываем путь до chromedriver версии 128, совместимой с Yandex Browser
                    System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
                    return new ChromeDriver(yandexOptions);
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }
}
