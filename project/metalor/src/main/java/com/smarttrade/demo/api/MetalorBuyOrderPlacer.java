package com.smarttrade.demo.api;

import com.smarttrade.demo.core.api.BuyOrder;
import com.smarttrade.demo.core.api.BuyOrderPlacer;
import com.smarttrade.demo.core.api.CoreBuyOrderPlacer;
import com.smarttrade.demo.core.sdp.PlacingResult;
import io.vavr.concurrent.Future;
import org.springframework.context.annotation.Primary;

import javax.inject.Named;

@Named
@Primary
public class MetalorBuyOrderPlacer implements BuyOrderPlacer {
    private final CoreBuyOrderPlacer coreBuyOrderPlacer;

    public MetalorBuyOrderPlacer(CoreBuyOrderPlacer coreBuyOrderPlacer) {
        this.coreBuyOrderPlacer = coreBuyOrderPlacer;
    }

    @Override
    public Future<PlacingResult> placeACurrencyBuyOrder(BuyOrder buyOrder) {
        System.out.println("Calling metalor implementation of placeACurrencyBuyOrder");
        var resultFuture = coreBuyOrderPlacer.placeACurrencyBuyOrder(buyOrder);
        System.out.println("Sending SMS as a callback");
        return resultFuture;
    }
}
