package com.vorkylele.api.generatingOfClasses;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import com.vorkylele.api.pojo.CreateCourier;

import java.util.List;

public class GeneratingCourier {

        public static CreateCourier getNewCourier() {
            return CreateCourier.builder()
                    .login(Faker.instance().name().username())
                    .password(Faker.instance().internet().password())
                    .firstName(Faker.instance().name().firstName())
                    .build();
        }

    public static CreateCourier getNewCourierWithFirstNameNull() {
        return CreateCourier.builder()
                .login(Faker.instance().name().username())
                .password(Faker.instance().internet().password())
                .firstName(null)
                .build();
    }


    public static CreateCourier getNewCourierWithLoginNull() {
        return CreateCourier.builder()
                .login(null)
                .password(Faker.instance().internet().password())
                .firstName(Faker.instance().name().firstName())
                .build();
    }

    public static CreateCourier getNewCourierWithPasswordNull() {
        return CreateCourier.builder()
                .login(Faker.instance().name().username())
                .password(null)
                .firstName(Faker.instance().name().firstName())
                .build();
    }
}
