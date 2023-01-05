package com.vorkylele.tests.orderTests;

import com.vorkylele.utils.BaseOrderTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import com.vorkylele.steps.OrderSteps;

import static com.vorkylele.helpers.StatusCodes.OK_STATUS;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@DisplayName("Получение списка заказов")
@Epic("Получение списка заказов")
public class GetOrderTest extends BaseOrderTest {

    @Feature("Получение списка заказов с положительным результатом")
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Ожидаемый код ответа: 200")
    public void getListOfOrder() {
        validatableResponse = OrderSteps.getOrderList();
        validatableResponse.and().log().all()
                .statusCode(OK_STATUS)
                .and()
                .body("orders", hasSize(greaterThan(0)));
    }
}
