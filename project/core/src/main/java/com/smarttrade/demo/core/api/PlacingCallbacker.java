package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.sdp.PlacingResult;

// As we don't want CoreBuyOrderPlacer to depends directly on HTTP, we have introduced here a collaborator to do that
public interface PlacingCallbacker {
    void callback(String url, PlacingResult placingResult);
}
