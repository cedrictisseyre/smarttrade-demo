package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.chooser.SdpChooser;

import javax.inject.Named;

@Named
public class CoreBuyOrderPlacer implements BuyOrderPlacer {
    private final SdpChooser sdpChooser;
    private final PlacingCallbacker callbacker;

    public CoreBuyOrderPlacer(SdpChooser sdpChooser, PlacingCallbacker callbacker) {
        this.sdpChooser = sdpChooser;
        this.callbacker = callbacker;
    }

    @Override
    public void placeACurrencyBuyOrder(BuyOrder buyOrder) {
        System.out.println("Calling core implementation of placeACurrencyBuyOrder");
        var sdp = sdpChooser.choose(buyOrder.currency(), buyOrder.clientName());
        var resultFuture = sdp.placeACurrencyBuyOrder(buyOrder.currency().name(), buyOrder.quantity());
        resultFuture.onSuccess(placingResult -> callbacker.callback(buyOrder.callbackUrl(), placingResult));
    }
}
