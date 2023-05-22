package com.smarttrade.demo.core.sdp;

import io.vavr.concurrent.Future;

public interface Sdp {
    Future<PlacingResult> placeACurrencyBuyOrder(String currency, Integer quantity);
}
