package pageobjects;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static pageobjects.Globals.*;

public class UserSteps {

    @Step
    public ValidatableResponse createUser(String email, String password, String name) {
        return given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body("{\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"password\": \"" + password + "\",\n" +
                        "    \"name\": \"" + name + "\"\n" +
                        "}")
                .when()
                .post(REGISTER_CLIENT)
                .then();
    }

    @Step
    public ValidatableResponse loginUser(String email, String password) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body("{\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post(AUTHORIZATION_CLIENT).then();
    }

    @Step
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete(ACTIONS_CLIENT)
                .then();
    }
}
