package com.smarttrade.demo.api;

import com.github.javafaker.Faker;
import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.api.BuyOrder;

public class BuyOrderFixture {
    public static BuyOrder aRandomBuyOrder() {
        Faker faker = new Faker();

        return new BuyOrder(
            faker.idNumber().valid(),
            faker.options().option(Currency.class),
            Integer.valueOf(faker.number().randomDigit()),
            faker.company().name(),
            "http://%s/%s".formatted(faker.internet().domainName(), faker.internet().slug())
        );
    }

}
