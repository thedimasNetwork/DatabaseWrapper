/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables.records;


import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

import stellar.database.enums.MessageType;
import stellar.database.gen.tables.Messages;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MessagesRecord extends UpdatableRecordImpl<MessagesRecord> implements Record8<Integer, OffsetDateTime, String, String, String, MessageType, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>mindustry.messages.id</code>.
     */
    public MessagesRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>mindustry.messages.timestamp</code>.
     */
    public MessagesRecord setTimestamp(OffsetDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.timestamp</code>.
     */
    public OffsetDateTime getTimestamp() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>mindustry.messages.server</code>.
     */
    public MessagesRecord setServer(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.server</code>.
     */
    public String getServer() {
        return (String) get(2);
    }

    /**
     * Setter for <code>mindustry.messages.from</code>.
     */
    public MessagesRecord setFrom(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.from</code>.
     */
    public String getFrom() {
        return (String) get(3);
    }

    /**
     * Setter for <code>mindustry.messages.target</code>.
     */
    public MessagesRecord setTarget(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.target</code>.
     */
    public String getTarget() {
        return (String) get(4);
    }

    /**
     * Setter for <code>mindustry.messages.type</code>.
     */
    public MessagesRecord setType(MessageType value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.type</code>.
     */
    public MessageType getType() {
        return (MessageType) get(5);
    }

    /**
     * Setter for <code>mindustry.messages.text</code>.
     */
    public MessagesRecord setText(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.text</code>.
     */
    public String getText() {
        return (String) get(6);
    }

    /**
     * Setter for <code>mindustry.messages.locale</code>.
     */
    public MessagesRecord setLocale(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>mindustry.messages.locale</code>.
     */
    public String getLocale() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, OffsetDateTime, String, String, String, MessageType, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, OffsetDateTime, String, String, String, MessageType, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Messages.messages.id;
    }

    @Override
    public Field<OffsetDateTime> field2() {
        return Messages.messages.timestamp;
    }

    @Override
    public Field<String> field3() {
        return Messages.messages.server;
    }

    @Override
    public Field<String> field4() {
        return Messages.messages.from;
    }

    @Override
    public Field<String> field5() {
        return Messages.messages.target;
    }

    @Override
    public Field<MessageType> field6() {
        return Messages.messages.type;
    }

    @Override
    public Field<String> field7() {
        return Messages.messages.text;
    }

    @Override
    public Field<String> field8() {
        return Messages.messages.locale;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public OffsetDateTime component2() {
        return getTimestamp();
    }

    @Override
    public String component3() {
        return getServer();
    }

    @Override
    public String component4() {
        return getFrom();
    }

    @Override
    public String component5() {
        return getTarget();
    }

    @Override
    public MessageType component6() {
        return getType();
    }

    @Override
    public String component7() {
        return getText();
    }

    @Override
    public String component8() {
        return getLocale();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public OffsetDateTime value2() {
        return getTimestamp();
    }

    @Override
    public String value3() {
        return getServer();
    }

    @Override
    public String value4() {
        return getFrom();
    }

    @Override
    public String value5() {
        return getTarget();
    }

    @Override
    public MessageType value6() {
        return getType();
    }

    @Override
    public String value7() {
        return getText();
    }

    @Override
    public String value8() {
        return getLocale();
    }

    @Override
    public MessagesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public MessagesRecord value2(OffsetDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public MessagesRecord value3(String value) {
        setServer(value);
        return this;
    }

    @Override
    public MessagesRecord value4(String value) {
        setFrom(value);
        return this;
    }

    @Override
    public MessagesRecord value5(String value) {
        setTarget(value);
        return this;
    }

    @Override
    public MessagesRecord value6(MessageType value) {
        setType(value);
        return this;
    }

    @Override
    public MessagesRecord value7(String value) {
        setText(value);
        return this;
    }

    @Override
    public MessagesRecord value8(String value) {
        setLocale(value);
        return this;
    }

    @Override
    public MessagesRecord values(Integer value1, OffsetDateTime value2, String value3, String value4, String value5, MessageType value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MessagesRecord
     */
    public MessagesRecord() {
        super(Messages.messages);
    }

    /**
     * Create a detached, initialised MessagesRecord
     */
    public MessagesRecord(Integer id, OffsetDateTime timestamp, String server, String from, String target, MessageType type, String text, String locale) {
        super(Messages.messages);

        setId(id);
        setTimestamp(timestamp);
        setServer(server);
        setFrom(from);
        setTarget(target);
        setType(type);
        setText(text);
        setLocale(locale);
    }
}
