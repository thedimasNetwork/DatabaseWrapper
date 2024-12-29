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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import stellar.database.gen.Keys;
import stellar.database.gen.Mindustry;
import stellar.database.gen.tables.records.BansRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Bans extends TableImpl<BansRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>mindustry.bans</code>
     */
    public static final Bans bans = new Bans();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BansRecord> getRecordType() {
        return BansRecord.class;
    }

    /**
     * The column <code>mindustry.bans.id</code>.
     */
    public final TableField<BansRecord, Integer> id = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>mindustry.bans.admin</code>.
     */
    public final TableField<BansRecord, String> admin = createField(DSL.name("admin"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.bans.target</code>.
     */
    public final TableField<BansRecord, String> target = createField(DSL.name("target"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.bans.created</code>.
     */
    public final TableField<BansRecord, OffsetDateTime> created = createField(DSL.name("created"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column <code>mindustry.bans.until</code>.
     */
    public final TableField<BansRecord, OffsetDateTime> until = createField(DSL.name("until"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>mindustry.bans.reason</code>.
     */
    public final TableField<BansRecord, String> reason = createField(DSL.name("reason"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>mindustry.bans.active</code>.
     */
    public final TableField<BansRecord, Boolean> active = createField(DSL.name("active"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>mindustry.bans.whitelist</code>.
     */
    public final TableField<BansRecord, String[]> whitelist = createField(DSL.name("whitelist"), SQLDataType.VARCHAR(40).getArrayDataType(), this, "");

    private Bans(Name alias, Table<BansRecord> aliased) {
        this(alias, aliased, null);
    }

    private Bans(Name alias, Table<BansRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>mindustry.bans</code> table reference
     */
    public Bans(String alias) {
        this(DSL.name(alias), bans);
    }

    /**
     * Create an aliased <code>mindustry.bans</code> table reference
     */
    public Bans(Name alias) {
        this(alias, bans);
    }

    /**
     * Create a <code>mindustry.bans</code> table reference
     */
    public Bans() {
        this(DSL.name("bans"), null);
    }

    public <O extends Record> Bans(Table<O> child, ForeignKey<O, BansRecord> key) {
        super(child, key, bans);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Mindustry.mindustry;
    }

    @Override
    public Identity<BansRecord, Integer> getIdentity() {
        return (Identity<BansRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<BansRecord> getPrimaryKey() {
        return Keys.idx_18431Primary;
    }

    @Override
    public Bans as(String alias) {
        return new Bans(DSL.name(alias), this);
    }

    @Override
    public Bans as(Name alias) {
        return new Bans(alias, this);
    }

    @Override
    public Bans as(Table<?> alias) {
        return new Bans(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Bans rename(String name) {
        return new Bans(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Bans rename(Name name) {
        return new Bans(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Bans rename(Table<?> name) {
        return new Bans(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, String, String, OffsetDateTime, OffsetDateTime, String, Boolean, String[]> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Integer, ? super String, ? super String, ? super OffsetDateTime, ? super OffsetDateTime, ? super String, ? super Boolean, ? super String[], ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Integer, ? super String, ? super String, ? super OffsetDateTime, ? super OffsetDateTime, ? super String, ? super Boolean, ? super String[], ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
