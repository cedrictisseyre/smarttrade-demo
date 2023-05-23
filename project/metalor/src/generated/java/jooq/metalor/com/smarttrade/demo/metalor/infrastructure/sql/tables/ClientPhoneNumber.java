/*
 * This file is generated by jOOQ.
 */
package com.smarttrade.demo.metalor.infrastructure.sql.tables;


import com.smarttrade.demo.metalor.infrastructure.sql.Keys;
import com.smarttrade.demo.metalor.infrastructure.sql.Metalor;
import com.smarttrade.demo.metalor.infrastructure.sql.tables.records.ClientPhoneNumberRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ClientPhoneNumber extends TableImpl<ClientPhoneNumberRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>metalor.client_phone_number</code>
     */
    public static final ClientPhoneNumber CLIENT_PHONE_NUMBER = new ClientPhoneNumber();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ClientPhoneNumberRecord> getRecordType() {
        return ClientPhoneNumberRecord.class;
    }

    /**
     * The column <code>metalor.client_phone_number.client_name</code>.
     */
    public final TableField<ClientPhoneNumberRecord, String> CLIENT_NAME = createField(DSL.name("client_name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>metalor.client_phone_number.phone_number</code>.
     */
    public final TableField<ClientPhoneNumberRecord, String> PHONE_NUMBER = createField(DSL.name("phone_number"), SQLDataType.VARCHAR.nullable(false), this, "");

    private ClientPhoneNumber(Name alias, Table<ClientPhoneNumberRecord> aliased) {
        this(alias, aliased, null);
    }

    private ClientPhoneNumber(Name alias, Table<ClientPhoneNumberRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>metalor.client_phone_number</code> table reference
     */
    public ClientPhoneNumber(String alias) {
        this(DSL.name(alias), CLIENT_PHONE_NUMBER);
    }

    /**
     * Create an aliased <code>metalor.client_phone_number</code> table reference
     */
    public ClientPhoneNumber(Name alias) {
        this(alias, CLIENT_PHONE_NUMBER);
    }

    /**
     * Create a <code>metalor.client_phone_number</code> table reference
     */
    public ClientPhoneNumber() {
        this(DSL.name("client_phone_number"), null);
    }

    public <O extends Record> ClientPhoneNumber(Table<O> child, ForeignKey<O, ClientPhoneNumberRecord> key) {
        super(child, key, CLIENT_PHONE_NUMBER);
    }

    @Override
    public Schema getSchema() {
        return Metalor.METALOR;
    }

    @Override
    public UniqueKey<ClientPhoneNumberRecord> getPrimaryKey() {
        return Keys.CLIENT_PHONE_NUMBER_PK;
    }

    @Override
    public List<UniqueKey<ClientPhoneNumberRecord>> getKeys() {
        return Arrays.<UniqueKey<ClientPhoneNumberRecord>>asList(Keys.CLIENT_PHONE_NUMBER_PK);
    }

    @Override
    public ClientPhoneNumber as(String alias) {
        return new ClientPhoneNumber(DSL.name(alias), this);
    }

    @Override
    public ClientPhoneNumber as(Name alias) {
        return new ClientPhoneNumber(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ClientPhoneNumber rename(String name) {
        return new ClientPhoneNumber(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ClientPhoneNumber rename(Name name) {
        return new ClientPhoneNumber(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
