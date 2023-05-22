/*
 * This file is generated by jOOQ.
 */
package com.smarttrade.demo.core.infrastructure.sql.tables.records;


import com.smarttrade.demo.core.infrastructure.sql.tables.SecondTable;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SecondTableRecord extends TableRecordImpl<SecondTableRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>metalor.second_table.id</code>.
     */
    public SecondTableRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>metalor.second_table.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>metalor.second_table.name</code>.
     */
    public SecondTableRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>metalor.second_table.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return SecondTable.SECOND_TABLE.ID;
    }

    @Override
    public Field<String> field2() {
        return SecondTable.SECOND_TABLE.NAME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public SecondTableRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public SecondTableRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public SecondTableRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SecondTableRecord
     */
    public SecondTableRecord() {
        super(SecondTable.SECOND_TABLE);
    }

    /**
     * Create a detached, initialised SecondTableRecord
     */
    public SecondTableRecord(Integer id, String name) {
        super(SecondTable.SECOND_TABLE);

        setId(id);
        setName(name);
    }
}