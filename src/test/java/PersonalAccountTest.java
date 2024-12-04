import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.*;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;

public class PersonalAccountTest {

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
    }

    @Test
    public void toPersonalAccountTransferTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "В этом разделе вы можете изменить свои персональные данные"));
        MatcherAssert.assertThat(personalAccountPage.profileText(), containsString("В этом разделе вы можете изменить свои персональные данные"));

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
