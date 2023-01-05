package com.vorkylele.api.generatingOfClasses;

import com.github.javafaker.Faker;
import com.vorkylele.api.pojo.CreateOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class GeneratingOrder {
    public static CreateOrder getNewOrder(List<String> color) {
        CreateOrder createOrder = new CreateOrder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        createOrder.setFirstName(Faker.instance().name().firstName());
        createOrder.setLastName(Faker.instance().name().lastName());
        createOrder.setAddress(Faker.instance().address().fullAddress());
        createOrder.setPhone(Faker.instance().phoneNumber().phoneNumber());
        createOrder.setDeliveryDate(LocalDate.now().format(formatter));
        createOrder.setComment(Faker.instance().rickAndMorty().toString());
        createOrder.setRentTime(new Random().nextInt(19));
        createOrder.setColor(color);

        return createOrder;
    }
}
