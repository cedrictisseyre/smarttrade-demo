package com.smarttrade.demo.api;

import com.smarttrade.demo.core.api.CoreBuyOrderPlacer;
import com.smarttrade.demo.domain.ClientPhoneNumber;
import com.smarttrade.demo.domain.ClientPhoneNumberRepository;
import com.smarttrade.demo.infrastructure.SmsSender;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static com.smarttrade.demo.api.BuyOrderFixture.aRandomBuyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class MetalorBuyOrderPlacerTest {

    @Test
    void should_call_core() {
        var buyOrder = aRandomBuyOrder();
        var coreBuyOrderPlacer = mock(CoreBuyOrderPlacer.class);
        var repository = mock(ClientPhoneNumberRepository.class);
        when(repository.find(any())).thenReturn(Option.of(new ClientPhoneNumber(buyOrder.clientName(), "0602030405")));
        var smsSender = mock(SmsSender.class);
        var sut = new MetalorBuyOrderPlacer(coreBuyOrderPlacer, repository, smsSender);

        sut.placeACurrencyBuyOrder(buyOrder);

        verify(coreBuyOrderPlacer).placeACurrencyBuyOrder(buyOrder);
    }

    @Test
    void should_send_sms_to_client_phone_number_when_known() {
        var buyOrder = aRandomBuyOrder();
        var phoneNumber = "0602030405";
        var coreBuyOrderPlacer = mock(CoreBuyOrderPlacer.class);
        var repository = mock(ClientPhoneNumberRepository.class);
        when(repository.find(buyOrder.clientName())).thenReturn(Option.of(new ClientPhoneNumber(buyOrder.clientName(), phoneNumber)));

        var smsSender = mock(SmsSender.class);
        var sut = new MetalorBuyOrderPlacer(coreBuyOrderPlacer, repository, smsSender);

        sut.placeACurrencyBuyOrder(buyOrder);

        var expectedMessage = "Transaction %s approved for currency %s".formatted(buyOrder.refId(), buyOrder.currency().name());
        verify(smsSender).send(phoneNumber, expectedMessage);
    }

    @Test
    void should_not_send_sms_to_client_phone_number_when_unknown() {
        var buyOrder = aRandomBuyOrder();
        var phoneNumber = "0602030405";
        var coreBuyOrderPlacer = mock(CoreBuyOrderPlacer.class);
        var repository = mock(ClientPhoneNumberRepository.class);
        when(repository.find(buyOrder.clientName())).thenReturn(Option.none());

        var smsSender = mock(SmsSender.class);
        var sut = new MetalorBuyOrderPlacer(coreBuyOrderPlacer, repository, smsSender);

        sut.placeACurrencyBuyOrder(buyOrder);

        verifyNoInteractions(smsSender);
    }
}
