package com.smarttrade.demo.core.chooser;

import com.github.javafaker.Faker;
import com.smarttrade.demo.core.Currency;
import com.smarttrade.demo.core.sdp.BinanceSdp;
import com.smarttrade.demo.core.sdp.CoinbaseSdp;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.smarttrade.demo.core.Currency.BNB;
import static com.smarttrade.demo.core.Currency.BTC;
import static com.smarttrade.demo.core.Currency.EUR_USD;
import static org.assertj.core.api.Assertions.assertThat;


class HardcodedSdpChooserTest {

    private final BinanceSdp binanceSdp = new BinanceSdp();
    private final CoinbaseSdp coinbaseSdp = new CoinbaseSdp();
    private HardcodedSdpChooser chooser;

    @BeforeEach
    void setUp() {
        chooser = new HardcodedSdpChooser(List.of(binanceSdp, coinbaseSdp).toJavaSet());
    }

    @ParameterizedTest
    @EnumSource(Currency.class)
    void should_return_Binance_for_any_currency_for_any_other_client_than_metalor(Currency currency) {
        var clientName = new Faker().company().name();

        assertThat(chooser.choose(currency, clientName)).isEqualTo(binanceSdp);
    }

    @ParameterizedTest
    @EnumSource(Currency.class)
    void should_return_a_specific_strategy_for_metalor(Currency currency) {
        var clientName = "Metalor";

        var binanceCurrencies = List.of(BTC, BNB, EUR_USD);

        var expected = binanceCurrencies.contains(currency) ? binanceSdp : coinbaseSdp;
        assertThat(chooser.choose(currency, clientName)).isEqualTo(expected);
    }
}
