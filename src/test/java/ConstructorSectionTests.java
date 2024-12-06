import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.DriverFactory;
import pageobjects.HomePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static pageobjects.Globals.BASE_URL;

public class ConstructorSectionTests {

    private static WebDriver driver;

    @Test
    public void shouldNavigateToBunsSection() {
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickSauceTab();
        homePage.clickBunTab();
        String element = homePage.getCurrentTab();
        assertNotNull(element);
        assertEquals("Булки", element);
    }

    @Test
    public void shouldNavigateToSaucesSection() {
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickSauceTab();
        String element = homePage.getCurrentTab();
        assertNotNull(element);
        assertEquals("Соусы", element);
    }

    @Test
    public void shouldNavigateToFillingsSection() {
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickFillingTab();
        String element = homePage.getCurrentTab();
        assertNotNull(element);
        assertEquals("Начинки", element);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
