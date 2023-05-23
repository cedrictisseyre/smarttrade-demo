package com.smarttrade.demo.infrastructure;

import com.smarttrade.demo.domain.ClientPhoneNumber;
import com.smarttrade.demo.domain.ClientPhoneNumberRepository;
import com.smarttrade.demo.metalor.infrastructure.sql.Tables;
import com.smarttrade.demo.metalor.infrastructure.sql.tables.records.ClientPhoneNumberRecord;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.jooq.Table;

import javax.inject.Named;

import static com.smarttrade.demo.metalor.infrastructure.sql.Tables.CLIENT_PHONE_NUMBER;

@Named
public class JooqClientPhoneNumberRepository implements ClientPhoneNumberRepository {
    private final DSLContext dslContext;

    public JooqClientPhoneNumberRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Option<ClientPhoneNumber> find(String clientName) {
        var record = dslContext.fetchOne(CLIENT_PHONE_NUMBER, CLIENT_PHONE_NUMBER.CLIENT_NAME.eq(clientName));

        return Option.of(record).map( r -> new ClientPhoneNumber(clientName, r.getPhoneNumber()));
    }
}
