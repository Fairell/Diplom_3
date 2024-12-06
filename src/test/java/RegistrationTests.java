import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import org.apache.commons.lang3.RandomStringUtils;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static pageobjects.Globals.REGISTER_PAGE;

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
        driver.get(REGISTER_PAGE);
    }

    @Test
    public void shouldRegisterSuccessfullyWithValidData() {
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
        LoginRequest loginRequest = new LoginRequest(testEmail, testPassword);
        String token = userSteps.loginUser(loginRequest)
                .extract().body().path("accessToken");
        if (token != null) {
            userSteps.deleteUser(token);
            driver.quit();
        }
    }
}
