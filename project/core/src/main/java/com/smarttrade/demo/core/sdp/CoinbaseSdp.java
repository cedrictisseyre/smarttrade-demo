package com.smarttrade.demo.core.sdp;

import io.vavr.concurrent.Future;

import javax.inject.Named;

@Named
public class CoinbaseSdp implements Sdp {
    @Override
    public Future<PlacingResult> placeACurrencyBuyOrder(String currency, Integer quantity) {
        // TODO : fake call to Coinbase and return result
        System.out.printf("Call Coinbase for placing a buy order of currency %d %s%n", quantity, currency);
        return Future.successful(PlacingResult.Approved);
    }
}
