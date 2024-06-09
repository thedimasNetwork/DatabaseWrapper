/*
 * This file is generated by jOOQ.
 */
package stellar.database.gen.tables;


import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function17;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row17;
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
import stellar.database.gen.tables.records.StatsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Stats extends TableImpl<StatsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>mindustry.stats</code>
     */
    public static final Stats stats = new Stats();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StatsRecord> getRecordType() {
        return StatsRecord.class;
    }

    /**
     * The column <code>mindustry.stats.uuid</code>.
     */
    public final TableField<StatsRecord, String> uuid = createField(DSL.name("uuid"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>mindustry.stats.waves</code>.
     */
    public final TableField<StatsRecord, Integer> waves = createField(DSL.name("waves"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.survivals</code>.
     */
    public final TableField<StatsRecord, Integer> survivals = createField(DSL.name("survivals"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.attacks</code>.
     */
    public final TableField<StatsRecord, Integer> attacks = createField(DSL.name("attacks"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.pvp</code>.
     */
    public final TableField<StatsRecord, Integer> pvp = createField(DSL.name("pvp"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hexes_captured</code>.
     */
    public final TableField<StatsRecord, Integer> hexesCaptured = createField(DSL.name("hexes_captured"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hexes_destroyed</code>.
     */
    public final TableField<StatsRecord, Integer> hexesDestroyed = createField(DSL.name("hexes_destroyed"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hexes_lost</code>.
     */
    public final TableField<StatsRecord, Integer> hexesLost = createField(DSL.name("hexes_lost"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hex_wins</code>.
     */
    public final TableField<StatsRecord, Integer> hexWins = createField(DSL.name("hex_wins"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hex_losses</code>.
     */
    public final TableField<StatsRecord, Integer> hexLosses = createField(DSL.name("hex_losses"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.hex_score</code>.
     */
    public final TableField<StatsRecord, Integer> hexScore = createField(DSL.name("hex_score"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.built</code>.
     */
    public final TableField<StatsRecord, Integer> built = createField(DSL.name("built"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.broken</code>.
     */
    public final TableField<StatsRecord, Integer> broken = createField(DSL.name("broken"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.kills</code>.
     */
    public final TableField<StatsRecord, Integer> kills = createField(DSL.name("kills"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.deaths</code>.
     */
    public final TableField<StatsRecord, Integer> deaths = createField(DSL.name("deaths"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.logins</code>.
     */
    public final TableField<StatsRecord, Integer> logins = createField(DSL.name("logins"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>mindustry.stats.messages</code>.
     */
    public final TableField<StatsRecord, Integer> messages = createField(DSL.name("messages"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    private Stats(Name alias, Table<StatsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Stats(Name alias, Table<StatsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>mindustry.stats</code> table reference
     */
    public Stats(String alias) {
        this(DSL.name(alias), stats);
    }

    /**
     * Create an aliased <code>mindustry.stats</code> table reference
     */
    public Stats(Name alias) {
        this(alias, stats);
    }

    /**
     * Create a <code>mindustry.stats</code> table reference
     */
    public Stats() {
        this(DSL.name("stats"), null);
    }

    public <O extends Record> Stats(Table<O> child, ForeignKey<O, StatsRecord> key) {
        super(child, key, stats);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Mindustry.mindustry;
    }

    @Override
    public UniqueKey<StatsRecord> getPrimaryKey() {
        return Keys.idx_16592Primary;
    }

    @Override
    public Stats as(String alias) {
        return new Stats(DSL.name(alias), this);
    }

    @Override
    public Stats as(Name alias) {
        return new Stats(alias, this);
    }

    @Override
    public Stats as(Table<?> alias) {
        return new Stats(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Stats rename(String name) {
        return new Stats(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Stats rename(Name name) {
        return new Stats(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Stats rename(Table<?> name) {
        return new Stats(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row17 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row17<String, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function17<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link #convertFrom(Class, Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function17<? super String, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
