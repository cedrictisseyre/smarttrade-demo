package com.smarttrade.demo.core.api;

import com.github.javafaker.Faker;
import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.chooser.SdpChooser;
import com.smarttrade.demo.core.sdp.PlacingResult;
import com.smarttrade.demo.core.sdp.Sdp;
import io.vavr.concurrent.Future;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CoreBuyOrderPlacerTest {
    @Test
    void should_call_sdp_returned_by_chooser() {
        var buyOrder = new BuyOrder("1", Currency.ETH, 10, new Faker().company().name(), "");

        var sdp1 = new Sdp() {
            @Override
            public Future<PlacingResult> placeACurrencyBuyOrder(String currency, Integer quantity) {
                return Future.successful(PlacingResult.Rejected);
            }
        };
        var sdp = mock(Sdp.class);
        when(sdp.placeACurrencyBuyOrder(any(), any())).thenReturn(Future.successful(PlacingResult.Rejected));


        var sdpChooser = mock(SdpChooser.class);
        when(sdpChooser.choose(buyOrder.currency(), buyOrder.clientName())).thenReturn(sdp);




        var callbacker = mock(PlacingCallbacker.class);
        var sut = new CoreBuyOrderPlacer(sdpChooser, callbacker);

        sut.placeACurrencyBuyOrder(buyOrder);

        verify(sdp).placeACurrencyBuyOrder(buyOrder.currency().name(), buyOrder.quantity());
    }

    @Test
    void should_call_callback_url_when_approved_by_sdp() {
        var refId = "1";
        String callbackUrl = "http://api.dummy.fr/buy-order-callback";

        var sdp = mock(Sdp.class);
        when(sdp.placeACurrencyBuyOrder(any(), any())).thenReturn(Future.successful(PlacingResult.Approved));
        var sdpChooser = mock(SdpChooser.class);
        when(sdpChooser.choose(any(), any())).thenReturn(sdp);
        var callbacker = mock(PlacingCallbacker.class);
        var sut = new CoreBuyOrderPlacer(sdpChooser, callbacker);

        sut.placeACurrencyBuyOrder(new BuyOrder(refId, Currency.ETH, 10, "dummy", callbackUrl));

        verify(callbacker).callback(callbackUrl, PlacingResult.Approved);
    }
}
