package com.smarttrade.demo.domain;

import com.smarttrade.demo.DbTestConfig;
import com.smarttrade.demo.infrastructure.JooqClientPhoneNumberRepository;
import com.smarttrade.demo.metalor.infrastructure.sql.Tables;
import com.smarttrade.demo.metalor.infrastructure.sql.tables.records.ClientPhoneNumberRecord;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(
    initializers = DbTestConfig.Initializer.class,
    classes = {
        DbTestConfig.class,
        ClientPhoneNumberRepository.class
    }
)
@JooqTest
class JooqClientPhoneNumberRepositoryTest {
    @Autowired
    private DSLContext dslContext;

    private ClientPhoneNumberRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JooqClientPhoneNumberRepository(dslContext);
    }

    @Test
    void should_fetch_phone_number_for_a_known_client() {
        dslContext.insertInto(Tables.CLIENT_PHONE_NUMBER)
            .set(new ClientPhoneNumberRecord("client 1", "phone 1"))
            .execute();
        dslContext.insertInto(Tables.CLIENT_PHONE_NUMBER)
            .set(new ClientPhoneNumberRecord("client 2", "phone 2"))
            .execute();

        var clientPhoneNumber = repository.find("client 1");

        assertThat(clientPhoneNumber).isEqualTo(Option.of(new ClientPhoneNumber("client 1", "phone 1")));
    }

    @Test
    void should_return_option_none_when_client_not_found() {
        var clientPhoneNumber = repository.find("unknown client");

        assertThat(clientPhoneNumber).isEqualTo(Option.none());
    }
}
