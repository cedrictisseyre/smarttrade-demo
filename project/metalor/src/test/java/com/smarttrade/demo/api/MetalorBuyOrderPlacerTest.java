package com.smarttrade.demo.api;

import com.smarttrade.demo.core.api.CoreBuyOrderPlacer;
import org.junit.jupiter.api.Test;

import static com.smarttrade.demo.api.BuyOrderFixture.aRandomBuyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MetalorBuyOrderPlacerTest {

    @Test
    void should_call_core() {
        var buyOrder = aRandomBuyOrder();
        var coreBuyOrderPlacer = mock(CoreBuyOrderPlacer.class);
        var sut = new MetalorBuyOrderPlacer(coreBuyOrderPlacer);

        sut.placeACurrencyBuyOrder(buyOrder);

        verify(coreBuyOrderPlacer).placeACurrencyBuyOrder(buyOrder);
    }
}
