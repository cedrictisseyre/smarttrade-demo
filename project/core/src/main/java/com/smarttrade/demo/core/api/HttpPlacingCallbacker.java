package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.sdp.PlacingResult;

import javax.inject.Named;

@Named
public class HttpPlacingCallbacker implements PlacingCallbacker {
    @Override
    public void callback(String url, PlacingResult placingResult) {
        // TODO: make a real callback here
    }
}
