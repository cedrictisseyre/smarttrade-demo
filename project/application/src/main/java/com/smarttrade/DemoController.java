package com.smarttrade;

import com.github.javafaker.Faker;
import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.api.BuyOrder;
import com.smarttrade.demo.core.api.BuyOrderPlacer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final BuyOrderPlacer buyOrderPlacer;

    public DemoController(BuyOrderPlacer buyOrderPlacer) {
        this.buyOrderPlacer = buyOrderPlacer;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        System.out.println("Demo called on DemoController");

        // Here we are placing a random buy order because we are just trying to demonstrate a principle
        var buyOrder = aRandomBuyOrder();
        buyOrderPlacer.placeACurrencyBuyOrder(buyOrder);

        return ResponseEntity.ok("Everything worked fine");
    }

    public static BuyOrder aRandomBuyOrder() {
        // Faker is used here to show an example of random, but realistic, data
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
