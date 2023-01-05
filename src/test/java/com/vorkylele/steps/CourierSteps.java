package com.vorkylele.steps;

import io.qameta.allure.Step;
import com.vorkylele.api.pojo.CreateCourier;
import com.vorkylele.api.pojo.LoginCourier;
import io.restassured.response.ValidatableResponse;

import static com.vorkylele.config.EndPoints.*;
import static com.vorkylele.specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Создание курьера")
    public static ValidatableResponse createCourier(CreateCourier body) {
        return given()
                .spec(requestSpecification())
                .body(body)
                .when()
                .post(CREATE_COURIER)
                .then()
                .log().ifError();
    }

    @Step("Логин курьера в системе")
    public static ValidatableResponse loginCourier(LoginCourier body) {
        return given()
                .spec(requestSpecification())
                .body(body)
                .when()
                .post(LOGIN_COURIER)
                .then()
                .log().ifError();
    }
}
