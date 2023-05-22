package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.sdp.PlacingResult;

public interface PlacingCallbacker {
    void callback(String url, PlacingResult placingResult);
}
