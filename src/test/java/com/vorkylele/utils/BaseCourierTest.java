package com.vorkylele.utils;

import com.vorkylele.api.pojo.CreateCourier;
import io.restassured.response.ValidatableResponse;

public class BaseCourierTest {

    protected CreateCourier request;
    protected ValidatableResponse createCourierResponse;
    protected ValidatableResponse loginResponse;
    protected ValidatableResponse errorResponse;
}
