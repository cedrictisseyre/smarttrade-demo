package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.sdp.PlacingResult;
import io.vavr.concurrent.Future;

public interface BuyOrderPlacer {
    Future<PlacingResult> placeACurrencyBuyOrder(BuyOrder buyOrder);
}
