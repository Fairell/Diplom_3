package pageobjects;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static pageobjects.Globals.*;

public class UserSteps {

    @Step
    public ValidatableResponse createUser(UserRequest userRequest) {
        return given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(userRequest)  // Передаем объект вместо строки
                .when()
                .post(REGISTER_CLIENT)
                .then();
    }

    @Step
    public ValidatableResponse loginUser(LoginRequest loginRequest) {
        return given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(loginRequest)  // Передаем объект вместо строки
                .when()
                .post(AUTHORIZATION_CLIENT)
                .then();
    }

    @Step
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .log().ifValidationFails()
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete(ACTIONS_CLIENT)
                .then();
    }
}
