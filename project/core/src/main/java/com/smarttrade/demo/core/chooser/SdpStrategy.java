package com.smarttrade.demo.core.chooser;

import com.smarttrade.demo.core.Currency;
import io.vavr.collection.Map;

public record SdpStrategy(
    Map<Currency, Class<?>> currencyToSdp,
    Class<?> defaultSdp
) {
    public Class<?> getSdpClass(Currency currency) {
        return currencyToSdp.get(currency).getOrElse(defaultSdp);
    }
}
