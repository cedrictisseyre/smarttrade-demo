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

        var buyOrder = randomBuyOrder();

        buyOrderPlacer.placeACurrencyBuyOrder(buyOrder);

        return ResponseEntity.ok("Everything worked fine");
    }

    private BuyOrder randomBuyOrder() {
        Faker faker = new Faker();

        return new BuyOrder(
            faker.idNumber().valid(),
            faker.options().option(Currency.class),
            Integer.valueOf(faker.number().randomDigit()),
            faker.company().name(),
            randomCallbackUrl()
        );
    }

    public String randomCallbackUrl() {
        Faker faker = new Faker();

        return "http://%s/%s".formatted(faker.internet().domainName(), faker.internet().slug());
    }
}
