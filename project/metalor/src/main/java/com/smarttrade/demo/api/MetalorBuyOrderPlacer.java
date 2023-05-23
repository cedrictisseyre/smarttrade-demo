package com.smarttrade.demo.api;

import com.smarttrade.demo.core.api.BuyOrder;
import com.smarttrade.demo.core.api.BuyOrderPlacer;
import com.smarttrade.demo.core.api.CoreBuyOrderPlacer;
import com.smarttrade.demo.core.sdp.PlacingResult;
import com.smarttrade.demo.domain.ClientPhoneNumberRepository;
import com.smarttrade.demo.infrastructure.SmsSender;
import io.vavr.concurrent.Future;
import org.springframework.context.annotation.Primary;

import javax.inject.Named;

@Named
@Primary
public class MetalorBuyOrderPlacer implements BuyOrderPlacer {
    private final CoreBuyOrderPlacer coreBuyOrderPlacer;
    private final ClientPhoneNumberRepository repository;
    private final SmsSender smsSender;

    public MetalorBuyOrderPlacer(
        CoreBuyOrderPlacer coreBuyOrderPlacer,
        ClientPhoneNumberRepository repository,
        SmsSender smsSender
    ) {
        this.coreBuyOrderPlacer = coreBuyOrderPlacer;
        this.repository = repository;
        this.smsSender = smsSender;
    }

    @Override
    public Future<PlacingResult> placeACurrencyBuyOrder(BuyOrder buyOrder) {
        System.out.println("Calling metalor implementation of placeACurrencyBuyOrder");
        var resultFuture = coreBuyOrderPlacer.placeACurrencyBuyOrder(buyOrder);

        var clientPhoneNumber = repository.find(buyOrder.clientName());

        if (clientPhoneNumber.isEmpty()) {
            System.out.println("Not sending SMS as phone number is unknown");
        } else {
            var message = "Transaction %s approved for currency %s".formatted(buyOrder.refId(), buyOrder.currency().name());
            smsSender.send(clientPhoneNumber.get().phoneNumber(), message);
        }

        return resultFuture;
    }
}
