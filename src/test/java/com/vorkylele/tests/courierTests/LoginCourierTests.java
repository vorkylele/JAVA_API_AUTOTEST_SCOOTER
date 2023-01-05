package com.vorkylele.tests.courierTests;

import com.vorkylele.api.generatingOfClasses.GeneratingCourier;
import com.vorkylele.utils.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import com.vorkylele.api.pojo.LoginCourier;
import com.vorkylele.steps.CourierSteps;

import static com.vorkylele.helpers.Assertions.*;
import static com.vorkylele.helpers.MessageOfResponse.*;
import static com.vorkylele.helpers.StatusCodes.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Логин  курьера")
@Epic("Логин  курьера")
public class LoginCourierTests extends BaseCourierTest {

    @Feature("Авторизация курьера")
    @Test
    @DisplayName("Авторизация курьера")
    @Description("Ожидаемый код ответа: 200")
    public void loginCourierWithAllValidParams() {
        request = GeneratingCourier.getNewCourier();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));

        loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), request.getPassword())));

        loginResponse.and()
                .statusCode(OK_STATUS)
                .assertThat().body(ASSERT_ID, notNullValue());
    }

    @Feature("Авторизация курьера под несуществующим пользователем")
    @Test
    @DisplayName("Авторизация курьера под несуществующим пользователем")
    @Description("Ожидаемый код ответа: 404")
    public void loginNonExistentCourier() {
        loginResponse = CourierSteps.loginCourier(new LoginCourier(
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.and()
                .statusCode(NOT_FOUND_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_LOGIN_ERROR_404));
    }

    @Feature("Авторизация курьера если неправильно указать логин")
    @Test
    @DisplayName("Авторизация курьера если неправильно указать логин")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithoutLogin() {
        request = GeneratingCourier.getNewCourier();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));

        loginResponse = CourierSteps.loginCourier((new LoginCourier(null, request.getPassword())));

        loginResponse.and()
                .statusCode(BAD_REQUEST_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_LOGIN_ERROR_400));
    }

    @Feature("Авторизация курьера если неправильно указать пароль")
    @Test
    @DisplayName("Авторизация курьера если неправильно указать пароль")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithoutPassword() {
        request = GeneratingCourier.getNewCourier();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));

        loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), null)));

        loginResponse.and()
                .statusCode(BAD_REQUEST_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_LOGIN_ERROR_400));
    }
}
