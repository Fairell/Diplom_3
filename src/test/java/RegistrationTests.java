import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import org.apache.commons.lang3.RandomStringUtils;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;

public class RegistrationTests {

    private static WebDriver driver;
    private final UserSteps userSteps = new UserSteps();
    private final String testName = "User_" + RandomStringUtils.randomAlphabetic(5);
    private final String testEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@example.com";
    private final String testPassword = RandomStringUtils.randomAlphanumeric(10);
    private final String testIncorrectPassword = RandomStringUtils.randomAlphanumeric(3);

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    public void shouldRegisterSuccessfullyWithValidData() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillUserInfo(testName, testEmail, testPassword);
        registrationPage.clickRegistrationButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.logInButtonIsVisible());
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    public void shouldShowErrorForInvalidPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillUserInfo(testName, testEmail, testIncorrectPassword);
        registrationPage.clickRegistrationButton();
        MatcherAssert.assertThat(registrationPage.errorText(), containsString("Некорректный пароль"));
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
