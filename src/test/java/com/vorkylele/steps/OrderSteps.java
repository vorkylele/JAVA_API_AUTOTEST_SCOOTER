package com.vorkylele.steps;

import com.vorkylele.specifications.Specifications;
import io.qameta.allure.Step;
import com.vorkylele.api.pojo.CreateOrder;
import io.restassured.response.ValidatableResponse;

import static com.vorkylele.config.EndPoints.ORDERS;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа")
    public static ValidatableResponse createOrder(CreateOrder body) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(body)
                .when()
                .post(ORDERS)
                .then()
                .log().ifError();
    }

    @Step("Получение списка заказов")
    public static ValidatableResponse getOrderList() {
        return given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(ORDERS)
                .then()
                .log().ifError();
    }
}
