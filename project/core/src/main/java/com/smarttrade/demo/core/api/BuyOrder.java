package com.smarttrade.demo.core.api;

import com.smarttrade.demo.core.Currency;

public record BuyOrder(
    String refId,
    Currency currency,
    int quantity,
    String clientName,
    String callbackUrl
) {
}
