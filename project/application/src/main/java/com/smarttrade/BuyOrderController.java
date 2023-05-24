package com.smarttrade;

import com.github.javafaker.Faker;
import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.api.BuyOrder;
import com.smarttrade.demo.core.api.BuyOrderPlacer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyOrderController {
    private final BuyOrderPlacer buyOrderPlacer;

    public BuyOrderController(BuyOrderPlacer buyOrderPlacer) {
        this.buyOrderPlacer = buyOrderPlacer;
    }

    @GetMapping("/buy-order")
    public ResponseEntity<String> buyOrder() {
        System.out.println("Demo called on BuyOrderController");

        // Here we are placing a random buy order because we are just trying to demonstrate a principle
        var buyOrder = aRandomBuyOrder();
        buyOrderPlacer.placeACurrencyBuyOrder(buyOrder);

        return ResponseEntity.ok("Everything worked fine");
    }

    @GetMapping("/buy-order/{clientName}")
    public ResponseEntity<String> buyOrderWithClientName(@PathVariable("clientName") String clientName) {
        System.out.println("Demo called on BuyOrderController");

        var buyOrder = aRandomBuyOrderWithClientName(clientName);
        buyOrderPlacer.placeACurrencyBuyOrder(buyOrder);

        return ResponseEntity.ok("Everything worked fine");
    }

    public static BuyOrder aRandomBuyOrder() {
        return aRandomBuyOrderWithClientName(new Faker().company().name());
    }

    public static BuyOrder aRandomBuyOrderWithClientName(String clientName) {
        // Faker is used here to show an example of random, but realistic, data
        Faker faker = new Faker();

        return new BuyOrder(
            faker.idNumber().valid(),
            faker.options().option(Currency.class),
            Integer.valueOf(faker.number().randomDigit()),
            clientName,
            "http://%s/%s".formatted(faker.internet().domainName(), faker.internet().slug())
        );
    }
}
