import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import static org.junit.Assert.assertTrue;
import static pageobjects.Globals.*;

public class LoginTests {

    private static WebDriver driver;
    private final UserSteps userSteps = new UserSteps();
    private final String testName = "User_" + RandomStringUtils.randomAlphabetic(5);
    private final String testEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@example.com";
    private final String testPassword = RandomStringUtils.randomAlphanumeric(10);

    @Before
    public void setup() {
        UserRequest userRequest = new UserRequest(testEmail, testPassword, testName);
        userSteps.createUser(userRequest);
        driver = DriverFactory.getDriver();
    }

    @Test
    @DisplayName("Авторизация по кнопке 'Войти в аккаунт' на главной странице")
    public void shouldLoginSuccessfullyFromLoginButton() {
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Авторизация по кнопке 'Войти' из личного кабинета")
    public void shouldLoginSuccessfullyFromProfileButton() {
        driver.get(BASE_URL);
        HomePage homePage = new HomePage(driver);
        homePage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Авторизация по кнопке 'Войти' с формы регистрации")
    public void shouldLoginSuccessfullyFromRegistrationFormButton() {
        driver.get(REGISTER_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLogInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Авторизация по кнопке 'Войти' с формы восстановления пароля")
    public void shouldLoginSuccessfullyFromPasswordRecoveryFormButton() {
        driver.get(FORGOT_PASSWORD_PAGE);
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
        LoginRequest loginRequest = new LoginRequest(testEmail, testPassword);
        String token = userSteps.loginUser(loginRequest)
                .extract().body().path("accessToken");
        if (token != null) {
            userSteps.deleteUser(token);
        }
        driver.quit();
    }
}
