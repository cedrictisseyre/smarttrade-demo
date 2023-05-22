package com.smarttrade.demo.core;

public enum Currency {
    BTC("Bitcoin"),
    ETH("Ethereum"),
    BNB("Binance Coin"),
    XRP("Ripple"),
    EUR_USD("Euro contre le dollar américain"),
    USD_JPY("Dollar américain contre le yen japonais"),
    GBP_USD("Livre sterling contre le dollar américain"),
    USD_CHF("Dollar américain contre le franc suisse");


    public final String label;

    private Currency(String label) {
        this.label = label;
    }
}
