package com.smarttrade.demo.core.chooser;

import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.sdp.BinanceSdp;
import com.smarttrade.demo.core.sdp.CoinbaseSdp;
import com.smarttrade.demo.core.sdp.Sdp;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;

import javax.inject.Named;

@Named
public class HardcodedSdpChooser implements SdpChooser {
    private final HashSet<Sdp> sdpList;

    public HardcodedSdpChooser(java.util.Set<Sdp> sdpList) {
        this.sdpList = HashSet.ofAll(sdpList);
    }

    // In a real case, we should rely on a strategy stored in a database
    // but we simplified in this demo to keep easily explorable code
    public Sdp choose(Currency currency, String clientName) {
        var strategy = hardcodedSdpStrategy(clientName);
        var sdpClass = strategy.getSdpClass(currency);
        return chosenSdp(sdpClass);
    }

    public SdpStrategy hardcodedSdpStrategy(String clientName) {
        if(clientName.startsWith("Metalor")) {
            return new SdpStrategy(
                HashMap.of(
                    Currency.BTC, BinanceSdp.class,
                    Currency.ETH, CoinbaseSdp.class,
                    Currency.BNB, BinanceSdp.class,
                    Currency.EUR_USD, BinanceSdp.class
                    ),
                CoinbaseSdp.class
            );
        }

        return new SdpStrategy(
            HashMap.empty(),
            BinanceSdp.class
        );
    }

    private Sdp chosenSdp(Class<?> choosenClass) {
        return this.sdpList.find(s -> s.getClass().equals(choosenClass)).get();
    }
}
