import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import static org.junit.Assert.assertTrue;

public class LoginTests {

    private static WebDriver driver;
    private final UserSteps userSteps = new UserSteps();
    private final String testName = "User_" + RandomStringUtils.randomAlphabetic(5);
    private final String testEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@example.com";
    private final String testPassword = RandomStringUtils.randomAlphanumeric(10);

    @Before
    public void setup() {
        userSteps.createUser(testEmail, testPassword, testName);
        driver = DriverFactory.getDriver();
    }

    @Test
    public void shouldLoginSuccessfullyFromLoginButton() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    public void shouldLoginSuccessfullyFromProfileButton() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    public void shouldLoginSuccessfullyFromRegistrationFormButton() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLogInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    public void shouldLoginSuccessfullyFromPasswordRecoveryFormButton() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickLogInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createOrderButtonIsEnabled());
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
