import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import static org.hamcrest.CoreMatchers.containsString;

public class GeneralNavigationTests {

    private static WebDriver driver;
    private final UserSteps userSteps = new UserSteps();
    private final String testName = "User_" + RandomStringUtils.randomAlphabetic(5);
    private final String testEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@example.com";
    private final String testPassword = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
        userSteps.createUser(testEmail, testPassword, testName);
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        homePage.clickPersonalAccountButton();
    }

    @Test
    public void shouldNavigateToConstructorFromPersonalAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickConstructorButton();
        HomePage homePage = new HomePage(driver);
        MatcherAssert.assertThat(homePage.constructorText(), containsString("Соберите бургер"));

    }

    @Test
    public void shouldNavigateToConstructorUsingLogoButton() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogoButton();
        HomePage homePage = new HomePage(driver);
        MatcherAssert.assertThat(homePage.constructorText(), containsString("Соберите бургер"));

    }

    @After
    public void tearDown() {
        String token = userSteps.loginUser(testEmail, testPassword)
                .extract().body().path("accessToken");
        if (token != null) {
            userSteps.deleteUser(token);
            driver.quit();
        }
    }

}
