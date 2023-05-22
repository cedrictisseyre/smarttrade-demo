package com.smarttrade.demo.core.sdp;

import io.vavr.concurrent.Future;

import javax.inject.Named;

@Named
public class BinanceSdp implements Sdp {
    @Override
    public Future<PlacingResult> placeACurrencyBuyOrder(String currency, Integer quantity) {
        // TODO : fake call to Binance and return result
        System.out.printf("Call Binance for placing a buy order of currency %d %s%n", quantity, currency);
        return Future.successful(PlacingResult.Approved);
    }
}
