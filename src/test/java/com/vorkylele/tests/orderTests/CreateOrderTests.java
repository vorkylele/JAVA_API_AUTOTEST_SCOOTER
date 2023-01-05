package com.vorkylele.tests.orderTests;

import com.vorkylele.api.generatingOfClasses.GeneratingOrder;
import com.vorkylele.utils.BaseOrderTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.vorkylele.steps.OrderSteps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.vorkylele.helpers.StatusCodes.CREATED_STATUS;
import static org.hamcrest.core.IsNull.notNullValue;

@AllArgsConstructor
@RunWith(Parameterized.class)
@DisplayName("Создание заказа")
@Epic("Создание заказа")
public class CreateOrderTests extends BaseOrderTest {

    private final List<String> colorsOfOrder;
    private final int expectedStatus;

    @Parameterized.Parameters(name = "color: {0}, return {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {List.of("BLACK"), CREATED_STATUS},
                {List.of("GREY"), CREATED_STATUS},
                {Arrays.asList("BLACK", "GREY"), CREATED_STATUS},
                {Collections.emptyList(), CREATED_STATUS}
        };
    }

    @Feature("Создание заказов с различными цветами")
    @Test
    @DisplayName("Создание заказа с параметризацией")
    @Description("Ожидаемый код ответа: 201")
    public void createOrderWithParam() {
        request = GeneratingOrder.getNewOrder(colorsOfOrder);
        validatableResponse = OrderSteps.createOrder(request);

        validatableResponse.and()
                .statusCode(expectedStatus)
                .and()
                .assertThat().body("track", notNullValue());
    }
}
