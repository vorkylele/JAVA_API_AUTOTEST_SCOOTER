package com.vorkylele.tests.courierTests;

import com.vorkylele.api.generatingOfClasses.GeneratingCourier;
import com.vorkylele.utils.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import com.vorkylele.steps.CourierSteps;

import static com.vorkylele.helpers.Assertions.*;
import static com.vorkylele.helpers.MessageOfResponse.*;
import static com.vorkylele.helpers.StatusCodes.*;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Создание  курьера")
@Epic("Создание  курьера")
public class CreateCourierTests extends BaseCourierTest {

    @Feature("Создание нового курьера с валидными данными")
    @Test
    @DisplayName("Создание нового курьера с валидными данными")
    @Description("Ожидаемый код ответа: 201")
    public void createCourierWithAllValidParams() {
        request = GeneratingCourier.getNewCourier();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));
    }

    @Feature("Создание нового курьера с валидными данными, без поля 'firstName")
    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'firstName")
    @Description("Ожидаемый код ответа: 201")
    public void createCourierWithoutFirstName() {
        request = GeneratingCourier.getNewCourierWithFirstNameNull();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));
    }

    @Feature("Создание нового курьера с валидными данными, без поля 'login")
    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'login")
    @Description("Ожидаемый код ответа: 400")
    public void createCourierWithoutLogin() {
        request = GeneratingCourier.getNewCourierWithLoginNull();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(BAD_REQUEST_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_REGISTER_ERROR_400));
    }

    @Feature("Создание нового курьера с валидными данными, без поля 'password")
    @Test
    @DisplayName("Создание нового курьера с валидными данными, без поля 'password")
    @Description("Ожидаемый код ответа: 400")
    public void createCourierWithoutPassword() {
        request = GeneratingCourier.getNewCourierWithPasswordNull();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(BAD_REQUEST_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_REGISTER_ERROR_400));
    }

    @Feature("Создание двух одинаковых курьеров")
    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Ожидаемый код ответа: 409")
    public void createTwoIdenticalCourier() {
        request = GeneratingCourier.getNewCourierWithFirstNameNull();
        createCourierResponse = CourierSteps.createCourier(request);

        createCourierResponse.and()
                .statusCode(CREATED_STATUS)
                .assertThat().body(ASSERT_OK, equalTo(true));

        errorResponse = CourierSteps.createCourier(request);
        errorResponse.and()
                .statusCode(CONFLICT_STATUS)
                .assertThat().body(ASSERT_MESSAGE, equalTo(MESSAGE_REGISTER_ERROR_409));
    }
}
