/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables;


import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.EnumConverter;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import stellar.database.enums.MessageType;
import stellar.database.gen.Keys;
import stellar.database.gen.Mindustry;
import stellar.database.gen.tables.records.MessagesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Messages extends TableImpl<MessagesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>mindustry.messages</code>
     */
    public static final Messages messages = new Messages();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MessagesRecord> getRecordType() {
        return MessagesRecord.class;
    }

    /**
     * The column <code>mindustry.messages.id</code>.
     */
    public final TableField<MessagesRecord, Integer> id = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>mindustry.messages.timestamp</code>.
     */
    public final TableField<MessagesRecord, OffsetDateTime> timestamp = createField(DSL.name("timestamp"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column <code>mindustry.messages.server</code>.
     */
    public final TableField<MessagesRecord, String> server = createField(DSL.name("server"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>mindustry.messages.from</code>.
     */
    public final TableField<MessagesRecord, String> from = createField(DSL.name("from"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.messages.target</code>.
     */
    public final TableField<MessagesRecord, String> target = createField(DSL.name("target"), SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>mindustry.messages.type</code>.
     */
    public final TableField<MessagesRecord, MessageType> type = createField(DSL.name("type"), SQLDataType.VARCHAR(10).nullable(false), this, "", new EnumConverter<String, MessageType>(String.class, MessageType.class));

    /**
     * The column <code>mindustry.messages.text</code>.
     */
    public final TableField<MessagesRecord, String> text = createField(DSL.name("text"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>mindustry.messages.locale</code>.
     */
    public final TableField<MessagesRecord, String> locale = createField(DSL.name("locale"), SQLDataType.VARCHAR(16).nullable(false), this, "");

    private Messages(Name alias, Table<MessagesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Messages(Name alias, Table<MessagesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>mindustry.messages</code> table reference
     */
    public Messages(String alias) {
        this(DSL.name(alias), messages);
    }

    /**
     * Create an aliased <code>mindustry.messages</code> table reference
     */
    public Messages(Name alias) {
        this(alias, messages);
    }

    /**
     * Create a <code>mindustry.messages</code> table reference
     */
    public Messages() {
        this(DSL.name("messages"), null);
    }

    public <O extends Record> Messages(Table<O> child, ForeignKey<O, MessagesRecord> key) {
        super(child, key, messages);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Mindustry.mindustry;
    }

    @Override
    public Identity<MessagesRecord, Integer> getIdentity() {
        return (Identity<MessagesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MessagesRecord> getPrimaryKey() {
        return Keys.idx_18452Primary;
    }

    @Override
    public Messages as(String alias) {
        return new Messages(DSL.name(alias), this);
    }

    @Override
    public Messages as(Name alias) {
        return new Messages(alias, this);
    }

    @Override
    public Messages as(Table<?> alias) {
        return new Messages(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages rename(String name) {
        return new Messages(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages rename(Name name) {
        return new Messages(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages rename(Table<?> name) {
        return new Messages(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, OffsetDateTime, String, String, String, MessageType, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Integer, ? super OffsetDateTime, ? super String, ? super String, ? super String, ? super MessageType, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Integer, ? super OffsetDateTime, ? super String, ? super String, ? super String, ? super MessageType, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
