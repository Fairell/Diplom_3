import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import static org.junit.Assert.assertTrue;
import static pageobjects.Globals.BASE_URL;

public class LogoutTests {

    private static WebDriver driver;
    private final UserSteps userSteps = new UserSteps();
    private final String testName = "User_" + RandomStringUtils.randomAlphabetic(5);
    private final String testEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@example.com";
    private final String testPassword = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
        UserRequest userRequest = new UserRequest(testEmail, testPassword, testName);
        userSteps.createUser(userRequest);
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginInfo(testEmail, testPassword);
        loginPage.clickLoginButton();
        homePage.clickPersonalAccountButton();
    }

    @Test
    public void shouldLogoutSuccessfullyFromPersonalAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogOutButton();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.logInButtonIsVisible());

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


